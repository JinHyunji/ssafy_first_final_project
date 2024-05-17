package com.ssafy.alarm.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.alarm.model.dto.Alarm;
import com.ssafy.alarm.model.dto.User;
import com.ssafy.alarm.model.service.AlarmService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api-alarm")
@Tag(name = "AlarmRestController", description = "알람 관리 기능")
public class AlarmRestController {
	
	private final AlarmService alarmService;

	public AlarmRestController(AlarmService alarmService) {
		this.alarmService = alarmService;
	}
	
	// 알람 생성
	@PostMapping("/alarm")
	@Operation(summary = "알람 생성")
	public ResponseEntity<?> createAlarm(@RequestBody Alarm alarm) {
		int result = alarmService.createAlarm(alarm);
		
		if (result > 0) {
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	// 알람 삭제
	@DeleteMapping("/alarm/{alarmId}")
	@Operation(summary = "알람 삭제")
	public ResponseEntity<?> removeAlarm(@PathVariable("alarmId") int alarmId) {
		int result = alarmService.removeAlarm(alarmId);
		
		if (result > 0) {
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	// 알람 수정
	@PutMapping("/alarm")
	@Operation(summary = "알람 수정")
	public ResponseEntity<?> modifyAlarm(@RequestBody Alarm alarm) {
		int result = alarmService.modifyAlarm(alarm);
		
		if (result > 0) {
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	// 알람 하나 가져오기
	@GetMapping("/alarm/{alarmId}")
	@Operation(summary = "알람 하나 가져오기")
	public ResponseEntity<?> getAlarm(@PathVariable("alarmId") int alarmId) {
		Alarm alarm = alarmService.getAlarm(alarmId);
		
		if (alarm != null) {
			return ResponseEntity.ok(alarm);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	
	// 알람 전체 가져오기
	@GetMapping("/alarm")
	@Operation(summary = "알람 전체 가져오기")
	public ResponseEntity<?> getAlarmList(HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		
		List<Alarm> list = alarmService.getAlarmListByUserId(loginUser.getUserId());
		
		if (list.size() > 0 && !list.isEmpty()) {
			return ResponseEntity.ok(list);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	
}
