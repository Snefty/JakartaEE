package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Categorie {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idCategorie;
	private String designationCategorie;

	public int getIdCategorie() {
		return idCategorie;
	}
	
	public String getDesignationCategorie() {
		return designationCategorie;
	}

	public void setDesignationCategorie(String designationCategorie) {
		this.designationCategorie = designationCategorie;
	}

	@Override
	public String toString() {
		return "Categorie [idCategorie=" + idCategorie + ", designationCategorie=" + designationCategorie + "]";
	}
	
}