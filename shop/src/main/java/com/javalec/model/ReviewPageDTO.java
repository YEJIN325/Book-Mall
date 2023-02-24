package com.javalec.model;

import java.util.List;

public class ReviewPageDTO {
	
	// 리뷰 리스트 정보
	List<ReviewDTO> list;
	
	// 페이지 정보
	PageDTO pageInfo;

	public List<ReviewDTO> getList() {
		return list;
	}

	public void setList(List<ReviewDTO> list) {
		this.list = list;
	}

	public PageDTO getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageDTO pageInfo) {
		this.pageInfo = pageInfo;
	}

	@Override
	public String toString() {
		return "ReviewPageDTO [list=" + list + ", pageInfo=" + pageInfo + "]";
	}
	
}
