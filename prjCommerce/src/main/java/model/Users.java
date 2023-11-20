package model;

import javax.persistence.*;

@Entity
public class Users {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idUsers;
	private String fName;
	private String lName;
	private String adresse;
	private String tel;
	private int age;
	private String sexe;
	private int idCompte;
	
	public Users(int idUsers, String fName, String lName, String adresse, String tel, int age, String sexe, int idCompte) {
		this.idUsers = idUsers;
		this.fName = fName;
		this.lName = lName;
		this.adresse = adresse;
		this.tel = tel;
		this.age = age;
		this.sexe = sexe;
		this.idCompte = idCompte;
	}
	
	public int getIdUsers() {
		return idUsers;
	}

	public void setIdCompte(int idCompte) {
		this.idCompte = idCompte;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public int getIdCompte() {
		return idCompte;
	}
	
}