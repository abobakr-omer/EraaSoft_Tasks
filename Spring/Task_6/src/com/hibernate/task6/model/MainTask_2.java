package com.hibernate.task6.model;

import java.util.Arrays;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MainTask_2 {

	
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
		
		
		/*
		 * Add user with user details
		 */

		User user1 = new User("Ahmed", 25);

		UserDetails userDetails = new UserDetails("Cairo", "01012345678");

		// Set both sides
		user1.setUserDetails(userDetails);
		userDetails.setUser(user1);

		// No cascade, so persist both entities
		session.persist(user1);
		session.persist(userDetails);


		/*
		 * Add user with friends
		 */

		User user2 = new User("Mohamed", 30);

		Friend friend1 = new Friend("Ali");
		Friend friend2 = new Friend("Omar");

		// Set both sides
		user2.setFriends(Arrays.asList(friend1, friend2));

		friend1.setUsers(Arrays.asList(user2));
		friend2.setUsers(Arrays.asList(user2));

		// Persist every entity because there is no cascade
		session.persist(friend1);
		session.persist(friend2);
		session.persist(user2);


		/*
		 * Add user with posts
		 */

		User user3 = new User("Sara", 22);

		Post post1 =
		        new Post("First Post", "Hello everyone");

		Post post2 =
		        new Post("Hibernate", "Learning Hibernate relationships");

		// Set both sides
		user3.setPosts(Arrays.asList(post1, post2));

		post1.setUser(user3);
		post2.setUser(user3);

		// Persist every entity because there is no cascade
		session.persist(user3);
		session.persist(post1);
		session.persist(post2);

		
		transaction.commit();
		
		
		session.close();
		factory.close();
		
		
		
		
	}
	
}
