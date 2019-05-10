package com.otta.restaurantDbServer.database.entity;

import java.math.BigInteger;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Restaurant {
	@Id
	@GeneratedValue
	private BigInteger id;
	private String name;

	public Restaurant() {
	}

	public Restaurant(BigInteger id, String name) {
		this.id = id;
		this.name = name;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Restaurant other = (Restaurant) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
	
	

}
