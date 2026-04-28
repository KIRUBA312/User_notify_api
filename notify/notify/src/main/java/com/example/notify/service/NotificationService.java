package com.example.notify.service;

import java.util.List;


import com.example.notify.entity.Notification;
import com.example.notify.entity.NotificationPreference;


public interface NotificationService {

	void createPreference(Long userId, NotificationPreference pref);

	NotificationPreference getPreference(Long userId);

	void updatePreference(Long userId, NotificationPreference pref);

	List<Notification> getNotificationHistory(Long userId);

	void sendNotification(Long userId, String message);

	List<Notification> getNotificationHistoryByStatus(String status);

	List<Notification> getNotificationHistoryByType(String type);
	

}
