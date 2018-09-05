package com.repository;

/**
* @author  Yogesh Khopade
* @version 1.0
* @since   2018-09-07 
*/

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	@Query(value = "select * from GENIE_USER where cust_Id = ?1", nativeQuery = true)
    User findByCust_Id(String custId);
}
