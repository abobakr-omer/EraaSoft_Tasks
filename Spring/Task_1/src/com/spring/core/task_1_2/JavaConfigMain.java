package com.spring.core.task_1_2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigMain {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(
                             "com.spring.core.task_1_2")) {

            AccountService accountService = context.getBean(AccountService.class);
            accountService.getSavePerson("Bakr from Java config");
        }
    }
}
