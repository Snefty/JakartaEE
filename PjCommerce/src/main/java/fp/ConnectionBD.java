package fp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionBD {

	private final String URL = "jdbc:mysql://localhost:3306/prjCommerce"; 
	private final String LOGIN = "root"; 
	private final String MDP = "";

	private Connection cn = null;
	private Statement st = null;
	private String sql = "";
	private ResultSet rs = null;
	
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
	
}
