package com.javalec.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.javalec.model.CartDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class CartServiceTests {
	
	@Autowired
	private CartService service;
	
	// 등록 테스트
	@Test
	public void addCartTest() {
		String memberId = "admin";
		// int bookId = 60;  // 이미 존재하는 bookId
		// int bookId = 30;  // 등록되지 않은 bookId
		int bookId = 0;  // 존재하지 않는 bookId
		int count = 5;
		
		CartDTO dto = new CartDTO();
		dto.setMemberId(memberId);
		dto.setBookId(bookId);
		dto.setBookCount(count);
		
		int result = service.addCart(dto);
		
		System.out.println("** result : " + result);
		
	}
}
