package com.spring.RestAPI.controller;


import com.spring.RestAPI.entity.User;
import com.spring.RestAPI.entity.UserModel;
import com.spring.RestAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {


	@Autowired
	private UserService userService;


	@GetMapping("/profile")
	public ResponseEntity<User>get(){
		return new ResponseEntity<User>(userService.readUser(),HttpStatus.OK);
	}

	@PutMapping("/profile")
	public ResponseEntity<User> updateUser(@RequestBody UserModel user ) {
		return new ResponseEntity<User>(userService.updateUser(user), HttpStatus.OK);
	}

	@DeleteMapping("/deactivate")
	public ResponseEntity<HttpStatus> updateUser() {
		userService.deleteUser();
		return new ResponseEntity<HttpStatus>( HttpStatus.NO_CONTENT);
	}



}
