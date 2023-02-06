package com.javalec.service;

import java.util.List;

import com.javalec.model.AttachImageVO;
import com.javalec.model.BookVO;
import com.javalec.model.CateVO;
import com.javalec.model.Criteria;

public interface AdminService {
	// 상품 등록
	public void bookEnroll(BookVO book);
	
	// 카테고리 리스트
	public List<CateVO> cateList();
	
	// 상품 리스트
	public List<BookVO> goodsGetList(Criteria cri);
	
	// 상품 총 갯수
	public int goodsGetTotal(Criteria cri);
	
	// 상품 조회
	public BookVO goodsGetDetail(int bookId);
	
	// 상품 수정
	public int goodsModify(BookVO book);
	
	// 상품 삭제
	public int goodsDelete(int bookId);
	
	// 지정 상품 이미지 정보 얻기
	public List<AttachImageVO> getAttachInfo(int bookId);
}
