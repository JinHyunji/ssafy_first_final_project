package com.ssafy.alarm.model.dao;

import java.util.Map;

import com.ssafy.alarm.model.dto.User;

public interface UserDao {

	public int insertUser(User user);
	
	public User selectOne(Map<String, String> user);
	
	public User selectById(String id);
	
}
