package model;

import java.util.Set;

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
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="idUsers")
	private Set<Compte> comptes;
	
	public Set<Compte> getComptes() {
		return comptes;
	}
	public void setComptes(Set<Compte> comptes) {
		this.comptes = comptes;
	}
	
	public int getIdUsers() {
		return idUsers;
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

	@Override
	public String toString() {
		return "Users [idUsers=" + idUsers + ", fName=" + fName + ", lName=" + lName + ", adresse=" + adresse + ", tel="
				+ tel + ", age=" + age + ", sexe=" + sexe + "]";
	}
	
}