package com.otta.restaurantDbServer.restaurant.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.otta.restaurantDbServer.database.entity.Restaurant;
import com.otta.restaurantDbServer.election.bean.ElectionWinner;
import com.otta.restaurantDbServer.election.utils.ElectionWinnersBuilder;
import com.otta.restaurantDbServer.restaurant.facade.RestaurantFacade;

@Component
public class RestaurantService {
	@Autowired
	private RestaurantFacade restaurantFacade;
	@Autowired
	private ElectionWinnersBuilder electionWinnersBuilder;
	
	public List<Restaurant> build() {
		List<Restaurant> availableRestaurants = restaurantFacade.listAllAvailableRestaurants();
		List<ElectionWinner> electionWinners = electionWinnersBuilder.build(LocalDate.now());
		
		electionWinners.stream()
			.filter(winner -> winner.isRestaurantPresent())
			.forEach(winner -> availableRestaurants.remove(winner.getRestaurant().get()));
		
		return availableRestaurants;
	}
}
