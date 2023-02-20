package com.javalec.service;

import java.util.List;

import com.javalec.model.OrderDTO;
import com.javalec.model.OrderPageItemDTO;

public interface OrderService {
	
	// 주문 정보
	public List<OrderPageItemDTO> getGoodsInfo(List<OrderPageItemDTO> orders);
	
	// 주문
	public void order(OrderDTO ord);
	
}
