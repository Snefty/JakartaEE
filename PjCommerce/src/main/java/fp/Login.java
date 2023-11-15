package fp;

public class Login {
	
	private String login;
	private String pwd;
	private String action;
	
	public Login(String login, String pwd, String action) {
		super();
		this.login = login;
		this.pwd = pwd;
		this.action = action;
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
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
}
