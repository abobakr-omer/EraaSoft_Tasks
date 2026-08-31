package com.hibernate.task6.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MainTask_1 {

	
	public static void main(String[] args) {
		
		Configuration configuration = new Configuration()
											.addAnnotatedClass(User.class)
											.addAnnotatedClass(UserDetails.class)
											.addAnnotatedClass(Friend.class)
											.addAnnotatedClass(Post.class)
											.configure("hibernate.cfg.xml");
		
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		
		transaction.begin();
		
		
		
		
		
		transaction.commit();
		
		
		session.close();
		factory.close();
		
		
		
		
	}
	
}
