package com.ssafy.alarm.model.dao;

import java.util.List;

import com.ssafy.alarm.model.dto.ExerInfo;

public interface ExerInfoDao {
	
	// 모든 info 가져옴
	public List<ExerInfo> selectAll();
		
	// id로 가져옴
	public ExerInfo selectOne(int infoId);
		
}
