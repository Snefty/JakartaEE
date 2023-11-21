package controleur;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import model.Article;
import model.Compte;
import model.Users;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

		if(flag.equalsIgnoreCase("connect")) {
			try {
				this.doConnection(request,response); 
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}else if(flag.equalsIgnoreCase("inscrit")) {
			try {
				this.doInscription(request,response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}else if(flag.equalsIgnoreCase("deconnect")) {
			try {
				this.doDeconnect(request,response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}else if(flag.equalsIgnoreCase("creaArticle")) {
			try {
				this.doCreerArticle(request,response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void doCreerArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession(true);

		String designation = request.getParameter("nom");
		int prixUnitaire = Integer.parseInt(request.getParameter("pU"));
		int quantité = Integer.parseInt(request.getParameter("qte"));
		int catégorie = Integer.parseInt(request.getParameter("cat"));

		String resultat;
		Map<String,String> erreurs = new HashMap<String,String>();

		if(erreurs.isEmpty()) {
			resultat = "Succès de l'inscription";

			
			
			Article art = new Article();
			art.setDesignation(designation);
			art.setpU(prixUnitaire);
			art.setQte(quantité);
			art.setIdCategorie(catégorie);
			
			this.uDAO.ajouterArticle(art);
			
			request.setAttribute("BaseDonnée", this.uDAO);
		}else {
			resultat = "Échec d'inscription";
			request.setAttribute("erreurs", erreurs);
			request.setAttribute("resultat", resultat);
		}
		request.getRequestDispatcher("/articleAdmin.jsp").forward(request, response);
	}

	private void doDeconnect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession(true);
		session.invalidate();

		response.sendRedirect("/accueil.jsp");
	}

	private void doInscription(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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

			this.uDAO.init();

			// INSERT INTO
			Users u = new Users();
			u.setfName(fname);
			u.setlName(lname);
			u.setAdresse(adresse);
			u.setAge(age);
			u.setSexe(sexe);
			u.setTel(tel);
			this.uDAO.ajouterUsers(u);

			Compte c = new Compte();
			c.setLogin(login);
			c.setPwd(pwd2);
			c.setType("s");
			this.uDAO.ajouterCompte(c);

			Set<Compte> lCompte = new HashSet<Compte>();
			lCompte.add(c);

			u.setComptes(lCompte);
			this.uDAO.commitAndClose();

			request.getRequestDispatcher("/connection.jsp").forward(request, response);
		}else {
			resultat = "Échec d'inscription";

			session.setAttribute("erreurs", erreurs);
			session.setAttribute("resultat", resultat);
			// Redirection 
			request.getRequestDispatcher("/inscription.jsp").forward(request, response);
		}
	}

	private void doConnection(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(500);

		String login = request.getParameter("login");
		String pwd = request.getParameter("mdp");

		Compte cpt = this.uDAO.verifierCoordonée(login,pwd);

		if(cpt == null) {
			request.setAttribute("LogImpo", "Login ou mot de passe inconnue !!");
			request.getRequestDispatcher("/connection.jsp").forward(request, response);
		}else {

			if(cpt.getType().equals("a")) {

				session.setAttribute("login", cpt);
				request.setAttribute("BaseDonnée", this.uDAO);

				request.getRequestDispatcher("/articleAdmin.jsp").forward(request, response);
			}else {
				session.setAttribute("login", cpt);

				request.getRequestDispatcher("/articleSimple.jsp").forward(request, response);
			}

		}
	}

}
