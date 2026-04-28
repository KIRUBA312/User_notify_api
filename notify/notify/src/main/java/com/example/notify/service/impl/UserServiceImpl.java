package com.example.notify.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.notify.dao.UserRepository;
import com.example.notify.entity.User;
import com.example.notify.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		return userRepo.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		return userRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found"));
	}

	@Override
	public User updateUser(Long id, User user) {
		// TODO Auto-generated method stub
		User existingUser = userRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found"));
		existingUser.setUsername(user.getUsername());
		existingUser.setEmail(user.getEmail());
		
		return userRepo.save(existingUser);
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		userRepo.deleteById(id);
		
	}
	

}
