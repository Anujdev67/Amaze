package com.hexaware.amazecare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.amazecare.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
