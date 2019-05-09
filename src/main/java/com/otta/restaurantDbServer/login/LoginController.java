package com.otta.restaurantDbServer.login;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.otta.restaurantDbServer.database.entity.User;
import com.otta.restaurantDbServer.user.facade.UserFacade;

@RestController
public class LoginController {
	@Autowired
	private UserFacade userFacade;

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		Optional<User> userExists = userFacade.findByName(user.getName()).stream().findFirst();
		if (userExists.isPresent()) {
			bindingResult.rejectValue("name", "error.user",
					"There is already a user registered with the name provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userFacade.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");

		}
		return modelAndView;
	}

	@RequestMapping(value = "/user/userHome", method = RequestMethod.GET)
	public ModelAndView user() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userFacade.findByName(auth.getName()).stream().findFirst().get();
		modelAndView.addObject("userName", "Welcome " + user.getName());
		modelAndView.addObject("userMessage", "This Page is available to Users with User Role");
		modelAndView.setViewName("user/userHome");
		return modelAndView;
	}
}
