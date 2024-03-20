package com.jinju.memo.post.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinju.memo.post.domain.Post;
import com.jinju.memo.post.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;

	// JPA는 이미 만들어져 있는 repository 메소드를 호출해 주기만 하면 된다.
	public Post addPost(int userId, String title, String contents) {
		
		// Entity 클래스에 객체로 한 행을 저장할 데이터를 멤버변수에 저장 해서 그 Entity 객체로 저장을 시킨다. 그래서 Entity 클래스 객체 Post 객체로 만들거고
		// builder 패턴으로 객체 생성할것이다 builder 패턴은 어떻게 만들어 낸다?? builder 뒤에 저장하고 싶은 멤버변수 값을 멤버변수 이름 그대로 써주고 괄호 안에다가 저장할 값을 넣어준다. 
		Post post = Post.builder()
					.userId(userId) // 여기 저장 할 값은 파라미터로 전달 받은 userId 이다.
					.title(title)
					.contents(contents)
					.build();
		// 필요한 값 다 채웠으면 마지막에 build 라는 메소드를 호출해주면 이제 Post 객체가 생성이 되고 .으로 나열한 멤버 변수 값들이 세팅이 되서 객체가 리턴 될거임
	
		// save 라는 메소드를 호출해서 인자로 Entity 클래스 객체를 전달하면 매칭되는 멤버 변수에 값이 해당 하는 컬럼에 저장이 될 것이다.
		post = postRepository.save(post); // 저장할 값을 저장한 객체 post 넣기 / save 는 저장 이후에 그 행의 정보를 다시 얻어 와서 그 행의 데이터를 Entity 클래스 객체로 값을 리턴해준다.
		return post;
		
	}
	
	// 특정 사용자의 메모 목록을 돌려준다.
	public List<Post> getPostList(int userId){
		
		// List<Post> postList = postRepository.findAll(); // 해당하는 테이블에 모든 행을 조회해온다.
		List<Post> postList = postRepository.findByUserIdOrderByIdDesc(userId);
		
		return postList;
	}
	
	public Post getPost(int id) {
		
		Optional<Post> optionalPost = postRepository.findById(id); // 딱 한 행만 지칭해서 리턴하는 형태에서는 그냥 그 객체가 아니라 Optional 로 한번 감싸진 객체가 전달이 된다.
		
		Post post = optionalPost.orElse(null); // null 일때 어떤 값을 리턴할지를 인자로 주는것이다 얘가 이제 결국 Post 객체로 리턴이 됨
		return post;
	}
	
	
}
