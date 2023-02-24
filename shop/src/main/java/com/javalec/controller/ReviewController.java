package com.javalec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javalec.model.Criteria;
import com.javalec.model.ReviewDTO;
import com.javalec.model.ReviewPageDTO;
import com.javalec.service.ReviewService;

@RestController
@RequestMapping("/review")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	// 리뷰 등록
	@PostMapping("/enroll")
	public void enrollReviewPOST(ReviewDTO review) {
		reviewService.enrollReview(review);
	}
	
	// 리뷰 체크
	@PostMapping("/check")
	public String reviewCheckPOST(ReviewDTO review) {
		return reviewService.checkReview(review);
	}
	
	// 리뷰 페이징
	@GetMapping(value="/list", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ReviewPageDTO reviewListPOST(Criteria cri) {
		return reviewService.reviewList(cri);
	}
	
}
