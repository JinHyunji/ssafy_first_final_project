package com.ssafy.alarm.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.alarm.model.dto.Template;
import com.ssafy.alarm.model.service.TemplateService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api-temp")
@Tag(name = "템플릿 관리 기능", description = "템플릿 가져오기")
public class TemplateRestController {
	
	private final TemplateService templateService;

	public TemplateRestController(TemplateService templateService) {
		this.templateService = templateService;
	}
	
	@GetMapping("/temp/{tempId}")
	@Operation(summary = "템플릿 아이디로 가져오기")
	public ResponseEntity<?> getTemplate(@PathVariable("tempId") int tempId) {
		Template temp = templateService.getTemplate(tempId);
		
		if (temp != null) {
			return ResponseEntity.ok(temp);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping("/temp")
	@Operation(summary = "템플릿 전체 가져오기")
	public ResponseEntity<?> getTemplateList() {
		List<Template> list = templateService.getTemplateList();
		
		if (list.size() > 0 && !list.isEmpty()) {
			return ResponseEntity.ok(list);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
}
