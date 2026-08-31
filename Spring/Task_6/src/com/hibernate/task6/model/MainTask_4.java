package com.hibernate.task6.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MainTask_4 {

    public static void main(String[] args) {

        Configuration configuration = new Configuration()
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(UserDetails.class)
                .addAnnotatedClass(Friend.class)
                .addAnnotatedClass(Post.class)
                .configure("hibernate.cfg.xml");

        SessionFactory factory = configuration.buildSessionFactory();

        // Add a user with details
        Session session1 = factory.openSession();
        Transaction transaction1 = session1.beginTransaction();

        User user = new User("Ahmed", 25);
        UserDetails details =new UserDetails("Cairo", "01012345678");

        user.setUserDetails(details);
        session1.persist(user);

        transaction1.commit();
        session1.close();

        Long userId = user.getId();

        // LAZY: details are accessed while the session is open
        Session session2 = factory.openSession();
        Transaction transaction2 = session2.beginTransaction();

        User lazyUser = session2.get(User.class, userId);

        System.out.println("User name: " + lazyUser.getName());
        System.out.println("Lazy address: " + lazyUser.getUserDetails().getAddress());

        transaction2.commit();
        session2.close();

        // EAGER: JOIN FETCH loads the user and details together
        Session session3 = factory.openSession();
        Transaction transaction3 = session3.beginTransaction();

        User eagerUser = session3.createQuery(
                        "select u from User u "
                                + "join fetch u.userDetails "
                                + "where u.id = :id",
                        User.class)
                .setParameter("id", userId)
                .getSingleResult();

        System.out.println("User name: " + eagerUser.getName());
        System.out.println(
                "Eager address: "
                        + eagerUser.getUserDetails().getAddress());

        transaction3.commit();
        session3.close();

        factory.close();
    }
}
