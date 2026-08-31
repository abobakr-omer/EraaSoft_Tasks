package com.hibernate.task6.model;

import java.util.Arrays;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MainTask_3 {

	
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
		
		user1.setUserDetails(userDetails);
		session.persist(user1);


		/*
		 * Add user with friends
		 */

		User user2 = new User("Mohamed", 30);

		Friend friend1 = new Friend("Ali");
		Friend friend2 = new Friend("Omar");

		user2.setFriends(Arrays.asList(friend1, friend2));
		
		session.persist(user2);
		
		



		/*
		 * Add user with posts
		 */

		User user3 = new User("Sara", 22);

		Post post1 =
		        new Post("First Post", "Hello everyone");

		Post post2 =
		        new Post("Hibernate", "Learning Hibernate relationships");

		user3.setPosts(Arrays.asList(post1, post2));

		session.persist(user3);
		
		
		
		transaction.commit();
		
		
		session.close();
		factory.close();
		
		
		
		
	}
	
}
