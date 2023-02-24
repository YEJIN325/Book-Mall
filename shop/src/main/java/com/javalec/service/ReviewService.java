package com.javalec.service;

import com.javalec.model.Criteria;
import com.javalec.model.ReviewDTO;
import com.javalec.model.ReviewPageDTO;

public interface ReviewService {
	
	// 리뷰 등록
	public int enrollReview(ReviewDTO review);
	
	// 리뷰 존재 체크
	public String checkReview(ReviewDTO review);
	
	// 리뷰 페이징
	public ReviewPageDTO reviewList(Criteria cri);
	
}
