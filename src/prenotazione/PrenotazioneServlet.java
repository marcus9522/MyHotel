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

import camera.CameraBean;
import camera.VisualizzatoreCamera;
import camera.VisualizzatoreCameraModel;
import carrello.CarrelloModel;
import carrello.GestoreCarrello;
import carrello.CarrelloBean;

public class PrenotazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static PrenotazioneModel gestoreprenotazione;
	static CarrelloModel gestorecarrello;
	static VisualizzatoreCameraModel visualizzatorecamera;
	static {
		gestoreprenotazione = new GestorePrenotazione();
		gestorecarrello = new GestoreCarrello();
		visualizzatorecamera = new VisualizzatoreCamera();

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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("insert")) {
			boolean free = true;
			String email = (String) request.getSession().getAttribute("email");
			Collection<CarrelloBean> camerecarrello = new LinkedList<CarrelloBean>();
			try {
				camerecarrello = gestorecarrello.getCarrelloUtente(email);
				Iterator<?> it = camerecarrello.iterator();
				Iterator<?> it2 = camerecarrello.iterator();
				while(it2.hasNext()){
					CarrelloBean camera = (CarrelloBean) it2.next();
					if(gestoreprenotazione.checkDisponibita(camera.getNumerocamera(), camera.getDatainizio(), camera.getDatafine())== false){
						free = false;
						String redirectedPage = "/carrello?action=getcarrello&occupata="+camera.getNumerocamera();
						response.sendRedirect(request.getContextPath() + redirectedPage);
						break;
					}
				}
				if(free == true){
				while (it.hasNext()) {
					CarrelloBean camera = (CarrelloBean) it.next();
					PrenotazioneBean bean = new PrenotazioneBean(0, email, camera.getTotale(), camera.getDatainizio(),
							camera.getDatafine(), camera.getNumerocamera());
					gestoreprenotazione.insertPrenotazione(bean);
					gestorecarrello.deleteCamera(email, camera.getNumerocamera());
				}
				String redirectedPage = "";
				response.sendRedirect(request.getContextPath() + redirectedPage);
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
			request.removeAttribute("prenotazioniall");
			try {
				request.setAttribute("prenotazioniall", gestoreprenotazione.getPrenotazioni());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gestisciprenotazioni.jsp");
			dispatcher.forward(request, response);
		}
		if (action.equalsIgnoreCase("getprenotazioniuser")) {
			request.removeAttribute("prenotazioni");
			request.removeAttribute("camere");
			String email = request.getParameter("email");
			String ruolo = (String) request.getSession().getAttribute("ruolo");
			Collection<PrenotazioneBean> prenotazioni = new LinkedList<PrenotazioneBean>();
			Collection<CameraBean> camere = new LinkedList<CameraBean>();
			try {
				prenotazioni = gestoreprenotazione.getPrenotazioniUtente(email);
				request.setAttribute("prenotazioni", prenotazioni);
				for (PrenotazioneBean p : prenotazioni) {
					camere.add(visualizzatorecamera.getCamera(p.getNumerocamera()));
				}
				request.setAttribute("camere", camere);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (ruolo.equals("user")) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/prenotazioniutente.jsp");
				dispatcher.forward(request, response);
			} else if (ruolo.equals("admin")) {
				request.removeAttribute("prenotazioniall");
				try {
					request.setAttribute("prenotazioniall", gestoreprenotazione.getPrenotazioni());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gestisciprenotazioni.jsp");
				dispatcher.forward(request, response);
			}
		}
		if(action.equalsIgnoreCase("filtra")){
			request.removeAttribute("prenotazioni");
			request.removeAttribute("camere");
			String periodo = request.getParameter("periodo");
			String order = request.getParameter("order");
			double totalemin = Double.valueOf(request.getParameter("totalemin"));
			double totalemax = Double.valueOf(request.getParameter("totalemax"));
			Collection<PrenotazioneBean> prenotazioni = new LinkedList<PrenotazioneBean>();
			Collection<CameraBean> camere = new LinkedList<CameraBean>();
			try {
				prenotazioni = gestoreprenotazione.filtraprenotazioni(periodo, order, totalemin, totalemax);
				request.setAttribute("prenotazioni", prenotazioni);
				for (PrenotazioneBean p : prenotazioni) {
					camere.add(visualizzatorecamera.getCamera(p.getNumerocamera()));
				}
				request.setAttribute("camere", camere);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/filtraprenotazioni.jsp");
			dispatcher.forward(request, response);
		}
	}

}
