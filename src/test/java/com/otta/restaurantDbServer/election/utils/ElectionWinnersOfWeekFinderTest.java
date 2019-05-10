package com.otta.restaurantDbServer.election.utils;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.otta.restaurantDbServer.database.entity.Restaurant;
import com.otta.restaurantDbServer.election.bean.ElectionWinner;

@RunWith(MockitoJUnitRunner.class)
public class ElectionWinnersOfWeekFinderTest {
	private static final int DATE_YEAR = 2019;
	private static final int DATE_MONTH = 5;
	private static final int DATE_DAY_OF_MONTH = 10;
	private static final int DAYS_TO_SUBSTRACT = 1;

	@Mock(lenient = true)
	private ElectionWinnerFinder electionWinnerFinder;
	@InjectMocks
	private ElectionWinnersOfWeekFinder finder;
	@Mock
	private Restaurant restaurant1;
	@Mock
	private Restaurant restaurant2;

	private LocalDate baseDate;

	@Before
	public void setUp() {
		baseDate = LocalDate.of(DATE_YEAR, DATE_MONTH, DATE_DAY_OF_MONTH);
		LocalDate date1 = baseDate.minusDays(DAYS_TO_SUBSTRACT);
		LocalDate date2 = date1.minusDays(DAYS_TO_SUBSTRACT);

		when(electionWinnerFinder.find(date1)).thenReturn(restaurant1);
		when(electionWinnerFinder.find(date2)).thenReturn(restaurant2);
	}

	@Test
	public void shouldReturnWinnersOfWeek() {
		// given
		ElectionWinner winner1 = new ElectionWinner(DayOfWeek.THURSDAY, Optional.of(restaurant1));
		ElectionWinner winner2 = new ElectionWinner(DayOfWeek.WEDNESDAY, Optional.of(restaurant2));
		ElectionWinner winner3 = new ElectionWinner(DayOfWeek.TUESDAY, Optional.empty());
		ElectionWinner winner4 = new ElectionWinner(DayOfWeek.MONDAY, Optional.empty());
		// when
		List<ElectionWinner> actualValue = finder.find(baseDate);
		// then
		assertThat(actualValue, IsIterableContainingInAnyOrder.containsInAnyOrder(winner1, winner2, winner3, winner4));
	}
}
