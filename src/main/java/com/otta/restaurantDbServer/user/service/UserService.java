package com.otta.restaurantDbServer.user.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.otta.restaurantDbServer.database.entity.User;
import com.otta.restaurantDbServer.user.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "Estou retornando.";
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> listAllUsers() {
		return StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void saveUser(@RequestBody User user) {
		userRepository.save(user);
	}
}
