package com.ssafy.alarm.model.service;

import java.util.List;

import com.ssafy.alarm.model.dto.Alarm;

public interface AlarmService {
	
	int createAlarm(Alarm alarm);
	
	int removeAlarm(int alarmId);
	
	int modifyAlarm(Alarm alarm);
	
	Alarm getAlarm(int alarmId);
	
	List<Alarm> getAlarmList();
	
}
