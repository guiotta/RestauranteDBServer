package com.otta.restaurantDbServer.user.facade;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.otta.restaurantDbServer.database.entity.Role;
import com.otta.restaurantDbServer.database.entity.User;
import com.otta.restaurantDbServer.role.repository.RoleRepository;
import com.otta.restaurantDbServer.user.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserFacadeTest {
	private static final String NAME = "NAME";
	private static final String ROLE_NAME = "USER";
	private static final String PASSWORD = "password";

	@Mock
	private UserRepository userRepository;
	@Mock
	private RoleRepository roleRepository;
	@Mock
	private BCryptPasswordEncoder passwordEncoder;
	@InjectMocks
	private UserFacade facade;

	@Mock
	private Role role;
	@Mock
	private User user;

	@Test
	public void shouldEncodePasswordToSave() {
		// given
		when(roleRepository.findByRole(ROLE_NAME)).thenReturn(role);
		when(user.getPassword()).thenReturn(PASSWORD);
		// when
		facade.saveUser(user);
		// then
		verify(passwordEncoder).encode(PASSWORD);
	}

	@Test
	public void shouldSaveUser() {
		// given
		// when
		facade.saveUser(user);
		// then
		verify(userRepository).save(user);
	}

	@Test
	public void shouldFindUserByNameIgnoreCase() {
		// given
		when(userRepository.findByNameIgnoreCase(NAME)).thenReturn(user);
		// when
		User actualValue = facade.findByNameIgnoreCase(NAME);
		// then
		assertEquals(user, actualValue);
	}
}
