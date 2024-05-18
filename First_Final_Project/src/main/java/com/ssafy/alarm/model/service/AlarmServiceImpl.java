package com.ssafy.alarm.model.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.alarm.model.dao.AlarmDao;
import com.ssafy.alarm.model.dto.Alarm;

@Service
public class AlarmServiceImpl implements AlarmService {

	private final AlarmDao alarmDao;
	private final String imgDir = "../first-final-vue-project/public/images";
	
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
		List<Alarm> alarmList = alarmDao.selectAllByUserId(userId);
		Collections.sort(alarmList, new Comparator<Alarm>() {
			@Override
			public int compare(Alarm a, Alarm b) {
				if(a.getExerType().equals(b.getExerType())) {
					return a.getAlarmId() - b.getAlarmId();
				}
				return a.getExerType().compareTo(b.getExerType());
			}
		});
		return alarmList;
	}

	@Override
	public String base64ToFileSource(String FileinBase64) {
		
		String ImgName = System.currentTimeMillis()+".png";
		
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(FileinBase64.split(",")[1]);
			
			File alarmImgDir = new File(imgDir);
			if(!alarmImgDir.exists()) {
				System.out.println("생성시도");
				alarmImgDir.mkdirs();
			}
			
			File alarmImgFile = new File(alarmImgDir, ImgName);
			
			try (FileOutputStream fos = new FileOutputStream(alarmImgFile)){
				fos.write(decodedBytes);
			}
			
			System.out.println("이미지 파일 저장 성공");
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("이미지 파일 저장 실패");
		}
		
		return ImgName;
	}

}
