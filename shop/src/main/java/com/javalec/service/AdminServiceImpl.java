package com.javalec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalec.mapper.AdminMapper;
import com.javalec.model.BookVO;
import com.javalec.model.CateVO;
import com.javalec.model.Criteria;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminMapper adminMapper;
	
	
	// 상품 등록
	@Override
	public void bookEnroll(BookVO book) {		
		adminMapper.bookEnroll(book);
	}
	
	// 카테고리 리스트
	@Override
	public List<CateVO> cateList(){
		return adminMapper.cateList();
	}
	
	// 상품 리스트
	@Override
	public List<BookVO> goodsGetList(Criteria cri){
		return adminMapper.goodsGetList(cri);
	}
	
	// 상품 총 갯수
	@Override
	public int goodsGetTotal(Criteria cri) {
		return adminMapper.goodsGetTotal(cri);
	}
	
	// 상품 조회
	@Override
	public BookVO goodsGetDetail(int bookId) {
		return adminMapper.goodsGetDetail(bookId);
	}
}
