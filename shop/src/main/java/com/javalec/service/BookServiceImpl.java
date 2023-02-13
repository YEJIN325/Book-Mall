package com.javalec.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalec.mapper.AttachMapper;
import com.javalec.mapper.BookMapper;
import com.javalec.model.AttachImageVO;
import com.javalec.model.BookVO;
import com.javalec.model.CateFilterVO;
import com.javalec.model.CateVO;
import com.javalec.model.Criteria;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookMapper bookMapper;
	
	@Autowired
	private AttachMapper attachMapper;
	
	// 상품 검색
	@Override
	public List<BookVO> getGoodsList(Criteria cri) {
		
		String type = cri.getType();
		String[] typeArr = type.split("");
		String[] authorArr = bookMapper.getAuthorIdList(cri.getKeyword());

		if (type.equals("A") || type.equals("AC") || type.equals("AT") || type.equals("ACT")) {
			if (authorArr.length == 0) {
				return new ArrayList();
			}
		}
	
		for (String t : typeArr) {
			if (t.equals("A")) {
				cri.setAuthorArr(authorArr);
			}
		}
		
		List<BookVO> list = bookMapper.getGoodsList(cri);
		list.forEach(book -> {
			int bookId = book.getBookId();
			List<AttachImageVO> imageList = attachMapper.getAttachList(bookId);
			book.setImageList(imageList);
		});
		
		return list;
	}
	
	// 상품 총 갯수
	@Override
	public int goodsGetTotal(Criteria cri) {
		return bookMapper.goodsGetTotal(cri);
	}

	// 국내 카테고리 리스트
	@Override
	public List<CateVO> getDomCateCode() {
		return bookMapper.getDomCateCode();
	}
	
	// 외국 카테고리 리스트
	@Override
	public List<CateVO> getAbCateCode() {
		return bookMapper.getAbCateCode();
	}
	
	// 검색 결과 카테고리 필터 정보
	@Override
	public List<CateFilterVO> getCateInfoList(Criteria cri) {
		List<CateFilterVO> filterInfoList = new ArrayList<CateFilterVO>();
		
		String[] typeArr = cri.getType().split("");
		String[] authorArr;
		
		for (String type: typeArr) {
			if (type.equals("A")) {
				authorArr = bookMapper.getAuthorIdList(cri.getKeyword());
				if (authorArr.length == 0) {
					return filterInfoList;
				}
				cri.setAuthorArr(authorArr);
			}
		}
		
		String[] cateList = bookMapper.getCateList(cri);
		String tempCateCode = cri.getCateCode();
		for (String cateCode : cateList) {
			cri.setCateCode(cateCode);
			CateFilterVO filterInfo = bookMapper.getCateInfo(cri);
			filterInfoList.add(filterInfo);
		}
		
		cri.setCateCode(tempCateCode);
		
		return filterInfoList;
	}
	
}
