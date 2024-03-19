package com.jinju.memo.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jinju.memo.user.domain.User;
import com.jinju.memo.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

// API를 만들기 위한 Controller
// @Controller + @ResponseBody
@RestController
@RequestMapping("/user")
public class UserRestController {

	// 여기서 만들어 낼 것은 API 이고 API는 다 response body 기반으로 객체 리턴해서 JSON 문자열을 response 에 담을 것이다.
	// 그니깐 모든 메소드에 responseBody 어노테이션이 들어가야 하는데 restController 세팅해줌으로써 여기에 있는 메소드에 @ResponseBody 어노테이션을 굳이 붙일 필요 없음
	
	@Autowired
	private UserService userService;
	
	// 회원가입 API
	@PostMapping("/join")
	public Map<String, String> join(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, @RequestParam("name") String name
			, @RequestParam("email") String email){
		
		int count = userService.addUser(loginId, password, name, email);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(count == 1) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}
	
	// 로그인 API
	@PostMapping("/login") // post인 이유는 비밀번호가 있으니깐!!
	public Map<String, String> login(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, HttpServletRequest request){
		
		User user = userService.getUser(loginId, password);
		
		Map<String, String> resultMap = new HashMap<>();
		
		// 조회가 되면 리턴된 값은 객체일거고 조회가 안되면 리턴된 값은 null 일 것이다.
		// 로그인 성공
		if(user != null) {
			
			// 로그인이 성공했을 때 사용자 정보를 session 이라는 곳에 저장할것이다.
			// 그 정보는 sessionId 라는 것을 통해 클라이언트 별로 구분되서 저장이 된다.
			// 그래서 그 해당 클라이언트가 새로운 페이지에 대한 요청을 할 때도 해당 하는 session 값을 활용할 수 있게 된다.
			// 그 session 값을 꺼내서 로그인 된 사용자 정보를 활용할 수 있도록 해준다.
			
			// HttpServletRequest 객체로 부터 Session 객체를 얻어 온다.
			HttpSession session = request.getSession();
			// 세션에 로그인 되었다는 정보를 저장
			// 세션에 사용자 정보를 저장
			// 세션에 사용자 정보가 저장되어 있으면 로그인으로 판단
			session.setAttribute("userId", user.getId()); // 사용자에 대한 primary key, id 저장
			session.setAttribute("userName", user.getName()); // 사용자 이름 저장
			
			
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}
	
	
	
}
