package com.ssafy.alarm.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.alarm.model.dto.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginPageInterceptor implements HandlerInterceptor {

	// 예시
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		
//		// 세션에서 loginUser 라고 하는 사람의 정보가 admin 과 동일하다면 ...
//		HttpSession session = request.getSession();
//		User loginUser = (User) session.getAttribute("loginUser");
//		if (loginUser == null || !"admin".equals(loginUser.getId())) {
//			response.sendRedirect("list");
//			return false;
//		}
//		
//		return true; // 통과
//	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser != null) {
			return true;
		}
		
		throw new Exception("로그인 필요");
		
		
	}
}
