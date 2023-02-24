package com.javalec.mapper;

import java.util.List;

import com.javalec.model.BookVO;
import com.javalec.model.CateFilterVO;
import com.javalec.model.CateVO;
import com.javalec.model.Criteria;

public interface BookMapper {
	
	// 상품 검색
	public List<BookVO> getGoodsList(Criteria cri);
	
	// 상품 총 갯수
	public int goodsGetTotal(Criteria cri);
	
	// 작가 id 리스트 요청
	public String[] getAuthorIdList(String keyword);
	
	// 국내 카테고리 리스트
	public List<CateVO> getDomCateCode();
	
	// 외국 카테고리 리스트
	public List<CateVO> getAbCateCode();
	
	// 검색 대상 카테고리 리스트
	public String[] getCateList(Criteria cri);
	
	// 카테고리 정보(+검색대상 갯수)
	public CateFilterVO getCateInfo(Criteria cri);
	
	// 상품 정보
	public BookVO getGoodsInfo(int bookId);
	
	// 상품 이름 얻기
	public BookVO getBookName(int bookId);
	
}
