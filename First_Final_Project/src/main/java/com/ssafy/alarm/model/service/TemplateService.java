package com.ssafy.alarm.model.service;

import java.util.List;

import com.ssafy.alarm.model.dto.Template;

public interface TemplateService {
	
	Template getTemplate(int tempId);
	
	List<Template> getTemplateList();
}
