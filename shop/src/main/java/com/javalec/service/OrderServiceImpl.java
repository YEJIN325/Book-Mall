package com.javalec.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javalec.mapper.AttachMapper;
import com.javalec.mapper.BookMapper;
import com.javalec.mapper.CartMapper;
import com.javalec.mapper.MemberMapper;
import com.javalec.mapper.OrderMapper;
import com.javalec.model.AttachImageVO;
import com.javalec.model.BookVO;
import com.javalec.model.CartDTO;
import com.javalec.model.MemberVO;
import com.javalec.model.OrderDTO;
import com.javalec.model.OrderItemDTO;
import com.javalec.model.OrderPageItemDTO;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private AttachMapper attachMapper;
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private CartMapper cartMapper;
	
	@Autowired
	private BookMapper bookMapper;
	
	
	@Override
	public List<OrderPageItemDTO> getGoodsInfo(List<OrderPageItemDTO> orders) {
		List<OrderPageItemDTO> result = new ArrayList<OrderPageItemDTO>();
		
		for (OrderPageItemDTO ord : orders) {
			OrderPageItemDTO goodsInfo = orderMapper.getGoodsInfo(ord.getBookId());
			goodsInfo.setBookCount(ord.getBookCount());
			goodsInfo.initSaleTotal();
			
			List<AttachImageVO> imageList = attachMapper.getAttachList(goodsInfo.getBookId());
			goodsInfo.setImageList(imageList);
			
			result.add(goodsInfo);
		}
		
		return result;
	}
	
	@Override
	@Transactional
	public void order(OrderDTO ord) {
		// 회원 정보
		MemberVO member = memberMapper.getMemberInfo(ord.getMemberId());
		// 주문 정보
		List<OrderItemDTO> ords = new ArrayList<>();
		for (OrderItemDTO oit : ord.getOrders()) {
			OrderItemDTO orderItem = orderMapper.getOrderInfo(oit.getBookId());
			orderItem.setBookCount(oit.getBookCount());
			orderItem.initSaleTotal();
			ords.add(orderItem);
		}
		ord.setOrders(ords);
		ord.getOrderPriceInfo();
		
		// orderId 만들기(회원ID + _년도 월 일 분)
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("_yyyyMMddmm");
		String orderId = member.getMemberId() + format.format(date);
		ord.setOrderId(orderId);
		
		// db 넣기
		orderMapper.enrollOrder(ord);
		for (OrderItemDTO oit : ord.getOrders()) {
			oit.setOrderId(orderId);
			orderMapper.enrollOrderItem(oit);
		}
		
		// 비용 차감
		int calMoney = member.getMoney();
		calMoney -= ord.getOrderFinalSalePrice();
		member.setMoney(calMoney);
		
		// 포인트 차감
		int calPoint = member.getPoint();
		calPoint = calPoint - ord.getUsePoint() + ord.getOrderSavePoint();
		member.setPoint(calPoint);
		
		// 변동 돈, 포인트 DB 적용
		orderMapper.deductMoney(member);
		
		// 재고 변동 적용
		for (OrderItemDTO oit : ord.getOrders()) {
			BookVO book = bookMapper.getGoodsInfo(oit.getBookId());
			book.setBookStock(book.getBookStock() - oit.getBookCount());
			orderMapper.deductStock(book);
		}
		
		// 장바구니 제거
		for (OrderItemDTO oit : ord.getOrders()) {
			CartDTO dto = new CartDTO();
			dto.setMemberId(ord.getMemberId());
			dto.setBookId(oit.getBookId());
			
			cartMapper.deleteOrderCart(dto);
		}
	}
}
