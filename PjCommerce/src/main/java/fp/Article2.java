package fp;

import javax.persistence.*;

@Entity
public class Article2 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idArticle;
	private String designation;
	private int pU;
	private int qte;
	private int idCategorie;
	
	public Article2(int idArticle,String designation, int pU, int qte, int idCategorie) {
		super();
		this.idArticle = idArticle;
		this.designation = designation;
		this.pU = pU;
		this.qte = qte;
		this.idCategorie = idCategorie;
	}

	public Article2(String designation, int pU, int qte, int idCategorie) {
		this(0,designation,pU,qte,idCategorie);
	}
	
	
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

}
