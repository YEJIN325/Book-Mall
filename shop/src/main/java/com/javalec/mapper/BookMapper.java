package com.javalec.mapper;

import java.util.List;

import com.javalec.model.BookVO;
import com.javalec.model.Criteria;

public interface BookMapper {
	
	// 상품 검색
	public List<BookVO> getGoodsList(Criteria cri);
	
	// 상품 총 갯수
	public int goodsGetTotal(Criteria cri);
	
	// 작가 id 리스트 요청
	public String[] getAuthorIdList(String keyword);
	
}