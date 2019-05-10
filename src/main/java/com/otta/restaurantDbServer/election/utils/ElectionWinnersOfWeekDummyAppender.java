package com.otta.restaurantDbServer.election.utils;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.otta.restaurantDbServer.election.bean.ElectionWinner;

@Component
public class ElectionWinnersOfWeekDummyAppender {
	private static final int MAX_DAY_OF_WEEK_IDENTIFIER = 7;

	public List<ElectionWinner> append(List<ElectionWinner> winners) {
		List<ElectionWinner> winnersUpdatedList = new ArrayList<ElectionWinner>(winners);
		
		for (int dayCount = MAX_DAY_OF_WEEK_IDENTIFIER; dayCount > 0; dayCount--) {
			DayOfWeek dayOfWeek = DayOfWeek.of(dayCount);

			if (winners.stream().anyMatch(winner -> winner.getDay() != dayOfWeek)) {
				winnersUpdatedList.add(new ElectionWinner(dayOfWeek, Optional.empty()));
			}
		}
		
		return winnersUpdatedList;
	}
}
