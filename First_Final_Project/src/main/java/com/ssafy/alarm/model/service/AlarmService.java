package com.ssafy.alarm.model.service;

import java.util.List;

import com.ssafy.alarm.model.dto.Alarm;

public interface AlarmService {
	
	int createAlarm(Alarm alarm);
	
	int removeAlarm(int alarmId);
	
	int modifyAlarm(Alarm alarm);
	
	Alarm getAlarm(int alarmId);
	
	List<Alarm> getAlarmListByUserId(String userId);
	
	String base64ToFileSource(String FileinBase64);
	
	int activateAlarm(int alarmId);

}
