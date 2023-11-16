package fp;

import java.io.IOException;

import jakarta.servlet.jsp.*;
import jakarta.servlet.jsp.tagext.*;

public class Login extends TagSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -424369596987319787L;
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
	
	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.print("<div class='container'>");
			out.print("<div class='card'>");
		
			out.print("<form method='POST' >");
			out.print("<fieldset style='margin: 35px;'>");
			out.print("<div style='text-align: center;'><h2>Connection</h2></div>");
						
			out.print("<div class='form-group'>");
							
			out.print("<label class='form-label mt-4'>Login</label>");
			out.print("<input type='text' class='form-control' id='login' name='login' placeholder='Enter login'>");
							
			out.print("</div>");

			out.print("<div class='form-group'>");
			out.print("<label class='form-label mt-4'>Password</label>");
			out.print("<input type='password' class='form-control' id='mdp' name='mdp' placeholder='Enter password'>");
			out.print("</div>");
						
			out.print("<br>");
			out.print("<br>");
			out.print("<input type='submit' class='btn btn-primary' value='submit'> ");
			out.print("<input type='reset' class='btn btn-primary' value='reset'>");
						
			out.print(" <div class='container aqua'> ");
			out.print("<!-- Structure if tertiaire -->");
			out.print("<p class='${empty erreurs ? 'succes' : 'erreur'}'>${resultat}</p>");
			out.print("</div>");
			out.print("</fieldset>");
			out.print("</form>");
			
			out.print("</div>");
			out.print("</div>");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 2;
	}
	@Override
	public int doEndTag() throws jakarta.servlet.jsp.JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.println("</form>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 4;
	}
	@Override
	public void release() {
		this.login = "";
		this.pwd = "";
		this.action = null;
	}
}
