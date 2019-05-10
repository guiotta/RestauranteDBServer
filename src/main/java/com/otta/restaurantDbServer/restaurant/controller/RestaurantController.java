package com.otta.restaurantDbServer.restaurant.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.otta.restaurantDbServer.database.entity.Restaurant;
import com.otta.restaurantDbServer.restaurant.facade.RestaurantFacade;

@RestController
public class RestaurantController {
	@Autowired
	private RestaurantFacade restaurantFacade;

	@RequestMapping(value = "/restaurant/restaurantHome", method = RequestMethod.GET)
	public ModelAndView restaurantHome() {
		ModelAndView modelAndView = new ModelAndView();
		Restaurant restaurant = new Restaurant();
		modelAndView.addObject("restaurant", restaurant);
		modelAndView.setViewName("restaurant/restaurantHome");
		return modelAndView;
	}
	
	@RequestMapping(value = "/restaurant/restaurantHome", method = RequestMethod.POST)
	public ModelAndView createNewRestaurant(@Valid Restaurant restaurant, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		Restaurant restaurantExists = restaurantFacade.findByName(restaurant.getName());
		if (restaurant.getName() == null || restaurant.getName().trim().isEmpty()) {
			bindingResult.rejectValue("name", "error.restaurant",
					"Restaurant name could not be empty");
		} else if (restaurantExists != null) {
			bindingResult.rejectValue("name", "error.restaurant",
					"There is already a restaurant registered with the name provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("restaurant/restaurantHome");
		} else {
			restaurantFacade.saveRestaurant(restaurant);
			modelAndView.addObject("successMessage", "Restaurant has been registered successfully");
			modelAndView.addObject("Restaurant", new Restaurant());
			modelAndView.setViewName("restaurant/restaurantHome");
		}
		
		modelAndView.addObject("restaurant", restaurant);
		modelAndView.setViewName("restaurant/restaurantHome");
		return modelAndView;
	}
}
