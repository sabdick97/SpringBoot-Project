package edu.cms.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;

import edu.cms.entity.User;
import edu.cms.repository.UserRepository;
import edu.cms.service.UserService;

public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	

	@Override
	public User login(String userName, String password) {
		
		return userRepository.findByUserNameAndPassword(userName, password);
	}

	
}
