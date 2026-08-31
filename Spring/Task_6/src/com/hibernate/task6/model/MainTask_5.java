package com.hibernate.task6.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MainTask_5 {

    public static void main(String[] args) {

        Configuration configuration = new Configuration()
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Admin.class)
                .addAnnotatedClass(Customer.class)
                .configure("hibernate.cfg.xml");

        SessionFactory factory = configuration.buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Admin admin = new Admin("Ahmed", 30, "Manager");
        Customer customer = new Customer("Mohamed", 25, "Cairo");

        session.persist(admin);
        session.persist(customer);

        transaction.commit();
        session.close();

        Session session2 = factory.openSession();
        Transaction transaction2 = session2.beginTransaction();

        // A parent query returns User, Admin, and Customer objects.
        List<User> users = session2
                .createQuery("from User", User.class)
                .getResultList();

        users.forEach(user -> System.out.println(
                user.getClass().getSimpleName()
                        + ": " + user.getName()
                        + ", age " + user.getAge()));

        transaction2.commit();
        session2.close();
        factory.close();
    }
}
