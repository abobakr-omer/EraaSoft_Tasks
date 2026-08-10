package com.spring.core.task_1_2;

import org.springframework.stereotype.Component;

@Component
public class PersonService implements UserService {

    @Override
    public void save(String name) {
        System.out.println("Person saved: " + name);
    }
}
