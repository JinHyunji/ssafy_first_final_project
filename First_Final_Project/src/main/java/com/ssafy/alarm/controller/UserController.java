package com.ssafy.alarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.alarm.model.dto.User;
import com.ssafy.alarm.model.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api-user")
@Tag(name = "회원 관리 기능")
@CrossOrigin("*")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/signup")
	@Operation(summary = "회원가입")
	public ResponseEntity<?> signup(@RequestBody User user) {
		if (userService.getUserByID(user.getUserId()) != null) {
			return ResponseEntity.notFound().build();
		}
		int result = userService.signup(user);
		return ResponseEntity.ok(result);
	}

	@PostMapping("/login")
	@Operation(summary = "로그인")
	public ResponseEntity<?> login(@RequestBody User user, HttpSession session) {
		if(session.getAttribute("loginUser") != null) {
			return ResponseEntity.notFound().build();
		}
		User loginUser = userService.login(user.getUserId(), user.getPassword());
		if (loginUser == null) {
			return ResponseEntity.notFound().build();
		}

		session.setAttribute("loginUser", loginUser);

		return ResponseEntity.ok("login success");
	}

	@GetMapping("/logout")
	@Operation(summary = "로그아웃")
	public ResponseEntity<String> logout(HttpSession session) {
		if (session.getAttribute("loginUser") == null) {
			return ResponseEntity.notFound().build();
		}
		session.invalidate();
		return ResponseEntity.ok("logout success");
	}

}
