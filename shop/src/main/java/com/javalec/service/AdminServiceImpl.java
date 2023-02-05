package com.javalec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javalec.mapper.AdminMapper;
import com.javalec.model.AttachImageVO;
import com.javalec.model.BookVO;
import com.javalec.model.CateVO;
import com.javalec.model.Criteria;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminMapper adminMapper;
	
	
	// 상품 등록
	@Transactional
	@Override
	public void bookEnroll(BookVO book) {	
		
		adminMapper.bookEnroll(book);
		
		if (book.getImageList() == null || book.getImageList().size() <= 0) {
			return;
		}
		
		for (AttachImageVO attach: book.getImageList()) {
			attach.setBookId(book.getBookId());
			adminMapper.imageEnroll(attach);
		}
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
	
	// 상품 정보 수정
	@Transactional
	@Override
	public int goodsModify(BookVO book) {
		int result = adminMapper.goodsModify(book);
		
		List<AttachImageVO> imageList = book.getImageList();
		int bookId = book.getBookId();
		
		if (result == 1 && imageList != null && imageList.size() > 0) {
			
			adminMapper.deleteImageAll(bookId);
			
			imageList.forEach(attach -> {
				attach.setBookId(bookId);
				adminMapper.imageEnroll(attach);
			});
		}
		
		return result;
	}
	
	// 상품 삭제
	@Override
	public int goodsDelete(int bookId) {
		return adminMapper.goodsDelete(bookId);
	}
	
}
