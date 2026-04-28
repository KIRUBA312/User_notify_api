package com.example.notify.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.notify.entity.NotificationPreference;

public interface PreferenceRepository extends JpaRepository<NotificationPreference, Long>{
	
	NotificationPreference findByUserId(Long userId);

}
