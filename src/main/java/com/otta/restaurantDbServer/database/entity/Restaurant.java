package com.otta.restaurantDbServer.database.entity;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Restaurant {
	@Id
	@GeneratedValue
	private BigInteger id;
	private String name;

	public Restaurant(BigInteger id, String name) {
		this.id = id;
		this.name = name;
	}

	public BigInteger getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
