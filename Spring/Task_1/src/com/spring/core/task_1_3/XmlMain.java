package com.spring.core.task_1_3;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlMain {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("task_1_3_applicationContext.xml");

        PersonService personService =
                context.getBean("personService", PersonService.class);

        personService.save("Bakr from XML");

        context.close();
    }
}
