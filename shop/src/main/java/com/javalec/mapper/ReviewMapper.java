package com.javalec.mapper;

import java.util.List;

import com.javalec.model.Criteria;
import com.javalec.model.ReviewDTO;

public interface ReviewMapper {
	// 리뷰 등록
	public int enrollReview(ReviewDTO review);
	
	// 리뷰 존재 체크
	public Integer checkReview(ReviewDTO review);
	
	// 리뷰 페이징
	public List<ReviewDTO> getReviewList(Criteria cri);
	
	// 리뷰 총 개수
	public int getReviewTotal(int bookId);
	
}
