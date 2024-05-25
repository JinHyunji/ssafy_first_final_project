package com.ssafy.alarm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.alarm.model.dto.ExerInfo;
import com.ssafy.alarm.model.service.ExerInfoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api-exerinfo")
@Tag(name = "ExerInfoController", description = "운동 정보 조회")
public class ExerInfoController {
	
	private final ExerInfoService exerInfoService;
	
	@Autowired
	public ExerInfoController(ExerInfoService exerInfoService) {
		this.exerInfoService = exerInfoService;
	}
	
	@GetMapping("/exerinfo")
	@Operation(summary="운동 정보 리스트 조회")
	public ResponseEntity<?> getExerInfoList(){
		List<ExerInfo> list = exerInfoService.getExerInfoList();
		
		if(list == null || list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<ExerInfo>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/exerinfo/{id}")
	@Operation(summary="운동 정보 객체 조회")
	public ResponseEntity<?> getExerInfo(@PathVariable("id") @Parameter(description="운동정보 id") int infoId){
		ExerInfo info = exerInfoService.getExerInfo(infoId);
		if(info == null) {
			return new  ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<ExerInfo>(info, HttpStatus.OK);
	}
	
	
	
}
