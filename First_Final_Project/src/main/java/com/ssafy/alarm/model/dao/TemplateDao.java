package com.ssafy.alarm.model.dao;

import java.util.List;

import com.ssafy.alarm.model.dto.Template;

public interface TemplateDao {
	
	Template selectOne(int tempId);
	
	List<Template> selectAll();
	
}
