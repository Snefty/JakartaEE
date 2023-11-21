package model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Article {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idArticle;
	private String designation;
	private int pU;
	private int qte;
	private int idCategorie;
	
	public int getIdArticle() {
		return idArticle;
	}

	public String getDesignation() {
		return designation;
	}

	public int getpU() {
		return pU;
	}

	public int getQte() {
		return qte;
	}

	public int getIdCategorie() {
		return idCategorie;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public void setpU(int pU) {
		this.pU = pU;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}

	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	@Override
	public String toString() {
		return "Article [idArticle=" + idArticle + ", designation=" + designation + ", pU=" + pU + ", qte=" + qte
				+ ", idCategorie=" + idCategorie + "]";
	}
	
}