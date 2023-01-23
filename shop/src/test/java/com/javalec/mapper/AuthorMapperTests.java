package com.javalec.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.javalec.model.AuthorVO;
import com.javalec.model.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AuthorMapperTests {
	@Autowired
	private AuthorMapper mapper;
	
//	@Test
//	public void authorEnroll() throws Exception {
//		AuthorVO author = new AuthorVO();
//		
//		author.setNationId("01");
//		author.setAuthorName("테스트");
//		author.setAuthorIntro("테스트 소개");
//		
//		mapper.authorEnroll(author);
//	}
	
	
	@Test
//	public void authorGetListTest() throws Exception {
//		Criteria cri = new Criteria(3, 10);
//		cri.setKeyword("폴크");
//		List<AuthorVO> list = mapper.authorGetList(cri);
//		
//		for (int i=0; i<list.size(); i++) {
//			System.out.println("list" + i + "........" + list.get(i));
//		}
//	}
	
//	public void authorGetTotalTest() throws Exception {
//		Criteria cri = new Criteria();
//		cri.setKeyword("폴크");
//		
//		int total = mapper.authorGetTotal(cri);
//		System.out.println("total......" + total);
//		
//	}
	
	// 작가 상세 페이지
	public void authorGetDetailTest() {
		int authorId = 30;
		AuthorVO author = mapper.authorGetDetail(authorId);
		System.out.println("author......" + author);
	}
}
