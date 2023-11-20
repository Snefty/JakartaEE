package controleur;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import model.Compte;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

import dao.Users_DAO;

/**
 * Servlet implementation class ServletA
 */
@WebServlet("/ServletA")
public class ServletA extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Users_DAO uDAO = new Users_DAO();
	/**
	 * Default constructor. 
	 */
	public ServletA() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flag = request.getParameter("flag");
		
		if(flag == "connect") {
			this.doConnection(request,response);
		}
	}

	private void doConnection(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(500);

		String login = request.getParameter("login");
		String pwd = request.getParameter("mdp");

		Compte cpt = uDAO.verifierCoordonée(login, pwd);
		

		if(cpt == null) {
			request.setAttribute("LogImpo", "Login ou mot de passe inconnue !!");
			request.getRequestDispatcher("/connection.jsp").forward(request, response);
		}else {
			
				if(cpt.getType() == "a") {

					session.setAttribute("login", cpt);

					request.getRequestDispatcher("/articleAdmin.jsp").forward(request, response);
				}else {
					session.setAttribute("login", cpt);

					request.getRequestDispatcher("/articleSimple.jsp").forward(request, response);
				}

			}
		}

}
