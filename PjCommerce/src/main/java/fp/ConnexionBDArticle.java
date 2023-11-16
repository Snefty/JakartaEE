package fp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class ConnexionBDArticle {

	private final String URL = "jdbc:mysql://localhost:3306/prjCommerce"; 
	private final String LOGIN = "root"; 
	private final String MDP = "";

	private Connection cn = null;
	private Statement st = null;
	private String sql = "";
	private ResultSet rs = null;
	PreparedStatement ps = null;

	private Configuration config = null;
	private SessionFactory sf = null;
	private Session session = null;
	private Transaction tr = null;

	public List<Article> recupArticle() throws SQLException{
		List<Article> art = new ArrayList<Article>();

		etablirConnexion();

		sql = "SELECT a.*, c.designationCategorie "
				+ "FROM article AS a JOIN categorie AS c "
				+ "ON a.idCategorie = c.idCategorie "
				+ "ORDER BY a.idArticle ASC;";
		rs = st.executeQuery(sql);

		while(rs.next()) {
			art.add(new Article(rs.getInt("idArticle"), rs.getString("designation"), rs.getInt("pu"),
					rs.getInt("qty"), rs.getInt("idCategorie") , rs.getString("designationCategorie")));
		}

		cloturerConnexion();
		return art;
	}

	public void ajouterArticle(Article e) throws SQLException {
		etablirConnexion();
		sql = "INSERT INTO article(designation, pu, qty, idCategorie) "
				+ "VALUES('" + e.getDesignation() + "',"
				+ "'" + e.getpU() + "',"
				+ "'" + e.getQte() + "',"
				+ "'" + e.getIdCategorie() + "'"
				+ ");";
		ps = cn.prepareStatement(sql);
		ps.execute();
		cloturerConnexion();
	}

	public void ajouterArticleHibernate(Article2 a) {
		init();
		session.persist(a);
		tr.commit();
		session.close();
		sf.close();
	}

	public void init() {
		config = new Configuration().configure();
		sf = config.buildSessionFactory();
		session = sf.openSession();
		tr = session.beginTransaction();
	}

	public Connection etablirConnexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection(
					URL,LOGIN,MDP);
			//System.out.println("Bravo!!! connexion réussie");
			st = cn.createStatement();
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return cn;
	}

	public void cloturerConnexion() {
		try {
			cn.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	public String getURL() {
		return URL;
	}

	public String getLOGIN() {
		return LOGIN;
	}

	public String getMDP() {
		return MDP;
	}

	public static void main(String[] args) throws SQLException {
		ConnexionBDArticle c = new ConnexionBDArticle();
		System.out.println(c.recupArticle());
	}

}
