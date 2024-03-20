package com.jinju.memo.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jinju.memo.post.domain.Post;

public interface PostRepository extends JpaRepository<Post,Integer>{ // JpaRepository에는 두 가지의 제너릭이 잡혀야 한다. 첫 번째는 해당 하는 다룰 대상이 되는 테이블에 Entity 클래스가 첫 번째 제너릭 클래스로 잡혀야 함.
															   		 // 제너릭 두 번째는 해당 하는 테이블에 프라이머리 키에 대한 타입이다.
	
	// userId 컬럼 값이 일치하는 행 조회
	// WHERE `userId` = #{userId} ORDER BY `id` DESC
	public List<Post> findByUserIdOrderByIdDesc(int userId);
	
}
