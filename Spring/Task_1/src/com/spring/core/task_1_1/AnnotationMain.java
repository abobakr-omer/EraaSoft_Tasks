package com.spring.core.task_1_1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SpringConfig.class);

        UserService personService = context.getBean(PersonService.class);
        UserService mangerService = context.getBean(MangerService.class);

        personService.save("Bakr");
        personService.update("Ahmed");

        mangerService.save("Mohamed");
        mangerService.update("Ibrahim");

        context.close();
    }
}
