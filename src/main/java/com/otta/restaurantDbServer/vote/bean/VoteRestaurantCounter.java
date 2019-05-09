package com.otta.restaurantDbServer.vote.bean;

public class VoteRestaurantCounter {
	public final String name;
	public final Long count;

	public VoteRestaurantCounter(String name, Long count) {
		this.name = name;
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public Long getCount() {
		return count;
	}

}
