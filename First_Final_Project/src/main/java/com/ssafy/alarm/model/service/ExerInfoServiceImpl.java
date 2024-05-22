package com.ssafy.alarm.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.alarm.model.dao.ExerInfoDao;
import com.ssafy.alarm.model.dto.ExerInfo;

@Service
public class ExerInfoServiceImpl implements ExerInfoService{
	
	private final ExerInfoDao exerInfoDao;
	
	@Autowired
	public ExerInfoServiceImpl (ExerInfoDao exerInfoDao) {
		this.exerInfoDao = exerInfoDao;
	}

	@Override
	public List<ExerInfo> getExerInfoList() {
		return exerInfoDao.selectAll();
	}

	@Override
	public ExerInfo getExerInfo(int infoId) {
		return exerInfoDao.selectOne(infoId);
	}

}
