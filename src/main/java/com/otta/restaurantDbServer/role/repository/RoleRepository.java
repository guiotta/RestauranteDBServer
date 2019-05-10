package com.otta.restaurantDbServer.role.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;

import com.otta.restaurantDbServer.database.entity.Role;

public interface RoleRepository extends CrudRepository<Role, BigInteger> {
	Role findByRole(String role);
}
