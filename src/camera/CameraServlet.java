package camera;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servizio.GestoreServizi;
import servizio.ServizioModel;

/**
 * Servlet implementation class CameraServlet
 */
public class CameraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static CameraModel gestorecamera;
	static VisualizzatoreCameraModel visualizzatorecamera;
	static ServizioModel gestoreservizi;

	static {
		gestorecamera = new GestoreCamera();
		visualizzatorecamera = new VisualizzatoreCamera();
		gestoreservizi = new GestoreServizi();
	}

	public CameraServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String ruolo = (String) request.getSession().getAttribute("ruolo");
		if (action.equalsIgnoreCase("getcamere")) {
			request.removeAttribute("camere");
			try {
				request.setAttribute("camere", visualizzatorecamera.getCamere());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (ruolo != null) {
				if (ruolo.equals("admin")) {
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/modificacamera.jsp");
					dispatcher.forward(request, response);
				}
				if (ruolo.equals("user")) {
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/catalogo.jsp");
					dispatcher.forward(request, response);
				}
			} else {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/catalogo.jsp");
				dispatcher.forward(request, response);
			}
		}
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ruolo = (String) request.getSession().getAttribute("ruolo");
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("insert")) {
			int numerocamera = Integer.valueOf(request.getParameter("numerocamera"));
			String tipologia = request.getParameter("tipologia");
			String immagine = request.getParameter("immagine");
			double prezzo = Double.valueOf(request.getParameter("prezzo"));
			String descrizione = request.getParameter("descrizione");
			String[] servizi = request.getParameterValues("servizi");
			CameraBean bean = new CameraBean(numerocamera, tipologia, immagine, prezzo, descrizione);
			try {
				if ((gestorecamera.checknumero(numerocamera)).equals("notfound")) {
					String redirectedPage = "/gestiscicamere.jsp";
					response.sendRedirect(request.getContextPath() + redirectedPage);
					gestorecamera.insertCamera(bean);
					if (servizi != null && servizi.length > 0) {
						for (int i = 0; i < servizi.length; i++)
							gestoreservizi.insertServizioCamera(servizi[i], numerocamera);
					}
				} else {
					String redirectedPage = "/camera?action=getservizi&done=no";
					response.sendRedirect(request.getContextPath() + redirectedPage);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (action.equalsIgnoreCase("delete")) {
			int numerocamera = Integer.valueOf(request.getParameter("numerocamera"));
			try {
				gestorecamera.deleteCamera(numerocamera);
				String redirectedPage = "/camera?action=getcamere";
				response.sendRedirect(request.getContextPath() + redirectedPage);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (action.equalsIgnoreCase("update")) {
			int numerocamera = Integer.valueOf(request.getParameter("numerocamera"));
			String tipologia = request.getParameter("tipologia");
			String immagine = request.getParameter("immagine");
			double prezzo = Double.valueOf(request.getParameter("prezzo"));
			String descrizione = request.getParameter("descrizione");
			CameraBean bean = new CameraBean(numerocamera, tipologia, immagine, prezzo, descrizione);
			try {
				String redirectedPage = "";
				response.sendRedirect(request.getContextPath() + redirectedPage);
				gestorecamera.modifyCamera(bean);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (action.equalsIgnoreCase("getcamera")) {
			int numerocamera = Integer.valueOf(request.getParameter("numerocamera"));
			request.removeAttribute("camera");
			request.removeAttribute("servizi");
			try {
				request.setAttribute("camera", visualizzatorecamera.getCamera(numerocamera));
				request.setAttribute("servizi", gestoreservizi.getServiziCamera(numerocamera));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (ruolo == null) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/camera.jsp");
				dispatcher.forward(request, response);
			} else if (ruolo.equals("admin")) {
				try {
					request.removeAttribute("camere");
					request.setAttribute("camere", visualizzatorecamera.getCamere());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/modificacamera.jsp");
				dispatcher.forward(request, response);
			} else if (ruolo.equals("user")) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/camera.jsp");
				dispatcher.forward(request, response);
			}
		}

		if (action.equalsIgnoreCase("filtra")) {
			request.removeAttribute("camere");
			double min = Double.valueOf(request.getParameter("min"));
			double max = Double.valueOf(request.getParameter("max"));
			String tipologia = request.getParameter("tipologia");
			String order = request.getParameter("order");
			try {
				request.setAttribute("camere", visualizzatorecamera.filtraCamere(min, max, tipologia, order));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/catalogo.jsp");
			dispatcher.forward(request, response);
		}
		if (action.equalsIgnoreCase("getservizi")) {
			request.removeAttribute("servizi");
			try {
				request.setAttribute("servizi", gestoreservizi.getServizi());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/inseriscicamera.jsp");
			dispatcher.forward(request, response);
		}
	}
}
