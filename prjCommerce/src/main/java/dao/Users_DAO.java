package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import model.Article;
import model.Categorie;
import model.Compte;
import model.Users;

public class Users_DAO {
	
	Configuration configuration;
	SessionFactory sf;
	Session session;
	Transaction tr;
	
	public void init() {
		configuration = new Configuration().configure();
		sf = configuration.buildSessionFactory();
		session = sf.openSession();
		tr = session.beginTransaction();
	}
	
	public void commitAndClose() {
		if (tr.getStatus().equals(TransactionStatus.ACTIVE)) { 
		    tr.commit();
		}
		session.close();
		sf.close();
	}
	
	public void ajouterUsers(Users u) {
		tr.begin();
		session.persist(u);
	}
	
	public void ajouterCompte(Compte c) {
		session.persist(c);
	}
	
	public Compte verifierCoordonée(String login,String pwd){
		init();
		Criteria criteria = session.createCriteria(Compte.class);
		criteria.add(Restrictions.like("login", login));
		criteria.add(Restrictions.like("pwd", pwd));
		
		@SuppressWarnings("unchecked")
		List<Compte> cpt2 = (List<Compte>) criteria.list();
		
		commitAndClose();
		
		if(cpt2.isEmpty()) {
			return null;
		}else {
			return cpt2.get(0);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Article> afficherArticle(){
		init();
		Criteria criteria = session.createCriteria(Article.class);
	
		List<Article> art = (List<Article>) criteria.list();	
		
		for(Article a : art) {
			System.out.println(a + " /");
		}
		
		commitAndClose();
		return art;
	}
	
	public List<Categorie> afficherCategorie(){
		init();
		Criteria criteria = session.createCriteria(Categorie.class);
	
		@SuppressWarnings("unchecked")
		List<Categorie> cat = (List<Categorie>) criteria.list();	
		
		for(Categorie a : cat) {
			System.out.println(a + " /");
		}
		
		commitAndClose();
		return cat;
	}
	
	public void ajouterArticle(Article a) {
		tr.begin();
		session.persist(a);
	}
 }
