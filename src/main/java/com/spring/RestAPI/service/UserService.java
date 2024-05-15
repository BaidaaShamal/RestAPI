package com.spring.RestAPI.service;


import com.spring.RestAPI.entity.User;
import com.spring.RestAPI.entity.UserModel;

public interface UserService {
	
	User createUser(UserModel user);
	/*User readUser(Long id);
	User updateUser(UserModel user ,Long id);
	void deleteUser(Long id);*/
	User readUser();
	User updateUser(UserModel user );
	void deleteUser();
	User getLoggedInUser();
}
