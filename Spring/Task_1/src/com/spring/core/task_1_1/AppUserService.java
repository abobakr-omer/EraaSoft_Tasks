package com.spring.core.task_1_1;

public class AppUserService {

	private UserService userService=null;
	
	public AppUserService(UserService userService) {
		this.userService=userService;
	}
	
	public void runSave(String name) {
		userService.save(name);
	}
	
	public void runUpdate(String name) {
		userService.update(name);
	}
	
	
}
