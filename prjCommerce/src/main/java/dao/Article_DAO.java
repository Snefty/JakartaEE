package dao;

import model.Article;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class Article_DAO {
	
	private Configuration configuration = new Configuration().configure();
	private SessionFactory sf = configuration.buildSessionFactory();
	private Session session = sf.openSession();
	private Transaction tr = session.beginTransaction();
	
	public void init() {
		 configuration = new Configuration().configure();
		 sf = configuration.buildSessionFactory();
		 session = sf.openSession();
		 tr = session.beginTransaction();
	}
	
	
	public void ajouterArticleHibernate(Article a) {
		init();
		session.persist(a);
		tr.commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<Article> afficherArticles(){
		List<Article> art = new ArrayList<Article>();
		
		init();
		
		Criteria criteria = session.createCriteria(Article.class);
		art = (List<Article>) criteria.list();
		
		tr.commit();
		session.close();
		sf.close();
		return art;
	}
}
