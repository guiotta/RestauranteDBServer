package com.otta.restaurantDbServer.vote.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.otta.restaurantDbServer.database.entity.Election;
import com.otta.restaurantDbServer.database.entity.Restaurant;
import com.otta.restaurantDbServer.database.entity.User;
import com.otta.restaurantDbServer.database.entity.Vote;
import com.otta.restaurantDbServer.election.facade.ElectionFacade;
import com.otta.restaurantDbServer.restaurant.facade.RestaurantFacade;
import com.otta.restaurantDbServer.restaurant.service.RestaurantService;
import com.otta.restaurantDbServer.user.facade.UserFacade;
import com.otta.restaurantDbServer.vote.facade.VoteFacade;

@RestController
public class VoteController {
	@Autowired
	private UserFacade userFacade;
	@Autowired
	private RestaurantFacade restaurantFacade;
	@Autowired
	private ElectionFacade electionFacade;
	@Autowired
	private VoteFacade voteFacade;
	@Autowired
	private RestaurantService restaurantService;
	
	@RequestMapping(value = "/vote/voteHome", method = RequestMethod.GET)
	public ModelAndView goToVoteHome() {
		ModelAndView modelAndView = new ModelAndView();
		Vote vote = new Vote();
		modelAndView.addObject("vote", vote);
		List<Restaurant> availableRestaurants = restaurantService.build();
		modelAndView.addObject("availableRestaurants", availableRestaurants);
		modelAndView.setViewName("vote/voteHome");
		return modelAndView;
	}

	@RequestMapping(value = "/vote/voteHome", method = RequestMethod.POST)
	public ModelAndView vote(@Valid Vote vote, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userFacade.findByName(auth.getName()).stream().findFirst().get();
		Restaurant restaurantExists = vote.getRestaurant();
		Election election = selectElectionForToday();
		Vote voteExists = voteFacade.findByElectionAndUser(election, user);
		
		if (voteExists != null) {
			bindingResult.rejectValue("user", "error.user",
					"There is already a Vote registered with the logged user");
		}
		if (restaurantExists == null) {
			bindingResult.rejectValue("restaurant", "error.user",
					"Could not find a valid restaurant for selection.");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("vote/voteHome");
		} else {			
			voteFacade.saveVote(vote, election, restaurantExists, user);
			modelAndView.addObject("userMessage", "User has voted successfully");
			modelAndView.setViewName("vote/voteHome");
		}
		
		List<Restaurant> availableRestaurants = restaurantFacade.listAllAvailableRestaurants();
		modelAndView.addObject("userName", "Welcome " + user.getName());
		modelAndView.addObject("availableRestaurants", availableRestaurants);
		return modelAndView;
	}
	
	private Election selectElectionForToday() {
		if (!electionFacade.hasElectionForDate(LocalDate.now())) {
			electionFacade.createAndSaveNewElection();
		}
		return electionFacade.findElectionByDate(LocalDate.now());
	}
}
