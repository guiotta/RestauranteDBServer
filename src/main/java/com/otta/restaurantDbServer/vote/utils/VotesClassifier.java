package com.otta.restaurantDbServer.vote.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.otta.restaurantDbServer.database.entity.Election;
import com.otta.restaurantDbServer.database.entity.Vote;
import com.otta.restaurantDbServer.election.facade.ElectionFacade;
import com.otta.restaurantDbServer.vote.bean.VoteRestaurantCounter;
import com.otta.restaurantDbServer.vote.facade.VoteFacade;

@Component
public class VotesClassifier {
	@Autowired
	private ElectionFacade electionFacade;
	@Autowired
	private VoteFacade voteFacade;

	public List<VoteRestaurantCounter> classify(LocalDate date) {
		Optional<Election> optionalElection = Optional.ofNullable(electionFacade.findElectionByDate(date));
		List<VoteRestaurantCounter> counterList = new ArrayList<>();
		
		if (optionalElection.isPresent()) {
			Election election = optionalElection.get();
			List<Vote> votesByElection = voteFacade.findByElection(election);

			Set<Entry<String, Long>> entries = votesByElection.stream()
					.map(vote -> vote.getRestaurant().getName())
					.collect(Collectors.groupingBy(c -> c, Collectors.counting())).entrySet();

			counterList.addAll(entries.stream()
					.map(entry -> new VoteRestaurantCounter(entry.getKey(), entry.getValue()))
					.sorted(Comparator.comparing(VoteRestaurantCounter::getCount).reversed())
					.collect(Collectors.toList()));
		}
		
		return counterList;
	}
}
