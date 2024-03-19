package com.jinju.memo.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jinju.memo.user.domain.User;

@Mapper
public interface UserRepository {
	
	// repository 에서 mybatis 기반으로 기능 수행 하는 과정의 메소드 파라미터는 insert 쿼리를 완성시키기 위해 사용되는 값이다
	// insert 쿼리는 xml에서 만들어지고 xml에서 만들어지기 위해서는 변수 자체가 그대로 쓰일 수 없으니깐 Param 어노테이션을 통해 키워드를 매칭 시켜 준다.
	
	public int insertUser(
			@Param("loginId") String loginId
			, @Param("password") String password
			, @Param("name") String name
			, @Param("email") String email);
	
	// 로그인 한 아이디와 패스워드가 일치하는 사용자 정보는 하나이거나 없거나이다. 그래서 리턴 타입은 리스트가 아닌 딱 하나의 사용자 정보, 딱 하나의 행을 조회하는 리턴 타입으로 잡아줄것이다. 그래서 한 행을 저장하기 위한 타입 User 테이블에 매칭이 되는 Entity 클래스가 만들어져야 함 
	public User selectUser(
			@Param("loginId") String loginId
			, @Param("password") String password);
	
	
}

// 해당하는 메소드 호출했을 때 수행 될 쿼리를 작성해야 됨
// 그건 UserRepository 인터페이스를 구현할 수 있도록 하는 Mapper 만들어서 구성해 나가야 되는데 문제는 db 설정이 안되어있음
// 필요한 db설정은 어떤 드라이버를 통해서 어떤 주소로 어떤 아이디 패스워드로 접속할건지에 대한 정보 세팅해줘야 하고
// 그리고 mapper xml을 어떤 위치에 어떤 형식으로 만들어갈건지를 세팅해줘야 한다.
// application.yml에다가 작성 해 나갈거다
//spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
//spring.datasource.url=jdbc:mysql://localhost:3306/dulumary_0102
//spring.datasource.username=root
//spring.datasource.password=root
// 우리가 원래 사용하던 세팅 형식을 바꿔줘야 한다.
// Mapper xml에 대한 경로 설정
// mybatis.mapper-locations=mappers/*Mapper.xml