package com.otta.restaurantDbServer.election.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.otta.restaurantDbServer.database.entity.Election;
import com.otta.restaurantDbServer.election.facade.ElectionFacade;

@Component
public class ElectionService {
	private static final Object SEMAPHORE = new Object();
	
	@Autowired
	private ElectionFacade electionFacade;
	
	/**
	 * Recupera a eleição para um determinado dia. Caso não exista eleição par este dia, cria a mesma antes de retornar. 
	 */
	public Election getValidElectionForDay(LocalDate date) {
		synchronized (SEMAPHORE) {
			if (!electionFacade.hasElectionForDate(date)) {
				electionFacade.createAndSaveNewElection();
			}
			return electionFacade.findElectionByDate(date);
		}
	}
}
