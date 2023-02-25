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
	
	// 리뷰 수정
	public int updateReview(ReviewDTO review);
	
	// 리뷰 한 개 정보
	public ReviewDTO getUpdateReview(int reviewId);
	
	// 리뷰 삭제
	public int deleteReview(ReviewDTO review);
	
}
