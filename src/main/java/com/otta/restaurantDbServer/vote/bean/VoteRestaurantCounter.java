package com.otta.restaurantDbServer.vote.bean;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(count, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VoteRestaurantCounter other = (VoteRestaurantCounter) obj;
		return Objects.equals(count, other.count) && Objects.equals(name, other.name);
	}

}
