package com.otta.restaurantDbServer.election.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.otta.restaurantDbServer.database.entity.Election;
import com.otta.restaurantDbServer.election.facade.ElectionFacade;

@RunWith(MockitoJUnitRunner.class)
public class ElectionServiceTest {
	@Mock
	private ElectionFacade facade;
	@InjectMocks
	private ElectionService service;

	@Mock
	private Election election;

	@Test
	public void shouldCreateElectionIfDontExists() {
		// given
		LocalDate date = LocalDate.now();

		when(facade.findElectionByDate(date)).thenReturn(election);
		// when
		Election actualValue = service.getValidElectionForDay(date);
		// then
		verify(facade).createAndSaveNewElection();
		assertEquals(election, actualValue);
	}

	@Test
	public void shouldNotCreateElectionIfExists() {
		// given
		LocalDate date = LocalDate.now();

		when(facade.hasElectionForDate(date)).thenReturn(Boolean.TRUE);
		when(facade.findElectionByDate(date)).thenReturn(election);
		// when
		Election actualValue = service.getValidElectionForDay(date);
		// then
		verify(facade, times(0)).createAndSaveNewElection();
		assertEquals(election, actualValue);
	}
}
