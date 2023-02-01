package com.javalec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalec.mapper.AttachMapper;
import com.javalec.model.AttachImageVO;

@Service
public class AttachServiceImpl implements AttachService {

	@Autowired
	private AttachMapper attachMapper;
	
	// 이미지 데이터 반환
	@Override
	public List<AttachImageVO> getAttachList(int bookId) {
		
		return attachMapper.getAttachList(bookId);
	}

}
