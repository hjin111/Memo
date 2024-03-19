package com.jinju.memo.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@GetMapping("/user/join-view")
	public String inputJoin() {
		return "user/join";
	}
	
	@GetMapping("/user/login-view")
	public String inputLogin() {
		return "user/login";
	}
	
	@GetMapping("/user/logout")
	public String logout(HttpServletRequest request) {
		// 세션에 사용자 정보를 저장하면 로그인
		// 세션에 저장된 사용자 정보를 제거한다. => 로그아웃
		HttpSession session = request.getSession(); // session 객체 얻어오기
		// userId, userName 키로 저장해놨음 이걸 지우자
		session.removeAttribute("userId");
		session.removeAttribute("userName");
		
		// 로그인 페이지로 리다이렉트( 리타이렉트는 response body가 없는 상태에서 간단하게 문자열 형태로 redirect:하고 리다이렉트 시킬 주소만 넣어주면 된다 )
		return "redirect:/user/login-view";
		
		
	}
	
}
