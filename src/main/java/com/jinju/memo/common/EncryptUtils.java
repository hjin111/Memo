package com.jinju.memo.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {
	
	// static 메소드는 멤버 변수를 사용 하지 않는 그냥 이 메소드 자체에서 순수하게 기능을 구현한 메소드를 static 메소드로 만들 수 있다.
	// static 메소드를 만들면 해당 하는 메소드는 객체 생성 없이 사용 가능 함
	
	// 문자열 암호화 기능
	// Hashing - md5(알고리즘)
	// 암호화할 문자열을 전달 받고, 암호화된 문자열을 돌려주는 기능
	public static String md5(String message) {
		
		String result = "";
		try {
			
			// MessageDigest 라는 객체를 직접 생성하는게 아니라 getInstance 매소드를 통해서 객체를 얻어오는 형태로 사용
			MessageDigest md = MessageDigest.getInstance("md5");
			// 암호화를 위해서 문자열을 byte 타입 데이터로 변환
			byte[] bytes = message.getBytes();
			md.update(bytes);
			
			// 암호화된 결과
			byte[] digest = md.digest();
			
			// byte 배열을 문자열로 변경
			// 16진수 숫자로 변경
			for(int i = 0; i < digest.length; i++) {
				// byte 값을 16진수로 변환하고 이걸 문자열로 만들어가는 과정이 필요
				// 비트연산 &
				// 001010
				// 111111
				// 001010
				// digest[i] & 0xff 이 과정을 거치면 데이터가 16진수 형태로 만들어진다.
				result += Integer.toHexString(digest[i] & 0xff);   
			}
			
		} catch (NoSuchAlgorithmException e) { //NoSuchAlgorithmException은 인자로 전달받은 md5 이 알고리즘이 없는 경우에 exception이 발생
			e.printStackTrace();
		}
		
		return result;
	}

}
