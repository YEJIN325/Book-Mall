package com.javalec.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReviewDTO {
	private int reviewId;
	
	private int bookId;
	
	private String memberId;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")
	private Date regDate;
	
	private String content;
	
	private double rating;

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "ReviewDTO [reviewId=" + reviewId + ", bookId=" + bookId + ", memberId=" + memberId + ", regDate="
				+ regDate + ", content=" + content + ", rating=" + rating + "]";
	}
	
}
