package fp;

public class Categorie {

	private int idCategorie;
	private String designationCategorie;
	
	public Categorie(int idCat, String des) {
		this.designationCategorie = des;
		this.idCategorie = idCat;
	}

	public int getIdCategorie() {
		return idCategorie;
	}

	public String getDesignationCategorie() {
		return designationCategorie;
	}
}
