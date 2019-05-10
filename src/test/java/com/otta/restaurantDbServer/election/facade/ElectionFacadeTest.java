package com.otta.restaurantDbServer.election.facade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.otta.restaurantDbServer.database.entity.Election;
import com.otta.restaurantDbServer.election.repository.ElectionRepository;

@RunWith(MockitoJUnitRunner.class)
public class ElectionFacadeTest {
	@Mock
	private ElectionRepository electionRepository;
	@InjectMocks
	private ElectionFacade facade;

	@Mock
	private Election election;

	@Test
	public void shouldSaveElection() {
		// given
		// when
		facade.saveElection(election);
		// then
		verify(electionRepository).save(election);
	}

	@Test
	public void shouldFindElectionByDate() {
		// given
		LocalDate date = LocalDate.now();

		when(electionRepository.findByDate(date)).thenReturn(election);
		// when
		Election actualValue = facade.findElectionByDate(date);
		// then
		assertEquals(election, actualValue);
	}

	@Test
	public void shouldReturnFalseWhenHasNoElectionForDate() {
		// given
		// when
		boolean actualValue = facade.hasElectionForDate(LocalDate.now());
		// then
		assertFalse(actualValue);
	}

	@Test
	public void shouldReturnTrueWhenHasElectionForDate() {
		// given
		LocalDate date = LocalDate.now();

		when(electionRepository.findByDate(date)).thenReturn(election);
		// when
		boolean actualValue = facade.hasElectionForDate(date);
		// then
		assertTrue(actualValue);
	}
}
