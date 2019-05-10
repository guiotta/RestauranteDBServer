package com.otta.restaurantDbServer.election.utils;

import static org.junit.Assert.assertThat;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.otta.restaurantDbServer.election.bean.ElectionWinner;

@RunWith(MockitoJUnitRunner.class)
public class ElectionWinnersOfWeekDummyAppenderTest {
	@InjectMocks
	private ElectionWinnersOfWeekDummyAppender appender;

	@Test
	public void shouldAppendDummyValues() {
		// given
		ElectionWinner element1 = new ElectionWinner(DayOfWeek.MONDAY, Optional.empty());
		ElectionWinner element2 = new ElectionWinner(DayOfWeek.FRIDAY, Optional.empty());
		List<ElectionWinner> param = Lists.list(element1, element2);
		
		ElectionWinner expectedValue1 = new ElectionWinner(DayOfWeek.TUESDAY, Optional.empty());
		ElectionWinner expectedValue2 = new ElectionWinner(DayOfWeek.WEDNESDAY, Optional.empty());
		ElectionWinner expectedValue3 = new ElectionWinner(DayOfWeek.THURSDAY, Optional.empty());
		ElectionWinner expectedValue4 = new ElectionWinner(DayOfWeek.SATURDAY, Optional.empty());
		ElectionWinner expectedValue5 = new ElectionWinner(DayOfWeek.SUNDAY, Optional.empty());
		// when
		List<ElectionWinner> actualList = appender.append(param);
		// then
		assertThat(actualList, IsIterableContainingInAnyOrder.containsInAnyOrder(element1, element2, expectedValue1,
				expectedValue2, expectedValue3, expectedValue4, expectedValue5));
	}
}
