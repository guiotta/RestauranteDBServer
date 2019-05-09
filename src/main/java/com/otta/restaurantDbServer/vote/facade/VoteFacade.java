package com.otta.restaurantDbServer.vote.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.otta.restaurantDbServer.database.entity.Election;
import com.otta.restaurantDbServer.database.entity.Restaurant;
import com.otta.restaurantDbServer.database.entity.User;
import com.otta.restaurantDbServer.database.entity.Vote;
import com.otta.restaurantDbServer.vote.repository.VoteRepository;

@Component
public class VoteFacade {
	@Autowired
	private VoteRepository voteRepository;

	public void saveVote(Vote vote) {
		voteRepository.save(vote);
	}
	
	public void saveVote(Vote vote, Election election, Restaurant restaurant, User user) {
		vote.setElection(election);
		vote.setRestaurant(restaurant);
		vote.setUser(user);
		
		saveVote(vote);
	}
	
	public Vote findByElectionAndUser(Election election, User user) {
		return voteRepository.findByElectionAndUser(election, user);
	}
 
	public List<Vote> findByElection(Election election) {
		return voteRepository.findByElection(election);
	}
}
