package com.jinju.memo.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jinju.memo.post.domain.Post;
import com.jinju.memo.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RestController
public class PostRestController {
	
	@Autowired
	private PostService postService;
	
	// 메모 입력 API
	@PostMapping("/post/create")
	public Map<String, String> createMemo(
			@RequestParam("title") String title
			, @RequestParam("contents") String contents
			, HttpSession session){
		
		// 로그인한 사용자의 PK 를 얻어 온다.
		// 로그인한 사용자의 PK 는 세션의 "userId" 키로 저장되어 있다. (로그인 할때 저장) session 값은 어떤 페이지에서도 접근 가능함
		// session 의 값은 session 객체를 통해 손쉽게 다룰 수 있다. session 객체는 HttpServletRequest 로 부터 얻어 온다.
		// HttpServletRequest 을 통해서 객체를 얻어 올 수도 있지만 바로 HttpSession 객체를 달라고 요청하면 줌 손쉽게 쓰는 방법임
		int userId = (Integer)session.getAttribute("userId"); // session 의 값을 저장할 때는 setAttribute 메소드를 통해 값을 세팅을 했음 getAttribute 로 얻어 오고 싶은 key 값 가져오기 
													     	  // 저장될 때 object 객체로 저장이 된거라서 얻어오는 과정에서 다운캐스팅 해서 얻어와야 함
		
		Post post = postService.addPost(userId, title, contents);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(post != null) {
			// 성공
			resultMap.put("result", "success");
		}else {
			// 실패
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}
	
	
}
