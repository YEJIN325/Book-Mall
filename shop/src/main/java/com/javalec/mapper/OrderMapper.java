package com.javalec.mapper;

import java.util.List;

import com.javalec.model.BookVO;
import com.javalec.model.MemberVO;
import com.javalec.model.OrderDTO;
import com.javalec.model.OrderItemDTO;
import com.javalec.model.OrderPageItemDTO;

public interface OrderMapper {
	
	// 주문 상품 정보 (주문 페이지)
	public OrderPageItemDTO getGoodsInfo(int bookId);
	
	// 주문 상품 정보 (주문 처리)
	public OrderItemDTO getOrderInfo(int bookId);
	
	// 주문 테이블 등록
	public int enrollOrder(OrderDTO ord);
	
	// 주문 아이템 테이블 등록
	public int enrollOrderItem(OrderItemDTO ordi);
	
	// 주문 금액 차감
	public int deductMoney(MemberVO member);
	
	// 주문 재고 차감
	public int deductStock(BookVO book);
	
	// 주문 취소
	public int orderCancel(String orderId);
	
	// 주문 상품 정보 (주문 취소)
	public List<OrderItemDTO> getOrderItemInfo(String orderId);
	
	// 주문 정보 (주문 취소)
	public OrderDTO getOrder(String orderId);
	
	
}
