package com.ssafy.alarm.model.dao;

import java.util.List;
import java.util.Map;

import com.ssafy.alarm.model.dto.Alarm;

public interface AlarmDao {
	
	int insertAlarm(Alarm alarm);
	
	int deleteAlarm(int alarmId);
	
	int updateAlarm(Alarm alarm);
	
	Alarm selectOne(int alarmId);
	
	List<Alarm> selectAllByUserId(String userId);
	
	int alarmOnOff(Map<String, String> alarmInfo);
}
