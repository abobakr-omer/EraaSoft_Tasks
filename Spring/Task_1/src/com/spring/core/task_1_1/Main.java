package com.spring.core.task_1_1;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext applicationContext=
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		
		UserService userService1=applicationContext.getBean("personService",PersonService.class);
		UserService userService2=applicationContext.getBean("mangerService",MangerService.class);
		
		userService1.save("bakr");
		userService1.update("ahmed");
		
		userService2.save("mohamed");
		userService2.update("ibrahim");
		
		
		
		AppUserService appUserService1=new AppUserService(new PersonService());
		AppUserService appUserService2=new AppUserService(new MangerService());
		
		appUserService1.runSave("osama");
		appUserService1.runUpdate("hossam");
		
		appUserService2.runSave("mostafa");
		appUserService2.runUpdate("khaled");

		applicationContext.close();
		
		
	}
	
}
