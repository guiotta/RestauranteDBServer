package com.otta.restaurantDbServer.election.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.otta.restaurantDbServer.database.entity.Restaurant;
import com.otta.restaurantDbServer.election.bean.ElectionWinner;

@Component
public class ElectionWinnersOfWeekFinder {
	@Autowired
	private ElectionWinnerFinder electionWinnerFinder;
	
	public List<ElectionWinner> find(LocalDate date) {
		List<ElectionWinner> winnersOfWeek = new ArrayList<>();
		DayOfWeek initialDay = date.getDayOfWeek();
		int daysToSubstract = 0;

		for (int dayCount = initialDay.getValue() - 1; dayCount > 0; dayCount--) {
			daysToSubstract++;
			LocalDate dateToFind = date.minusDays(daysToSubstract);
			Restaurant winnerOfDate = electionWinnerFinder.find(dateToFind);
			DayOfWeek dateOfWinning = DayOfWeek.of(dayCount);
			
			winnersOfWeek.add(new ElectionWinner(dateOfWinning, Optional.ofNullable(winnerOfDate)));
		}
		
		return winnersOfWeek;
	}

}
