package com.hibernate.demo.task2;

import java.util.Arrays;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MainPart2 {

public static void main(String[] args) {
		
		Configuration configuration = new Configuration()
											.addAnnotatedClass(Doctor.class)
											.addAnnotatedClass(Hospital.class)
											.configure("hibernate.cfg.xml");
		
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		
		transaction.begin();
		
		
		Hospital hospital = new Hospital("Al Salam Hospital", 3, 100);

		Doctor doctor1 = new Doctor("doctor_ahmed", 15000);
		Doctor doctor2 = new Doctor("doctor_mohamed", 18000);
		Doctor doctor3 = new Doctor("doctor_sara", 20000);
		
		doctor1.setHospital(hospital);
		doctor2.setHospital(hospital);
		doctor3.setHospital(hospital);
		
		hospital.setDoctors(Arrays.asList(doctor1,doctor2,doctor3));
		
		session.save(hospital);
		session.save(doctor1);
		session.save(doctor2);
		session.save(doctor3);
		
		transaction.commit();
		
		session.close();
		factory.close();
}
	
	
}
