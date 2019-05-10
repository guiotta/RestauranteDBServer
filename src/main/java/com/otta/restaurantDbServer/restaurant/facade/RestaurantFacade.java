package com.otta.restaurantDbServer.restaurant.facade;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.otta.restaurantDbServer.database.entity.Restaurant;
import com.otta.restaurantDbServer.restaurant.repository.RestaurantRepository;

@Component
public class RestaurantFacade {
	@Autowired
	private RestaurantRepository restaurantRepository;

	public void saveRestaurant(Restaurant restaurant) {
		restaurantRepository.save(restaurant);
	}

	public Restaurant findByName(String name) {
		return restaurantRepository.findByName(name);
	}

	public List<Restaurant> listAllAvailableRestaurants() {
		return StreamSupport.stream(restaurantRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}
}
