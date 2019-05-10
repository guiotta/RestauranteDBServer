package com.otta.restaurantDbServer.election.repository;

import java.math.BigInteger;
import java.time.LocalDate;

import org.springframework.data.repository.CrudRepository;

import com.otta.restaurantDbServer.database.entity.Election;

public interface ElectionRepository extends CrudRepository<Election, BigInteger> {
	Election findByDate(LocalDate date);

}
