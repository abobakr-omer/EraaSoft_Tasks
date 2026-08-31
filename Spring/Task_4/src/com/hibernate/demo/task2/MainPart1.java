package com.hibernate.demo.task2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MainPart1 {
	
	
public static void main(String[] args) {
		
		Configuration configuration = new Configuration()
											.addAnnotatedClass(Doctor.class)
											.addAnnotatedClass(DoctorDetails.class)
											.configure("hibernate.cfg.xml");
		
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		
		transaction.begin();

	    Doctor doctor = new Doctor("doctor_ahmed", 15000);
	    DoctorDetails doctorDetails = new DoctorDetails("Cairo, Egypt", "Ahmed", "Mohamed", 35);

	    session.save(doctor);
        session.save(doctorDetails);	 
			
		
        transaction.commit();
		
		session.close();
		factory.close();
        
	
	}

}
