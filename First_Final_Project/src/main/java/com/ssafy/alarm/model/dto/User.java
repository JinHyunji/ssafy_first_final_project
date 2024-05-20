package com.ssafy.alarm.model.dto;

public class User {
	
	private String userId;
	private String password;
	private String nickname;
	private String email;
	
	public User() {
		
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", nickname=" + nickname + ", email=" + email
				+ "]";
	}
		
}
