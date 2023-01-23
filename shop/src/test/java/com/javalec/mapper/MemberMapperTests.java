package com.javalec.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.javalec.model.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberMapperTests {
	
	@Autowired
	private MemberMapper membermapper;
	
	/*
	@Test
	public void memberJoin() throws Exception {
		MemberVO member = new MemberVO();
		
		member.setMemberId("test");
		member.setMemberPw("test");
		member.setMemberName("test");
		member.setMemberMail("test");
		member.setMemberAddr1("test");
		member.setMemberAddr2("test");
		member.setMemberAddr3("test");
		
		membermapper.memberJoin(member);
	}
	*/
	
	// 아이디 중복 검사
	/*
	@Test
	public void memberIdChk() throws Exception{
		String id = "admin";  // 존재하는 아이디
		String id2 = "test123";  // 존재하지 않는 아이디
		membermapper.idCheck(id);
		membermapper.idCheck(id2);
		
	}
	*/
	
	// 로그인 쿼리 mapper 메서드 테스트
	@Test
	public void memberLogin() throws Exception{
		MemberVO member = new MemberVO();
//		member.setMemberId("test");
//		member.setMemberPw("test");
		
		member.setMemberId("test111");
		member.setMemberPw("test111");
		
		membermapper.memberLogin(member);
		System.out.println("결과 값 : " + membermapper.memberLogin(member));
		
	}
}
