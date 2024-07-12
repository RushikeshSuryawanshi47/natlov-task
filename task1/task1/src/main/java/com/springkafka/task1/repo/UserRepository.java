package com.springkafka.task1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springkafka.task1.entity.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
}
