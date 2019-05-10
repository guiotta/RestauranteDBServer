package com.otta.restaurantDbServer.vote.utils;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.util.Lists;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.otta.restaurantDbServer.database.entity.Election;
import com.otta.restaurantDbServer.database.entity.Restaurant;
import com.otta.restaurantDbServer.database.entity.Vote;
import com.otta.restaurantDbServer.election.facade.ElectionFacade;
import com.otta.restaurantDbServer.vote.bean.VoteRestaurantCounter;
import com.otta.restaurantDbServer.vote.facade.VoteFacade;

@RunWith(MockitoJUnitRunner.class)
public class VotesClassifierTest {
	private static final String NAME1 = "name1";
	private static final String NAME2 = "name2";
	
	@Mock
	private ElectionFacade electionFacade;
	@Mock
	private VoteFacade voteFacade;
	@InjectMocks
	private VotesClassifier classifier;
	
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
	public void shouldReturnEmptyListWhenElectionDontExists() {
		// given
		// when
		List<VoteRestaurantCounter> actualValue = classifier.classify(LocalDate.now());
		// then
		assertTrue(actualValue.isEmpty());
	}
	
	@Test
	public void shouldClassifyVotesInElection() {
		// given
		LocalDate date = LocalDate.now();
		when(electionFacade.findElectionByDate(date)).thenReturn(election);
		when(voteFacade.findByElection(election)).thenReturn(Lists.list(vote1, vote2, vote3));
		when(vote1.getRestaurant()).thenReturn(restaurant1);
		when(vote2.getRestaurant()).thenReturn(restaurant1);
		when(vote3.getRestaurant()).thenReturn(restaurant2);
		when(restaurant1.getName()).thenReturn(NAME1);
		when(restaurant2.getName()).thenReturn(NAME2);
		
		VoteRestaurantCounter expected1 = new VoteRestaurantCounter(NAME1, 2L);
		VoteRestaurantCounter expected2 = new VoteRestaurantCounter(NAME2, 1L);
		// when
		List<VoteRestaurantCounter> actualValue = classifier.classify(date);
		// then
		assertThat(actualValue, IsIterableContainingInAnyOrder.containsInAnyOrder(expected1, expected2));
	}
}
