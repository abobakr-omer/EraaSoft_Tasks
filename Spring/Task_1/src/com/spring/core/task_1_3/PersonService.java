package com.spring.core.task_1_3;

import org.springframework.stereotype.Component;

@Component
public class PersonService implements UserService {

    public void init() {
        System.out.println("PersonService initialized");
    }

    @Override
    public void save(String name) {
        System.out.println("Person saved: " + name);
    }

    public void destroy() {
        System.out.println("PersonService destroyed");
    }
}
