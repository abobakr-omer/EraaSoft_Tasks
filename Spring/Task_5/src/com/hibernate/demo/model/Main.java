package com.hibernate.demo.model;

import java.util.Arrays;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {

	
	public static void main(String[] args) {
		
		Configuration configuration = new Configuration()
											.addAnnotatedClass(Book.class)
											.addAnnotatedClass(Author.class)
											.configure("hibernate.cfg.xml");
		
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		
		transaction.begin();
		
		Book book1 = new Book("Clean Code");
		Book book2 = new Book("Effective Java");

		Author author1 = new Author("Ahmed");
		Author author2 = new Author("Mohamed");
		
		book1.setAuthors(Arrays.asList(author1,author2));
		book2.setAuthors(Arrays.asList(author2));
		
		author1.setBooks(Arrays.asList(book1));
		author2.setBooks(Arrays.asList(book1, book2));
		
		
		session.persist(book1);
		session.persist(book2);
		
		
		
		
		transaction.commit();
		
		
		session.close();
		factory.close();
		
		
		
		
	}
	
}
