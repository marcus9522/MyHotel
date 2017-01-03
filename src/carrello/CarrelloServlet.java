package carrello;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import camera.CameraBean;
import camera.VisualizzatoreCamera;
import camera.VisualizzatoreCameraModel;
import prenotazione.GestorePrenotazione;
import prenotazione.PrenotazioneModel;

/**
 * Servlet implementation class CarrelloServlet
 */
public class CarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static VisualizzatoreCameraModel visualizzatorecamera;
	static CarrelloModel gestorecarrello;
	static PrenotazioneModel gestoreprenotazione;

	static {
		visualizzatorecamera = new VisualizzatoreCamera();
		gestorecarrello = new GestoreCarrello();
		gestoreprenotazione = new GestorePrenotazione();
	}

	public CarrelloServlet() {
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
		if (action.equalsIgnoreCase("getcarrello")) {
			request.removeAttribute("carrello");
			request.removeAttribute("camere");
			ArrayList<Integer> idcamere = new ArrayList<Integer>();
			Collection<CameraBean> camere = new LinkedList<CameraBean>();
			String email = (String) request.getSession().getAttribute("email");
			try {
				request.setAttribute("carrello", gestorecarrello.getCarrelloUtente(email));
				idcamere = gestorecarrello.getIdCamereCarrello(email);
				for (int c : idcamere)
					camere.add(visualizzatorecamera.getCamera(c));
				request.setAttribute("camere", camere);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("insert")) {
			String email = (String) request.getSession().getAttribute("email");
			int numerocamera = Integer.valueOf(request.getParameter("numerocamera"));
			Date datainizio = Date.valueOf(request.getParameter("datainizio"));
			Date datafine = Date.valueOf(request.getParameter("datafine"));
			double prezzo = Double.valueOf(request.getParameter("prezzo"));
			try {
				if (gestoreprenotazione.checkDisponibita(numerocamera, datainizio, datafine)) {
					gestorecarrello.insertCamera(email, numerocamera, datainizio, datafine,prezzo);
					String redirectedPage = "";
					response.sendRedirect(request.getContextPath() + redirectedPage);
				} else {
					String redirectedPage = "";
					response.sendRedirect(request.getContextPath() + redirectedPage);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(action.equalsIgnoreCase("delete")){
			String email = (String) request.getSession().getAttribute("email");
			int numerocamera = Integer.valueOf(request.getParameter("numerocamera"));
			try {
				gestorecarrello.deleteCamera(email, numerocamera);
				String redirectedPage = "";
				response.sendRedirect(request.getContextPath() + redirectedPage);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(action.equalsIgnoreCase("empty")){
			String email = (String) request.getSession().getAttribute("email");
			try {
				gestorecarrello.emptyCarrello(email);
				String redirectedPage = "";
				response.sendRedirect(request.getContextPath() + redirectedPage);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
