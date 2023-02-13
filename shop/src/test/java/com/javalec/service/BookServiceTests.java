package com.javalec.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.javalec.model.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BookServiceTests {
	
	@Autowired
	BookService service;
	
	/*
	@Test
	public void getCateInfoListTest1() {
		Criteria cri = new Criteria();
		
		String type = "TC";
		//String keyword = "테스트";
		String keyword = "없음";
		String cateCode = "107003";
		
		cri.setType(type);
		cri.setKeyword(keyword);
		cri.setCateCode(cateCode);
		
		System.out.println("List<CateFilterVO> : " + service.getCateInfoList(cri));
	}
	*/
	
	/*
	@Test
	public void getCateInfoListTest2() {
		Criteria cri = new Criteria();
		
		String type = "AC";
		String keyword = "폴";
		//String keyword = "없음";
		String cateCode = "107003";
		
		cri.setType(type);
		cri.setKeyword(keyword);
		cri.setCateCode(cateCode);
		
		System.out.println("List<CateFilterVO> : " + service.getCateInfoList(cri));
	}
	*/
	
	/*
	@Test
	public void getCateInfoListTest3() {
		Criteria cri = new Criteria();
		
		String type = "T";
		//String keyword = "테스트";
		String keyword = "없음";
		
		cri.setType(type);
		cri.setKeyword(keyword);
		
		System.out.println("List<CateFilterVO> : " + service.getCateInfoList(cri));
	}
	*/
	
	@Test
	public void getCateInfoListTest4() {
		Criteria cri = new Criteria();
		
		String type = "AC";
		//String keyword = "폴";
		String keyword = "없음";
		
		cri.setType(type);
		cri.setKeyword(keyword);
		
		System.out.println("List<CateFilterVO> : " + service.getCateInfoList(cri));
	}
}
