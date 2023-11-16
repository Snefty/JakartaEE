package fp;

public class Compte {
	private String login;
	private String pwd;
	private String type = "s";
	
	
	public Compte(String login, String pwd) {
		super();
		this.login = login;
		this.pwd = pwd;
		this.type = "s";
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

}
