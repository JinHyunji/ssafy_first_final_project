package com.ssafy.alarm.model.service;

import com.ssafy.alarm.model.dto.User;

public interface UserService {

	public int signup(User user);
	
	public User login(String id, String password);
	
	public User getUserByID(String userId);

}
