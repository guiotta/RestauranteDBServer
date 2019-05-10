package com.otta.restaurantDbServer.restaurant.facade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.assertj.core.util.Lists;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.otta.restaurantDbServer.database.entity.Restaurant;
import com.otta.restaurantDbServer.restaurant.repository.RestaurantRepository;

@RunWith(MockitoJUnitRunner.class)
public class RestaurantFacadeTest {
	private static final String NAME = "name";
	
	@Mock
	private RestaurantRepository repository;
	@InjectMocks
	private RestaurantFacade facade;

	@Mock
	private Restaurant restaurant;

	@Test
	public void shouldSaveRestaurant() {
		// given
		// when
		facade.saveRestaurant(restaurant);
		// then
		verify(repository).save(restaurant);
	}

	@Test
	public void shouldFindRestaurantByName() {
		// given
		when(repository.findByName(NAME)).thenReturn(restaurant);
		// when
		Restaurant actualValue = facade.findByName(NAME);
		// then
		assertEquals(restaurant, actualValue);
	}
	
	@Test
	public void shouldFindAllRestaurants() {
		// given
		when(repository.findAll()).thenReturn(Lists.list(restaurant));
		// when
		List<Restaurant> actualList = facade.listAllAvailableRestaurants();
		// then
		assertThat(actualList, IsIterableContainingInAnyOrder.containsInAnyOrder(restaurant));
	}
}
