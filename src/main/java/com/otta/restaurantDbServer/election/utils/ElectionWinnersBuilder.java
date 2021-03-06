package com.otta.restaurantDbServer.election.utils;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.otta.restaurantDbServer.election.bean.ElectionWinner;

/**
 * Constr�i uma lista com os ganhadores da semana. Caso o dia n�o tenha uma elei��o, retorna o atributo v�zio.
 * @author Guilherme
 *
 */
@Component
public class ElectionWinnersBuilder {
	@Autowired
	private ElectionWinnersOfWeekFinder electionWinnersOfWeekFinder;
	@Autowired
	private ElectionWinnersOfWeekDummyAppender winnersOfWeekDummyAppender;
	
	public List<ElectionWinner> build(LocalDate date) {
		List<ElectionWinner> winnersOfWeek = electionWinnersOfWeekFinder.find(date);
		
		return winnersOfWeekDummyAppender.append(winnersOfWeek);
	}
}
