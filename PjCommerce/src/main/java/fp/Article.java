package fp;

public class Article {
	private int idArticle;
	private String designation;
	private int pU;
	private int qte;
	private int idCategorie;
	private String nameCategorie;
	
	public Article(int idArticle,String designation, int pU, int qte, int idCategorie, String nameCategorie) {
		super();
		this.idArticle = idArticle;
		this.designation = designation;
		this.pU = pU;
		this.qte = qte;
		this.idCategorie = idCategorie;
		this.nameCategorie = nameCategorie;
	}

	public Article(String designation, int pU, int qte, int idCategorie, String nameCategorie) {
		this(0,designation,pU,qte,idCategorie,nameCategorie);
	}
	
	public Article(String designation, int pU, int qte, int idCategorie) {
		this(0,designation,pU,qte,idCategorie,null);
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

	public String getNameCategorie() {
		return nameCategorie;
	}
	
}
