package ioc.controller;

import ioc.annoation.AutoWired;
import ioc.service.UserService;

import java.util.Date;

/**
 * @author number47
 * @date 2020/6/19 00:38
 * @description
 */
public class UserController {

	@AutoWired
	private UserService userService;

	public UserService getUserService() {
		int i = 1;
		int j = 1;
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(new Date());
		}
	}

}
