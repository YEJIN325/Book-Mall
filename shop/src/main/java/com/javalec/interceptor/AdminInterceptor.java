package com.javalec.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.javalec.model.MemberVO;

public class AdminInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		MemberVO lvo = (MemberVO)session.getAttribute("member");
		if (lvo == null || lvo.getAdminCk() == 0) {
			response.sendRedirect("/main");
			return false;
		}
		return true;
	}
}
