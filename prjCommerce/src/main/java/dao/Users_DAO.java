package dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import model.Compte;

public class Users_DAO {
	
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
	
	public Compte verifierCoordonée(String login, String pwd){
		init();
		Criteria criteria = session.createCriteria(Compte.class);
		criteria.add(Restrictions.like("login", login));
		criteria.add(Restrictions.like("pwd", pwd));
		
		Compte cpt = (Compte) criteria.list();
		
		tr.commit();
		session.close();
		sf.close();
		
		return cpt;
	}
	
}
