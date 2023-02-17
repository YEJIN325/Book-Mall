package com.javalec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalec.mapper.AttachMapper;
import com.javalec.mapper.CartMapper;
import com.javalec.model.AttachImageVO;
import com.javalec.model.CartDTO;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartMapper cartMapper;
	
	@Autowired
	private AttachMapper attachMapper;
	
	// 장바구니 추가
	@Override
	public int addCart(CartDTO cart) {
		// 장바구니 데이터 체크
		CartDTO checkCart = cartMapper.checkCart(cart);
		
		if (checkCart != null) {
			return 2;
		}
		
		// 장바구니 등록 & 에러 시 0 반환
		try {
			return cartMapper.addCart(cart);
		} catch (Exception e) {
			return 0;
		}
	}
	
	// 장바구니 정보 리스트
	@Override
	public List<CartDTO> getCartList(String memberId){
		List<CartDTO> cart = cartMapper.getCart(memberId);
		
		for (CartDTO dto : cart) {
			dto.initSaleTotal();
			
			// 이미지 정보 얻기
			int bookId = dto.getBookId();
			List<AttachImageVO> imageList = attachMapper.getAttachList(bookId);
			dto.setImageList(imageList);
			
		}
		
		return cart;
	}
	
	// 카트 수량 수정
	@Override
	public int modifyCount(CartDTO cart) {
		return cartMapper.modifyCount(cart);
	}
	
	// 카트 삭제
	@Override
	public int deleteCart(int cartId) {
		return cartMapper.deleteCart(cartId);
	}
}
