package com.spring.core.task_1_2;

import org.springframework.stereotype.Component;

@Component
public class AccountServiceImpl implements AccountService {

    private final UserService userService;

    public AccountServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void getSavePerson(String name) {
        userService.save(name);
    }
}
