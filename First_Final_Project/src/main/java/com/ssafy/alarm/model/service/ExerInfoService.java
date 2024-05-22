package com.ssafy.alarm.model.service;

import java.util.List;

import com.ssafy.alarm.model.dto.ExerInfo;

public interface ExerInfoService {
	
	// 정보 전체 조회
	public List<ExerInfo> getExerInfoList();
	
	// 정보 하나 조회
	public ExerInfo getExerInfo(int infoId);
	
}
