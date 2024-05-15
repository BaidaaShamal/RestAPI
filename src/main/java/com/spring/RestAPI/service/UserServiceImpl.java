package com.spring.RestAPI.service;


import com.spring.RestAPI.entity.User;
import com.spring.RestAPI.entity.UserModel;
import com.spring.RestAPI.exceptions.ItemExistsException;
import com.spring.RestAPI.exceptions.ResourceNotFoundException;
import com.spring.RestAPI.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User createUser(UserModel user) {
		if(userRepo.existsByEmail(user.getEmail())){
			throw new ItemExistsException("User is already registered with email:"+user.getEmail());
		}
		User newUser = new User();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		BeanUtils.copyProperties(user, newUser);
		return userRepo.save(newUser);

	}

	@Override
	public User readUser() {
		Long userId=getLoggedInUser().getId();
		return userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException(("user not found for id:"+userId)));
	}

	@Override
	public User updateUser(UserModel user) {
		User existingUser = readUser();
		existingUser.setName(user.getName() != null ? user.getName() : existingUser.getName());
		existingUser.setEmail(user.getEmail() != null ? user.getEmail() : existingUser.getEmail());
		existingUser.setPassword(user.getPassword() != null ? passwordEncoder.encode(user.getPassword()) : existingUser.getPassword());
		existingUser.setAge(user.getAge() != null ? user.getAge() : existingUser.getAge());
		return userRepo.save(existingUser);
	}

	@Override
	public void deleteUser() {
		User existingUser = readUser();
		userRepo.delete(existingUser);
	}

	@Override
	public User getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();

		return userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found for the email"+email));
	}




}

























