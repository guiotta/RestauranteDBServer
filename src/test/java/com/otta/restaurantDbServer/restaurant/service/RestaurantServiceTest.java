package com.otta.restaurantDbServer.restaurant.service;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.otta.restaurantDbServer.database.entity.Restaurant;
import com.otta.restaurantDbServer.election.bean.ElectionWinner;
import com.otta.restaurantDbServer.election.utils.ElectionWinnersBuilder;
import com.otta.restaurantDbServer.restaurant.facade.RestaurantFacade;

@RunWith(MockitoJUnitRunner.class)
public class RestaurantServiceTest {
	@Mock
	private RestaurantFacade facade;
	@Mock
	private ElectionWinnersBuilder builder;
	@InjectMocks
	private RestaurantService service;
	
	@Mock
	private Restaurant restaurant1;
	@Mock
	private Restaurant restaurant2;
	@Mock
	private ElectionWinner winner;

	@Test
	public void shouldRemoveWinnersFromRestaurantList() {
		// given
		LocalDate date = LocalDate.now();
		
		when(winner.isRestaurantPresent()).thenReturn(Boolean.TRUE);
		when(winner.getRestaurant()).thenReturn(Optional.of(restaurant1));
		when(facade.listAllAvailableRestaurants()).thenReturn(Lists.list(restaurant1, restaurant2));
		when(builder.build(date)).thenReturn(Lists.list(winner));
		// when
		List<Restaurant> actualList = service.listAllAvailableRestaurants(date);
		// then
		assertThat(actualList, IsIterableContainingInAnyOrder.containsInAnyOrder(restaurant2));
	}
	
	@Test
	public void shouldReturnAllRestaurantsListWhenWinnersListIsEmpty() {
		// given
		LocalDate date = LocalDate.now();
		
		when(facade.listAllAvailableRestaurants()).thenReturn(Lists.list(restaurant1, restaurant2));
		when(builder.build(date)).thenReturn(Lists.list());
		// when
		List<Restaurant> actualList = service.listAllAvailableRestaurants(date);
		// then
		assertThat(actualList, IsIterableContainingInAnyOrder.containsInAnyOrder(restaurant1, restaurant2));
	}
}
