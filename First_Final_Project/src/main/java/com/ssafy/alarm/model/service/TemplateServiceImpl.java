package com.ssafy.alarm.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.alarm.model.dao.TemplateDao;
import com.ssafy.alarm.model.dto.Template;

@Service
public class TemplateServiceImpl implements TemplateService {

	private final TemplateDao templateDao;
	
	public TemplateServiceImpl(TemplateDao templateDao) {
		this.templateDao = templateDao;
	}

	@Override
	public Template getTemplate(int tempId) {
		return templateDao.selectOne(tempId);
	}

	@Override
	public List<Template> getTemplateList() {
		return templateDao.selectAll();
	}

	@Override
	public void countTemp(int tempId) {
		templateDao.countTemp(tempId);
	}

}
