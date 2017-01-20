package utente;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UtenteServlet
 */
public class UtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static UtenteModel gestoreutente;
	static {
		gestoreutente = new GestoreUtente();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UtenteServlet() {
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
		if (action.equalsIgnoreCase("registrazione")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String ruolo = request.getParameter("ruolo");
			String nome = request.getParameter("nome");
			String cognome = request.getParameter("cognome");
			Date data_nascita = Date.valueOf(request.getParameter("datanascita"));
			UtenteBean bean = new UtenteBean(email, password, nome, cognome, data_nascita, ruolo);
			try {
				if (gestoreutente.checkEmail(email) == false) {
					response.sendRedirect("index.jsp");
					gestoreutente.insertUtente(bean);
				} else
					response.sendRedirect("registrazione.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		if (action.equalsIgnoreCase("login")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			if ((email != null) && (password != null)) {
				try {
					if (gestoreutente.login(email, password).equals("true")) {
						String redirectedPage = "/index.jsp";
						response.sendRedirect(request.getContextPath() + redirectedPage);
						request.getSession().setAttribute("ruolo", "user");
						request.getSession().setAttribute("email", email);
					} else if (gestoreutente.login(email, password).equals("admin")) {
						String redirectedPage = "/index.jsp";
						response.sendRedirect(request.getContextPath() + redirectedPage);
						request.getSession().setAttribute("ruolo", "admin");
						request.getSession().setAttribute("email", email);

					} else {
						response.sendRedirect("login.jsp?done=no");
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if (action.equalsIgnoreCase("logout")) {
			request.getSession().removeAttribute("ruolo");
			request.getSession().invalidate();
			String redirectedPage = "/index.jsp";
			response.sendRedirect(request.getContextPath() + redirectedPage);
		}
		if (action.equalsIgnoreCase("update")) {
			String email = (String) request.getSession().getAttribute("email");
			String password = request.getParameter("password");
			String ruolo = request.getParameter("ruolo");
			String nome = request.getParameter("nome");
			String cognome = request.getParameter("cognome");
			Date data_nascita = Date.valueOf(request.getParameter("datanascita"));
			UtenteBean bean = new UtenteBean(email, password, nome, cognome, data_nascita, ruolo);
			try {
				if (request.getSession().getAttribute("ruolo").equals("user")) {
					String redirectedPage = "/areautente.jsp?esito=yes";
					response.sendRedirect(request.getContextPath() + redirectedPage);
					gestoreutente.modifyUtente(bean);					
				} else if (request.getSession().getAttribute("ruolo").equals("admin")) {
					String redirectedPage = "/utente?action=getusers&email="+email;
					response.sendRedirect(request.getContextPath() + redirectedPage);
					gestoreutente.modifyUtente(bean);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (action.equalsIgnoreCase("delete")) {
			String email = request.getParameter("email");
			String email2 = (String) request.getSession().getAttribute("email");
			try {
				if (request.getSession().getAttribute("ruolo").equals("user")) {
					String redirectedPage = "/index.jsp";
					request.getSession().invalidate();
					response.sendRedirect(request.getContextPath() + redirectedPage);
					gestoreutente.deleteUtente(email2);
				} else if (request.getSession().getAttribute("ruolo").equals("admin")) {
					String redirectedPage = "/utente?action=getusers&email="+email2;
					response.sendRedirect(request.getContextPath() + redirectedPage);
					gestoreutente.deleteUtente(email);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (action.equals("getusers")) {
			request.removeAttribute("utenti");
			request.removeAttribute("utente");
			String email = request.getParameter("email");
			try {
				request.setAttribute("utenti", gestoreutente.getUtenti());
                request.setAttribute("utente", gestoreutente.getUtente(email));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gestisciutenti.jsp");
			dispatcher.forward(request, response);
		}
		if (action.equals("getuser")) {
			String email = (String) request.getSession().getAttribute("email");
			request.removeAttribute("utente");

			try {
				request.setAttribute("utente", gestoreutente.getUtente(email));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher dispatcher2 = getServletContext().getRequestDispatcher("/modificadati.jsp");
			dispatcher2.forward(request, response);
		}

	}

}
