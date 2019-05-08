package com.otta.restaurantDbServer.database.entity;

import java.math.BigInteger;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Election {
	@Id
	@GeneratedValue
	private BigInteger id;
	private LocalDate date;

	public Election(BigInteger id, LocalDate date) {
		this.id = id;
		this.date = date;
	}

	public BigInteger getId() {
		return id;
	}

	public LocalDate getDate() {
		return date;
	}
}
