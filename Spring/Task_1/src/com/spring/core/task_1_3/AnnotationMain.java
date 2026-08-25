package com.spring.core.task_1_3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SpringConfig.class);

        UserService personService = context.getBean(PersonService.class);
        personService.save("Bakr from annotation container");

        context.close();
    }
}
