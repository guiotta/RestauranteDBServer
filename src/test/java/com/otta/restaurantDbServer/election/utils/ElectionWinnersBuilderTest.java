package com.otta.restaurantDbServer.election.utils;

import static org.junit.Assert.assertThat;
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

import com.otta.restaurantDbServer.election.bean.ElectionWinner;

@RunWith(MockitoJUnitRunner.class)
public class ElectionWinnersBuilderTest {
	@Mock
	private ElectionWinnersOfWeekFinder electionWinnersOfWeekFinder;
	@Mock
	private ElectionWinnersOfWeekDummyAppender winnersOfWeekDummyAppender;
	@InjectMocks
	private ElectionWinnersBuilder builder;

	@Mock
	private ElectionWinner element1;
	@Mock
	private ElectionWinner element2;

	@Test
	public void shouldBuildListCorrectly() {
		// given
		LocalDate date = LocalDate.now();
		List<ElectionWinner> list = Lists.list(element1);

		when(electionWinnersOfWeekFinder.find(date)).thenReturn(list);
		when(winnersOfWeekDummyAppender.append(list)).thenReturn(Lists.list(element1, element2));
		// when
		List<ElectionWinner> actualValue = builder.build(date);
		// then
		assertThat(actualValue, IsIterableContainingInAnyOrder.containsInAnyOrder(element1, element2));
	}

}
