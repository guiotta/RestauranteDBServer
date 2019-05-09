package com.otta.restaurantDbServer.user.facade;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.otta.restaurantDbServer.database.entity.Role;
import com.otta.restaurantDbServer.database.entity.User;
import com.otta.restaurantDbServer.role.repository.RoleRepository;
import com.otta.restaurantDbServer.user.repository.UserRepository;

@Component
public class UserFacade {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(createRolesForNewUser());
		user.setActive(true);
		
		userRepository.save(user);
	}
	
	public List<User> findByName(String name) {
		return userRepository.findByName(name);
	}
	
	private Set<Role> createRolesForNewUser() {
		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByRole("USER"); 
		roles.add(userRole);
		
		return roles;
	}
}
