package com.ssafy.alarm.model.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.alarm.model.dao.AlarmDao;
import com.ssafy.alarm.model.dto.Alarm;

@Service
public class AlarmServiceImpl implements AlarmService {

	private final AlarmDao alarmDao;
	
	@Autowired
	public AlarmServiceImpl(AlarmDao alarmDao) {
		this.alarmDao = alarmDao;
	}

	@Override
	public int createAlarm(Alarm alarm) {
		return alarmDao.insertAlarm(alarm);
	}

	@Override
	public int removeAlarm(int alarmId) {
		return alarmDao.deleteAlarm(alarmId);
	}

	@Override
	public int modifyAlarm(Alarm alarm) {
		return alarmDao.updateAlarm(alarm);
	}

	@Override
	public Alarm getAlarm(int alarmId) {
		return alarmDao.selectOne(alarmId);
	}

	@Override
	public List<Alarm> getAlarmListByUserId(String userId) {
		return alarmDao.selectAllByUserId(userId);
	}

}
