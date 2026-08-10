package com.spring.core.task_1_1;

import org.springframework.stereotype.Component;

@Component
public class PersonService implements UserService{

	@Override
	public void save(String name) {
		// TODO Auto-generated method stub
		System.out.println("Person saved: " + name);
	}

	@Override
	public void update(String name) {
		// TODO Auto-generated method stub
		System.out.println("Person updated: " + name);
	}

}
