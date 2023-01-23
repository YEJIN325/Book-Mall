package com.javalec.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalec.mapper.AuthorMapper;
import com.javalec.model.AuthorVO;
import com.javalec.model.Criteria;

@Service
public class AuthorServiceImpl implements AuthorService {
	
	@Autowired
	private AuthorMapper authorMapper;
	
	private static final Logger log = LoggerFactory.getLogger(AuthorServiceImpl.class);
	
	@Override
	public void authorEnroll(AuthorVO author) throws Exception {
		
		authorMapper.authorEnroll(author);
	}
	
	@Override
	public List<AuthorVO> authorGetList(Criteria cri) throws Exception {
		log.info("(service)authorGetList()......" + cri);
		return authorMapper.authorGetList(cri);
	}
	
	@Override
	public int authorGetTotal(Criteria cri) throws Exception {
		log.info("(service)authorGetTotal()......" + cri);
		return authorMapper.authorGetTotal(cri);
	}
	
	@Override
	public AuthorVO authorGetDetail(int authorId) throws Exception {
		log.info("authorGetDetail......" + authorId);
		return authorMapper.authorGetDetail(authorId);
	}
	
	@Override
	public int authorModify(AuthorVO author) throws Exception {
		return authorMapper.authorModify(author);
	}
}
