package com.jinju.memo.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jinju.memo.user.service.UserService;

// API를 만들기 위한 Controller
// @Controller + @ResponseBody
@RestController
public class UserRestController {

	// 여기서 만들어 낼 것은 API 이고 API는 다 response body 기반으로 객체 리턴해서 JSON 문자열을 response 에 담을 것이다.
	// 그니깐 모든 메소드에 responseBody 어노테이션이 들어가야 하는데 restController 세팅해줌으로써 여기에 있는 메소드에 @ResponseBody 어노테이션을 굳이 붙일 필요 없음
	
	@Autowired
	private UserService userService;
	
	// 회원가입 API
	@PostMapping("/user/join")
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
	
	
}
