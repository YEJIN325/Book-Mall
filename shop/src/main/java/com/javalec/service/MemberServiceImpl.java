package com.javalec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalec.mapper.MemberMapper;
import com.javalec.model.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberMapper membermapper; 
	
	@Override
	public void memberJoin(MemberVO member) throws Exception {
		// TODO Auto-generated method stub
		
		membermapper.memberJoin(member);
	}
	
	@Override
	public int idCheck(String memberId) throws Exception {
		return membermapper.idCheck(memberId);
	}
	
	@Override
	public MemberVO memberLogin(MemberVO member) throws Exception {
		return membermapper.memberLogin(member);
	}
}
