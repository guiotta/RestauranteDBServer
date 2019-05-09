package com.otta.restaurantDbServer.vote.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.otta.restaurantDbServer.database.entity.Election;
import com.otta.restaurantDbServer.database.entity.User;
import com.otta.restaurantDbServer.database.entity.Vote;

public interface VoteRepository extends CrudRepository<Vote, BigInteger> {
	List<Vote> findByElection(Election election);
	Vote findByElectionAndUser(Election election, User user);
}
