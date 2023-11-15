package fp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet implementation class ServletA
 */
@WebServlet("/ServletA")
public class ServletA extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnexionBDArticle cBDarticle = new ConnexionBDArticle();
	ConnectionBD cBD = new ConnectionBD();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletA() {
        super();
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
		
		if(flag.equalsIgnoreCase("connect")) {
			try {
				this.doConnection(request,response);
			} catch (ServletException | IOException | SQLException e) {
				e.printStackTrace();
			}
		}else if(flag.equalsIgnoreCase("deconnect")) {
			try {
				this.doDeconnection(request,response);
			} catch (ServletException | IOException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void doDeconnection(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		HttpSession session = request.getSession(true); 
		session.invalidate();
		
		request.getRequestDispatcher("/Accueil.jsp").forward(request, response);
	}

	private void doConnection(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		HttpSession session = request.getSession(true);
		
		String login = request.getParameter("login");
		String pwd = request.getParameter("mdp");
		
		List<String> pwdBDD = this.cBD.verifierCoordonnees(login);

		if(pwdBDD == null) {
			request.getRequestDispatcher("/connection.jsp").forward(request, response);
		}else {
			if(pwd.equals(pwdBDD.get(0))) {
				session.setAttribute("BaseDonnéeArticle", this.cBDarticle.recupArticle());
				
				if(pwdBDD.get(1).equals("a")) {
					
					session.setAttribute("login", new Login(login, pwd, "a"));
					
					request.getRequestDispatcher("/articleAdmin.jsp").forward(request, response);
				}else {
					session.setAttribute("login", new Login(login, pwd, "s"));
					
					request.getRequestDispatcher("/articleSimple.jsp").forward(request, response);
				}

			}else {
				request.getRequestDispatcher("/connection.jsp").forward(request, response);
			}
		}
		
	}
}
