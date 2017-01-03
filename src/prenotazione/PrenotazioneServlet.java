package prenotazione;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import carrello.CarrelloModel;
import carrello.GestoreCarrello;
import carrello.CarrelloBean;

public class PrenotazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static PrenotazioneModel gestoreprenotazione;
	static CarrelloModel gestorecarrello;
	static {
		gestoreprenotazione = new GestorePrenotazione();
		gestorecarrello = new GestoreCarrello();
	}

	public PrenotazioneServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("insert")) {
			int idprenotazione = Integer.valueOf(request.getParameter("idprenotazione"));
			String email = (String) request.getSession().getAttribute("email");
			Collection<CarrelloBean> camerecarrello = new LinkedList<CarrelloBean>();
			try {
				camerecarrello = gestorecarrello.getCarrelloUtente(email);
				Iterator<?> it = camerecarrello.iterator();
				while (it.hasNext()) {
					String redirectedPage = "";
					response.sendRedirect(request.getContextPath() + redirectedPage);
					CarrelloBean camera = (CarrelloBean) it.next();
					PrenotazioneBean bean = new PrenotazioneBean(idprenotazione, email, camera.getPrezzo(),
							camera.getDatainizio(), camera.getDatafine(), camera.getNumerocamera());
					gestoreprenotazione.insertPrenotazione(bean);
					gestorecarrello.deleteCamera(email, camera.getNumerocamera());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (action.equalsIgnoreCase("delete")) {
			int idprenotazione = Integer.valueOf(request.getParameter("idprenotazione"));
			try {
				String redirectedPage = "";
				response.sendRedirect(request.getContextPath() + redirectedPage);
				gestoreprenotazione.deletePrenotazione(idprenotazione);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (action.equalsIgnoreCase("getprenotazione")) {
			int idprenotazione = Integer.valueOf(request.getParameter("idprenotazione"));
			request.removeAttribute("prenotazione");
			try {
				request.setAttribute("prenotazione", gestoreprenotazione.getPrenotazione(idprenotazione));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("");
			dispatcher.forward(request, response);
		}
		if (action.equalsIgnoreCase("getprenotazioni")) {
			request.removeAttribute("prenotazioni");
			try {
				request.setAttribute("prenotazioni", gestoreprenotazione.getPrenotazioni());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("");
			dispatcher.forward(request, response);
		}
		if (action.equalsIgnoreCase("getprenotazioniuser")) {
			request.removeAttribute("prenotazioni");
			String email = (String) request.getSession().getAttribute("email");
			try {
				request.setAttribute("prenotazioni", gestoreprenotazione.getPrenotazioniUtente(email));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("");
			dispatcher.forward(request, response);
		}
	}

}
