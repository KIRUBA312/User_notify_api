package com.example.notify.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.notify.dao.NotificationRepository;
import com.example.notify.dao.PreferenceRepository;
import com.example.notify.dao.UserRepository;
import com.example.notify.entity.Notification;
import com.example.notify.entity.NotificationPreference;
import com.example.notify.entity.User;
import com.example.notify.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {
	
	@Autowired
	private PreferenceRepository preferenceRepo;
	
	@Autowired
	private NotificationRepository notificationRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public void createPreference(Long userId, NotificationPreference pref) {

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found"));

		pref.setUser(user);
		preferenceRepo.save(pref);
		
	}

	@Override
	public NotificationPreference getPreference(Long userId) {
		// TODO Auto-generated method stub
		return preferenceRepo.findByUserId(userId);
	}

	@Override
	public void updatePreference(Long userId, NotificationPreference pref) {
		// TODO Auto-generated method stub
		 NotificationPreference existingPref = preferenceRepo.findByUserId(userId);
	        if (existingPref != null) {
	        	
	        	existingPref.setEmailEnabled(pref.isEmailEnabled());
	        	existingPref.setSmsEnabled(pref.isSmsEnabled());
	        	existingPref.setPushEnabled(pref.isPushEnabled());
	        	preferenceRepo.save(existingPref);
	        	
	        }
	        else {
	        	User user = userRepo.findById(userId)
	        		    .orElseThrow(() -> new RuntimeException("User not found"));
				pref.setUser(user);
	        	preferenceRepo.save(pref);
	        }
	        
            
	        
		
	}

	@Override
	public List<Notification> getNotificationHistory(Long userId) {
		// TODO Auto-generated method stub
		return notificationRepo.findAll()
				.stream()
				.filter(n -> n.getUser().getId().equals(userId))
				.toList();
	}

	@Override
	public void sendNotification(Long userId, String message) {
		// TODO Auto-generated method stub
		
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		NotificationPreference pref = preferenceRepo.findByUserId(userId);

		if(pref == null) {
//			throw new RuntimeException("Notification preference not found for user " + userId);
			// If no preferences found, assume all channels enabled
			pref = new NotificationPreference();
			pref.setEmailEnabled(false);
			pref.setSmsEnabled(false);
			pref.setPushEnabled(false);
			
		}
		
		if(!pref.isEmailEnabled() && !pref.isSmsEnabled() && !pref.isPushEnabled()) {
			throw new RuntimeException("No notification channels enabled for user " + userId);
		}
		
		if(pref.isEmailEnabled()) {
			// Simulate sending email
			save(user, message, "EMAIL","SENT");
		}else {
			save(user, message, "EMAIL","FAILED");
		}
		if(pref.isSmsEnabled()) {
			// Simulate sending SMS
			save(user, message, "SMS","SENT");
		}else {
			save(user, message, "SMS","FAILED");
		}
		if(pref.isPushEnabled()) {
			// Simulate sending push notification
			save(user, message, "PUSH","SENT");
		}else {
			save(user, message, "PUSH","FAILED");
		}
	}
		
		private void save(User user, String message, String type, String status) {
			Notification notification = new Notification();
			notification.setUser(user);
			notification.setMessage(message);
			notification.setType(type);
			notification.setStatus(status);
			notification.setSentAt(LocalDateTime.now());
			
//			try {
//				//simulate sending (always success)
//				
//			}
//			catch(Exception e) {
//				notification.setStatus("FAILED");
//			}
			notificationRepo.save(notification);
		
		
	}

		@Override
		public List<Notification> getNotificationHistoryByStatus(String status) {
			// TODO Auto-generated method stub
			return notificationRepo.findByStatus(status);
		}

		@Override
		public List<Notification> getNotificationHistoryByType(String type) {
			// TODO Auto-generated method stub
			return notificationRepo.findByType(type);
		}

		
	
	

}
