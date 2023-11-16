package fp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		}else if(flag.equalsIgnoreCase("inscri")) {
			try {
				this.doInscription(request,response);
			} catch (ServletException | IOException | SQLException e) {
				e.printStackTrace();
			}
		}else if(flag.equalsIgnoreCase("deconnect")) {
			try {
				this.doDeconnection(request,response);
			} catch (ServletException | IOException | SQLException e) {
				e.printStackTrace();
			}
		}else if(flag.equalsIgnoreCase("creationArticle")) {
			try {
				this.doAjouterArticle(request,response);
			} catch (ServletException | IOException | SQLException e) {
				e.printStackTrace();
			}
		}else if(flag.equalsIgnoreCase("supprimerArticle")) {
			try {
				this.doAjouterArticle(request,response);
			} catch (ServletException | IOException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void doAjouterArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		HttpSession session = request.getSession(true);
		
		String designation = request.getParameter("nom");
		int prixUnitaire = Integer.parseInt(request.getParameter("pU"));
		int quantité = Integer.parseInt(request.getParameter("qte"));
		int catégorie = Integer.parseInt(request.getParameter("cat"));
		
		String resultat;
		Map<String,String> erreurs = new HashMap<String,String>();
		
		if(erreurs.isEmpty()) {
			resultat = "Succès de l'inscription";
			
			session.setAttribute("BaseDonnéeArticle", this.cBDarticle.recupArticle());
			cBDarticle.ajouterArticle(new Article(designation, prixUnitaire, quantité, catégorie));
			
		}else {
			resultat = "Échec d'inscription";
			session.setAttribute("erreurs", erreurs);
			session.setAttribute("resultat", resultat);
		}
		request.getRequestDispatcher("/articleAdmin.jsp").forward(request, response);
	}
	
	/*private void doAjouterArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		HttpSession session = request.getSession(true);
		
		String designation = request.getParameter("nom");
		int prixUnitaire = Integer.parseInt(request.getParameter("pU"));
		int quantité = Integer.parseInt(request.getParameter("qte"));
		int catégorie = Integer.parseInt(request.getParameter("cat"));
		
		String resultat;
		Map<String,String> erreurs = new HashMap<String,String>();
		
		if(erreurs.isEmpty()) {
			resultat = "Succès de l'inscription";
			
			session.setAttribute("BaseDonnéeArticle", this.cBDarticle.recupArticle());
			cBDarticle.ajouterArticle(new Article(designation, prixUnitaire, quantité, catégorie));
			request.getRequestDispatcher("/articleAdmin.jsp").forward(request, response);
			
		}else {
			resultat = "Échec d'inscription";
			session.setAttribute("erreurs", erreurs);
			session.setAttribute("resultat", resultat);
			// Redirection 
			request.getRequestDispatcher("/articleAdmin.jsp").forward(request, response);
		}
		
	}*/

	private void doInscription(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		HttpSession session = request.getSession(true);

		// sauvegarde du résultat de validation
		String resultat;
		Map<String,String> erreurs = new HashMap<String,String>();
		
		// Récupérer les valeurs des champs à partir du JSP
		String fname = request.getParameter("nom");
		String lname = request.getParameter("prenom");
		String adresse = request.getParameter("adr");
		String tel = request.getParameter("tel");
		int age = Integer.parseInt(request.getParameter("age"));
		String[] tabRadio = request.getParameterValues("sexe");
		String login = request.getParameter("login");
		String pwd = request.getParameter("pwd");
		String pwd2 = request.getParameter("pwd2");
		// récupérer la valeur du bouton radio
		String sexe = "";
		for(int i = 0; i < tabRadio.length; i++) {
			if(tabRadio[i] != null) {
				sexe = tabRadio[i];
			}
		}
		
		if(erreurs.isEmpty()) {
			resultat = "Succès de l'inscription";
			
			session.setAttribute("erreurs", erreurs);
			session.setAttribute("resultat", resultat);
			
			// INSERT INTO
			Compte c = new Compte(login,pwd);
			cBD.ajouterCompte(c);
			
			Users u = new Users(fname,lname,adresse,tel,age,sexe,cBD.idCompteUser(login, pwd));
			cBD.ajouterUsers(u);
			
			request.getRequestDispatcher("/connection.jsp").forward(request, response);
		}else {
			resultat = "Échec d'inscription";
			
			session.setAttribute("erreurs", erreurs);
			session.setAttribute("resultat", resultat);
			// Redirection 
			request.getRequestDispatcher("/inscription.jsp").forward(request, response);
		}
		
	}

	private void doDeconnection(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		HttpSession session = request.getSession(true); 
		session.invalidate();

		request.getRequestDispatcher("/Accueil.jsp").forward(request, response);
	}

	private void doConnection(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(300);

		String login = request.getParameter("login");
		String pwd = request.getParameter("mdp");

		List<String> pwdBDD = this.cBD.verifierCoordonnees(login);

		if(pwdBDD == null) {
			request.getRequestDispatcher("/connection.jsp").forward(request, response);
		}else {
			if(pwd.equals(pwdBDD.get(0))) {
				session.setAttribute("BaseDonnéeArticle", this.cBDarticle.recupArticle());
				session.setAttribute("BaseDonnée", this.cBD);
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
