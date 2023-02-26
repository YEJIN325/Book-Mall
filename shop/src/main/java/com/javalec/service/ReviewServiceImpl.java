package com.javalec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalec.mapper.ReviewMapper;
import com.javalec.model.Criteria;
import com.javalec.model.PageDTO;
import com.javalec.model.ReviewDTO;
import com.javalec.model.ReviewPageDTO;
import com.javalec.model.UpdateReviewDTO;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewMapper reviewMapper;
	
	// 리뷰 등록
	@Override
	public int enrollReview(ReviewDTO review) {
		int result = reviewMapper.enrollReview(review);
		setRating(review.getBookId());
		return result;
	}
	
	// 리뷰 존재 체크
	@Override
	public String checkReview(ReviewDTO review) {
		Integer result = reviewMapper.checkReview(review);
		
		if (result == null) {
			return "0";
		} else {
			return "1";
		}
	}
	
	// 리뷰 페이징
	@Override
	public ReviewPageDTO reviewList(Criteria cri) {
		ReviewPageDTO dto = new ReviewPageDTO();
		
		dto.setList(reviewMapper.getReviewList(cri));
		dto.setPageInfo(new PageDTO(cri, reviewMapper.getReviewTotal(cri.getBookId())));
		
		return dto;
	}
	
	// 리뷰 수정
	@Override
	public int updateReview(ReviewDTO review) {
		int result = reviewMapper.updateReview(review);
		setRating(review.getBookId());
		return result;
	}
	
	// 리뷰 한 개 정보
	@Override
	public ReviewDTO getUpdateReview(int reviewId) {
		return reviewMapper.getUpdateReview(reviewId);
	}
	
	// 리뷰 삭제
	@Override
	public int deleteReview(ReviewDTO review) {
		int result = reviewMapper.deleteReview(review.getReviewId());
		setRating(review.getBookId());
		return result;
	}
	
	// 평점 계산
	public void setRating(int bookId) {
		Double ratingAvg = reviewMapper.getRatingAverage(bookId);
		
		if (ratingAvg == null) {
			ratingAvg = 0.0;
		}
		
		ratingAvg = (double)(Math.round(ratingAvg*10));
		ratingAvg = ratingAvg / 10;
		
		UpdateReviewDTO urd = new UpdateReviewDTO();
		urd.setBookId(bookId);
		urd.setRatingAvg(ratingAvg);
		
		reviewMapper.updateRating(urd);
	}
}
