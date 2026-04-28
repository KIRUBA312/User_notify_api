package com.example.notify.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "notification_preference")
public class NotificationPreference {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private boolean emailEnabled;
	private boolean smsEnabled;
	private boolean pushEnabled;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public NotificationPreference() {}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public boolean isEmailEnabled() {
		return emailEnabled;
	}

	public void setEmailEnabled(boolean emailEnabled) {
		this.emailEnabled = emailEnabled;
	}

	public boolean isSmsEnabled() {
		return smsEnabled;
	}

	public void setSmsEnabled(boolean smsEnabled) {
		this.smsEnabled = smsEnabled;
	}

	public boolean isPushEnabled() {
		return pushEnabled;
	}

	public void setPushEnabled(boolean pushEnabled) {
		this.pushEnabled = pushEnabled;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
