package com.otta.restaurantDbServer.restaurant.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;

import com.otta.restaurantDbServer.database.entity.Restaurant;

public interface RestaurantRepository extends CrudRepository<Restaurant, BigInteger> {
	Restaurant findByName(String name);

}
