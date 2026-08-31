package com.hibernate.demo.task2;

import java.util.Arrays;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MainPart4 {

	
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
		
		Hospital hospital1 = new Hospital("Al Salam Hospital", 1, 3);
		Hospital hospital2 = new Hospital("Al Shifa Hospital", 0, 3);

		Doctor doctor = new Doctor("doctor_ahmed", 15000);

		Patient patient1 = new Patient("Mohamed", "Diabetes");
		Patient patient2 = new Patient("Sara", "High blood pressure");
		Patient patient3 = new Patient("Ali", "Asthma");
		
		doctor.setHospital(hospital1);
		hospital1.setDoctors(Arrays.asList(doctor));

		patient1.setDoctor(doctor);
		patient2.setDoctor(doctor);
		patient3.setDoctor(doctor);
		doctor.setPatients(Arrays.asList(patient1, patient2, patient3));

		
		hospital1.setPatients(Arrays.asList(patient1,patient2,patient3));
		hospital2.setPatients(Arrays.asList(patient1,patient2,patient3));
		
		patient1.setHospitals(Arrays.asList(hospital1,hospital2));
		patient2.setHospitals(Arrays.asList(hospital1,hospital2));
		patient3.setHospitals(Arrays.asList(hospital1,hospital2));
		
		session.save(hospital1);
		session.save(hospital2);
		session.save(doctor);

		session.save(patient1);
		session.save(patient2);
		session.save(patient3);
		
		
		transaction.commit();
		
		session.close();
		factory.close();
		
		
		
		
		
}
	
	
}
