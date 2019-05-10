package com.otta.restaurantDbServer.election.utils;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.otta.restaurantDbServer.database.entity.Election;
import com.otta.restaurantDbServer.database.entity.Restaurant;
import com.otta.restaurantDbServer.database.entity.Vote;
import com.otta.restaurantDbServer.election.facade.ElectionFacade;
import com.otta.restaurantDbServer.vote.facade.VoteFacade;

/**
 * Retorna o restaurate que foi o vencedor da eleição de um dia. Pode retornar um valor nulo.
 * @author Guilherme
 *
 */
@Component
public class ElectionWinnerFinder {
	@Autowired
	private ElectionFacade electionFacade;
	@Autowired
	private VoteFacade voteFacade;

	public Restaurant find(LocalDate date) {
		Optional<Election> optionalElection = Optional.ofNullable(electionFacade.findElectionByDate(date));
		
		if (optionalElection.isPresent()) {
			Election election = optionalElection.get();
			List<Vote> votesByElection = voteFacade.findByElection(election);

			return votesByElection.stream()
					.map(vote -> vote.getRestaurant())
					.collect(Collectors.groupingBy(c -> c, Collectors.counting())).entrySet()
					.stream()
					.max(Comparator.comparing(Entry::getValue)).get().getKey();
		}
		
		return null;
	}
}
