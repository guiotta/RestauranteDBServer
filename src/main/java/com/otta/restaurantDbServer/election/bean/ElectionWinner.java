package com.otta.restaurantDbServer.election.bean;

import java.time.DayOfWeek;
import java.util.Objects;
import java.util.Optional;

import com.otta.restaurantDbServer.database.entity.Restaurant;

public class ElectionWinner {
	private DayOfWeek day;
	private Optional<Restaurant> restaurant;

	public ElectionWinner(DayOfWeek day, Optional<Restaurant> restaurant) {
		this.day = day;
		this.restaurant = restaurant;
	}

	public DayOfWeek getDay() {
		return day;
	}

	public void setDay(DayOfWeek day) {
		this.day = day;
	}

	public Optional<Restaurant> getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Optional<Restaurant> restaurant) {
		this.restaurant = restaurant;
	}
	
	public boolean isRestaurantPresent() {
		return restaurant.isPresent();
	}

	@Override
	public int hashCode() {
		return Objects.hash(day, restaurant);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElectionWinner other = (ElectionWinner) obj;
		return day == other.day && Objects.equals(restaurant, other.restaurant);
	}
}
