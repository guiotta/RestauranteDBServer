package com.otta.restaurantDbServer.user.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.otta.restaurantDbServer.database.entity.Restaurant;
import com.otta.restaurantDbServer.database.entity.User;
import com.otta.restaurantDbServer.restaurant.facade.RestaurantFacade;
import com.otta.restaurantDbServer.user.facade.UserFacade;
import com.otta.restaurantDbServer.vote.bean.VoteRestaurantCounter;
import com.otta.restaurantDbServer.vote.utils.VotesClassifier;

@RestController
public class UserController {
	@Autowired
	private UserFacade userFacade;
	@Autowired
	private RestaurantFacade restaurantFacade;
	@Autowired
	private VotesClassifier classifier;

	@RequestMapping(value = "/user/userHome", method = RequestMethod.GET)
	public ModelAndView user() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userFacade.findByNameIgnoreCase(auth.getName());
		List<Restaurant> availableRestaurants = restaurantFacade.listAllAvailableRestaurants();
		List<VoteRestaurantCounter> todayVotes = classifier.classify(LocalDate.now());
		
		modelAndView.addObject("userName", "Welcome " + user.getName());
		modelAndView.addObject("availableRestaurants", availableRestaurants);
		modelAndView.addObject("todayVotes", todayVotes);
		modelAndView.setViewName("user/userHome");
		return modelAndView;
	}
}
