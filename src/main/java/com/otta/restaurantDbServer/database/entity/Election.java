package com.otta.restaurantDbServer.database.entity;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Election {
	@Id
	@GeneratedValue
	private BigInteger id;
	private LocalDate date;
	
	public Election() {
	}

	public Election(BigInteger id, LocalDate date) {
		this.id = id;
		this.date = date;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Election other = (Election) obj;
		return Objects.equals(date, other.date) && Objects.equals(id, other.id);
	}
}
