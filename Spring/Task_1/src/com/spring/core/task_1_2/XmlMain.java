package com.spring.core.task_1_2;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlMain {

    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("task_1_2_applicationContext.xml")) {

            AccountService accountService =
                    context.getBean("accountService", AccountService.class);

            accountService.getSavePerson("Bakr from XML");
        }
    }
}
