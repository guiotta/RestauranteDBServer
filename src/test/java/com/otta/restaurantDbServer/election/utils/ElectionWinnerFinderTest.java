package com.otta.restaurantDbServer.election.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.otta.restaurantDbServer.database.entity.Election;
import com.otta.restaurantDbServer.database.entity.Restaurant;
import com.otta.restaurantDbServer.database.entity.Vote;
import com.otta.restaurantDbServer.election.facade.ElectionFacade;
import com.otta.restaurantDbServer.vote.facade.VoteFacade;

@RunWith(MockitoJUnitRunner.class)
public class ElectionWinnerFinderTest {
	@Mock
	private ElectionFacade electionFacade;
	@Mock
	private VoteFacade voteFacade;
	@InjectMocks
	private ElectionWinnerFinder finder;

	@Mock
	private Election election;
	@Mock
	private Vote vote1;
	@Mock
	private Vote vote2;
	@Mock
	private Vote vote3;
	@Mock
	private Restaurant restaurant1;
	@Mock
	private Restaurant restaurant2;

	@Test
	public void shouldReturnNullWhenElectionDontExists() {
		// given
		LocalDate date = LocalDate.now();
		when(electionFacade.findElectionByDate(date)).thenReturn(null);
		// when
		Restaurant actualValue = finder.find(date);
		// then
		assertNull(actualValue);
	}

	@Test
	public void shouldReturnRestaurantWinnerOfElectionInDate() {
		// given
		LocalDate date = LocalDate.now();
		when(electionFacade.findElectionByDate(date)).thenReturn(election);
		when(voteFacade.findByElection(election)).thenReturn(Lists.list(vote1, vote2, vote3));
		when(vote1.getRestaurant()).thenReturn(restaurant1);
		when(vote2.getRestaurant()).thenReturn(restaurant1);
		when(vote3.getRestaurant()).thenReturn(restaurant2);
		// when
		Restaurant actualValue = finder.find(date);
		// then
		assertEquals(restaurant1, actualValue);
	}
}
