package com.jinju.memo.post.domain;

import java.util.Date;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder // 객체 생성을 직접 해야 하는 케이스도 생기는데 그때 빌더 패턴을 통해서 만들어주면 편했기 때문에 빌더 패턴을 활용하기 위한 어노테이션 붙이기 
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor //모든 멤버변수를 파라미터로 전달 받고 세팅해주는 생성자가 필요하다.
@Getter
@Table(name="post") // 테이블 어노테이션을 통해서 대상이 되는 테이블 이름을 정확히 명시해준다.
@Entity
public class Post {
	
	@Id // primary key 에 대응되는 멤버변수에는 Id 라는 어노테이션 붙이기
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	// 카멜 케이스로 세팅된 컬럼 이름들은 컬럼어노테이션을 통해 정확히 매칭시켜 준다.
	@Column(name="userId")
	private int userId;
	
	private String title;
	private String contents;
	
	@Column(name="imagePath")
	private String imagePath;
	
	@UpdateTimestamp // 자동으로 현재 시간 잡히기
	@Column(name="createdAt", updatable=false) // createdAt은 수정될 때는 해당하는 날짜 시간 정보가 업데이트 되면 안됨 그래서 컬럼 어노테이션에다가 updatable 이라는 값을 false 로 주기
	private Date createdAt;
	
	@UpdateTimestamp
	@Column(name="updatedAt")
	private Date updatedAt;
	
}
