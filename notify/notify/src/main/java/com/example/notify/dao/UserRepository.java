package com.example.notify.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.notify.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
//	User findByUsername(String username);

}
