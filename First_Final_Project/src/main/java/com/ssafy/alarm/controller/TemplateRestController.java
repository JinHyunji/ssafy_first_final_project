package com.ssafy.alarm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.alarm.model.service.TemplateService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api-temp")
@Tag(name = "TemplateRestController", description = "템플릿 가져오기")
public class TemplateRestController {
	
	private final TemplateService templateService;

	public TemplateRestController(TemplateService templateService) {
		this.templateService = templateService;
	}
	
//	@GetMapping("/temp/{tempId}")
//	public ResponseEntity<?> getTemplate(@PathVariable("tempId") int tempId) {
//		
//	}
	
	

}
