package com.javalec.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.javalec.model.ReviewDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ReviewMapperTests {
	
	@Autowired
	private ReviewMapper mapper;
	
	// 리뷰 등록
	@Test
	public void reviewEnrollTest() {
		String id = "admin";
		int bookId = 95;
		double rating = 3.5;
		String content= "리뷰 테스트";
		
		ReviewDTO review = new ReviewDTO();
		review.setBookId(bookId);
		review.setMemberId(id);
		review.setRating(rating);
		review.setContent(content);
		
		mapper.enrollReview(review);
		
	}
	
}
