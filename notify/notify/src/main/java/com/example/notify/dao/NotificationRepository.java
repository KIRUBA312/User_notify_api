package com.example.notify.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.notify.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

	List<Notification> findByStatus(String status);
	
	List<Notification> findByType(String type);
	
	
}
