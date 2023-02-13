package com.javalec.service;

import java.util.List;

import com.javalec.model.BookVO;
import com.javalec.model.CateFilterVO;
import com.javalec.model.CateVO;
import com.javalec.model.Criteria;

public interface BookService {
	
	// 상품 검색
	public List<BookVO> getGoodsList(Criteria cri);
	
	// 상품 총 갯수
	public int goodsGetTotal(Criteria cri);
	
	// 국내 카테고리 리스트
	public List<CateVO> getDomCateCode();
	
	// 외국 카테고리 리스트
	public List<CateVO> getAbCateCode();
	
	// 검색 결과 카테고리 필터 정보
	public List<CateFilterVO> getCateInfoList(Criteria cri);
	
	// 상품 정보
	public BookVO getGoodsInfo(int bookId);
	
}
