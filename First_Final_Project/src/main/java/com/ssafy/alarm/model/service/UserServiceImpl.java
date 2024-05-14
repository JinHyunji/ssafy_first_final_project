package com.ssafy.alarm.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.alarm.model.dao.UserDao;
import com.ssafy.alarm.model.dto.User;

@Service
public class UserServiceImpl implements UserService{

	private final UserDao userDao;
	
	@Autowired
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public int signup(User user) {
		return userDao.insertUser(user);
	}

	@Override
	public User login(String id, String password) {
		Map<String, String> loginInfo = new HashMap<>();
		loginInfo.put("userId", id);
		loginInfo.put("password", password);
		return userDao.selectOne(loginInfo);
	}

	@Override
	public User getUserByID(String id) {
		return userDao.selectById(id);
	}
	

}
