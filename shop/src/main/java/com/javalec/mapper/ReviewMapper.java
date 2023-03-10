package com.javalec.mapper;

import java.util.List;

import com.javalec.model.Criteria;
import com.javalec.model.ReviewDTO;
import com.javalec.model.UpdateReviewDTO;

public interface ReviewMapper {
	// 리뷰 등록
	public int enrollReview(ReviewDTO review);
	
	// 리뷰 존재 체크
	public Integer checkReview(ReviewDTO review);
	
	// 리뷰 페이징
	public List<ReviewDTO> getReviewList(Criteria cri);
	
	// 리뷰 총 개수
	public int getReviewTotal(int bookId);
	
	// 리뷰 수정
	public int updateReview(ReviewDTO review);
	
	// 리뷰 한 개 정보
	public ReviewDTO getUpdateReview(int reviewId);
	
	// 리뷰 삭제
	public int deleteReview(int reviewId);
	
	// 평점 평균 구하기
	public Double getRatingAverage(int bookId);
	
	// 평점 평균 반영하기
	public int updateRating(UpdateReviewDTO dto);
	
}
