package com.otta.restaurantDbServer.election.facade;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.otta.restaurantDbServer.database.entity.Election;
import com.otta.restaurantDbServer.election.repository.ElectionRepository;

@Component
public class ElectionFacade {
	@Autowired
	private ElectionRepository electionRepository;
	
	public void saveElection(Election election) {
		electionRepository.save(election);
	}
	
	public void createAndSaveNewElection() {
		Election election = new Election();
		election.setDate(LocalDate.now());
		saveElection(election);
	}
	
	public boolean hasElectionForDate(LocalDate date) {
		return electionRepository.findByDate(date) != null;
	}
	
	public Election findElectionByDate(LocalDate date) {
		return electionRepository.findByDate(date);
	}
}
