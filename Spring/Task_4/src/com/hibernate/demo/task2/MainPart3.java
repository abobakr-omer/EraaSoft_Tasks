package com.hibernate.demo.task2;

import java.util.Arrays;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MainPart3 {

public static void main(String[] args) {
		
		Configuration configuration = new Configuration()
										.addAnnotatedClass(Hospital.class)
										.addAnnotatedClass(Doctor.class)
										.addAnnotatedClass(DoctorDetails.class)
										.addAnnotatedClass(Patient.class)
										.configure("hibernate.cfg.xml");
		
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		
		transaction.begin();
		
		Hospital hospital = new Hospital("Al Salam Hospital", 1, 3);

		Doctor doctor1 = new Doctor("doctor_ahmed", 15000);
		
		
		Patient patient1 = new Patient("Mohamed", "Diabetes");
		Patient patient2 = new Patient("Sara", "High blood pressure");
		Patient patient3 = new Patient("Ali", "Asthma");

		doctor1.setHospital(hospital);
		hospital.setDoctors(Arrays.asList(doctor1));
		
		patient1.setDoctor(doctor1);
		patient2.setDoctor(doctor1);
		patient3.setDoctor(doctor1);

		doctor1.setPatients(Arrays.asList(patient1, patient2, patient3));
		
		session.save(hospital);
		session.save(doctor1);
		session.save(patient1);
		session.save(patient2);
		session.save(patient3);
		
		transaction.commit();
		
		session.close();
		factory.close();
	
	
}
}
