package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Compte {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idCompte;
	private String login;
	private String pwd;
	private String type;
	
	public int getIdCompte() {
		return idCompte;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		return "Compte [idCompte=" + idCompte + ", login=" + login + ", pwd=" + pwd + ", type=" + type + "]";
	}

}