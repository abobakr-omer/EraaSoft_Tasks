package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.model.Teacher;

public class Main {
	
	public static void main(String[] args) {
		
		
		Configuration configuration = new Configuration()
										.addAnnotatedClass(Teacher.class)
										.configure("hibernate.cfg.xml");
		
		SessionFactory factory = configuration.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		Transaction transaction = session.getTransaction();
		
		transaction.begin();
		
		
		
		
	/*	 // Here use Create --> <property name="hbm2ddl.auto">create</property>
	 
	 
	   Teacher teacher1 = new Teacher("Ahmed", 15, "Cairo");
		Teacher teacher2 = new Teacher("Mohamed", 16, "Alexandria");
		Teacher teacher3 = new Teacher("Sara", 17, "Giza");
		Teacher teacher4 = new Teacher("Mona", 18, "Mansoura");
		Teacher teacher5 = new Teacher("Omar", 20, "Aswan");
		
		
		session.save(teacher1);
		session.save(teacher2);
		session.save(teacher3);
		session.save(teacher4);
		session.save(teacher5); */
		
		// Here use Update --> <property name="hbm2ddl.auto">update</property>
		Teacher teacher = session.get(Teacher.class, 1L);
		session.detach(teacher);
		teacher.setAddress("Zag");
		session.update(teacher);
		session.delete(teacher); 
		
		transaction.commit();
		
		session.close();
		factory.close();
		
		
		
		
	}

}
