package com.otta.restaurantDbServer.database.entity;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Vote {
	@Id
	@GeneratedValue
	private BigInteger id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user")
	private User user;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_restaurant")
	private Restaurant restaurant;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_election")
	private Election election;

	public Vote(BigInteger id, User user, Restaurant restaurant, Election election) {
		this.id = id;
		this.user = user;
		this.restaurant = restaurant;
		this.election = election;
	}

	public BigInteger getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public Election getElection() {
		return election;
	}
}
