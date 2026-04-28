package com.example.notify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.notify.dto.MessageRequest;
import com.example.notify.entity.Notification;
import com.example.notify.entity.NotificationPreference;
import com.example.notify.service.NotificationService;

@RestController
@RequestMapping("/api/notify")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;
	
	@PostMapping("/preference/{userId}")
	public String createPreference(@PathVariable Long userId, @RequestBody NotificationPreference pref) {
		notificationService.createPreference(userId, pref);
		return "Preference created";
	}
	
	@GetMapping("/preference/{userId}")
	public NotificationPreference getPreference(@PathVariable Long userId) {
		return notificationService.getPreference(userId);
	}
	
	@PutMapping("/preference/{userId}")
	public String updatePreference(@PathVariable Long userId, @RequestBody NotificationPreference pref) {
		notificationService.updatePreference(userId, pref);
		return "Preference updated";
	}
	
	@GetMapping("/history/{userId}")
	public List<Notification> history(@PathVariable Long userId) {
		return notificationService.getNotificationHistory(userId);
	}
	
	@PostMapping("/send/{userId}")
	public String sendNotification(@PathVariable Long userId, @RequestBody MessageRequest request) {
		notificationService.sendNotification(userId, request.getMessage());
		return "Notification sent to user " ;
	}
	
	@GetMapping("/history/status/{status}")
	public List<Notification> historyByStatus(@PathVariable String status) {
		return notificationService.getNotificationHistoryByStatus(status);
	}
	
	@GetMapping("/history/type/{type}")
	public List<Notification> historyByType(@PathVariable String type) {
		return notificationService.getNotificationHistoryByType(type);
	}
}
