package com.jinju.memo.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinju.memo.common.EncryptUtils;
import com.jinju.memo.user.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public int addUser(String loginId, String password, String name, String email) {
		
		// Service가 하는 역할
		// 전달 받은 데이터를 변경해야 되고 조작해야 되는 상황이 발생 된다 그런것들을 해야하는게 로직이고 Service 가 할 일이다.
		
		// 비밀번호 암호화
		// 암호화 기능은 직접 해당하는 메소드 안에서 구현함으로써 지저분해지는 코드 정리도 어려워지고 다른 곳에서 또 쓰일수도 있기 떄문에 이 놈을 따로 메소드로 만들어서 구성해주는게 좋음
		// 이런식으로 특정한 목적을 가진 기능을 메소드로 만들어야 될 때 꼭 기존에 만들어 놨던 클래스 안에서 꼭 만들지 않아도 되고 도리어 같은 목적을 가진 메소드를 하나로 묶어 놓으면 훨씬 나중에 구별해서 사용하기가 편리
		// 그래서 암호화와 관련된 기능들을 모은 클래스를 새로 하나 만들거임
		String encryptPasswordpassword = EncryptUtils.md5(password);
		return userRepository.insertUser(loginId, encryptPasswordpassword, name, email);
	}
	
}
