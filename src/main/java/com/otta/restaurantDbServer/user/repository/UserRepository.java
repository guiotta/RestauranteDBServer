package com.otta.restaurantDbServer.user.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.otta.restaurantDbServer.database.entity.User;

public interface UserRepository extends CrudRepository<User, BigInteger> {

	List<User> findByName(String name);
}
