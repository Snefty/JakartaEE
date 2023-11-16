package fp;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectionBD {

	private final String URL = "jdbc:mysql://localhost:3306/prjCommerce"; 
	private final String LOGIN = "root"; 
	private final String MDP = "";

	private Connection cn = null;
	private Statement st = null;
	private String sql = "";
	private ResultSet rs = null;
	PreparedStatement ps = null;
	
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
	
	public List<String> verifierCoordonnees(String login) {
		List<String> lt = new ArrayList<>();
		etablirConnexion();
		sql = "SELECT pwd, type FROM compte WHERE login LIKE '" + 
				login + "'";
		try {
			rs = st.executeQuery(sql);
			if(rs.next()) {
				lt.add(rs.getString(1));
				lt.add(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lt;
	}
	
	public void ajouterCompte(Compte cp) throws SQLException {
		etablirConnexion();
		
		sql = "INSERT INTO compte(login,pwd,type) VALUES('"+ cp.getLogin() + "','" + cp.getPwd() + "','" + cp.getType() + "');";
		ps = cn.prepareStatement(sql);
		ps.execute();
		cloturerConnexion();
	}
	
	public void ajouterUsers(Users u) throws SQLException {
		etablirConnexion();
		sql = "INSERT INTO users(fname,lname,adresse,tel,age,sexe,idCompte) "
				+ "VALUES('" + u.getfName() + "','" + u.getlName() +
				"','" + u.getAdresse() + "','" + u.getTel() + "'," +
				u.getAge() + ",'" + u.getSexe() + "','" + u.getIdCompte() + "');";
		ps = cn.prepareStatement(sql);
		ps.execute();
		cloturerConnexion();
	}
	
	public int idCompteUser(String login, String mdp) throws SQLException {
		etablirConnexion();
		int id = 0;
		sql = "SELECT idCompte "
				+ "FROM compte "
				+ "WHERE login LIKE '"+ login + "' "
						+ "AND pwd LIKE '"+ mdp +"';";
		rs = st.executeQuery(sql);
		if(rs.next()) {
			id = rs.getInt(1);
		}
		cloturerConnexion();
		return id;
	}

	public List<Categorie> afficherCategorie() throws SQLException{
		List<Categorie> cat = new ArrayList<Categorie>();
		etablirConnexion();

		sql = "Select * FROM categorie;";
		rs = st.executeQuery(sql);

		while(rs.next()) {
			cat.add(new Categorie(rs.getInt("idCategorie"), rs.getString("designationCategorie")));
		}

		cloturerConnexion();
		return cat;
	}
}
