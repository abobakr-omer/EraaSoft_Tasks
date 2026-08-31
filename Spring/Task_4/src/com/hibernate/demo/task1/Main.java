package com.hibernate.demo.task1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {

	
	public static void main(String[] args) {
		
		Configuration configuration = new Configuration()
											.addAnnotatedClass(Player.class)
											.configure("hibernate.cfg.xml");
		
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		
		transaction.begin();
		
		// Save
		Player player1 = new Player("Mohamed", 7, true);
		Player player2 = new Player("Ahmed", 9, false);
		Player player3 = new Player("bakr", 8, true);
		
		session.save(player1);
		session.save(player2);
		session.save(player3);
		
		// Get by generated ID
		Player player = session.get(Player.class,player1.getId());
		player.setAge(6);

		// Update
		session.update(player);
		
		 // Delete a different player
		session.delete(player3);
		
		transaction.commit();
		
		
		session.close();
		factory.close();
		
		
		
		
	}
	
}
