package com.javalec.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javalec.model.AttachImageVO;
import com.javalec.model.BookVO;
import com.javalec.model.Criteria;
import com.javalec.model.PageDTO;
import com.javalec.service.AttachService;
import com.javalec.service.BookService;


@Controller
public class BookController {
	
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private AttachService attachService;
	
	@Autowired
	private BookService bookService;
	
	// 메인 페이지 이동
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public void mainPageGET(Model model) {
		logger.info("메인 페이지 진입");
		
		model.addAttribute("cate1", bookService.getDomCateCode());
		model.addAttribute("cate2", bookService.getAbCateCode());

	}
	
	// 이미지 출력
	@GetMapping("/display")
	public ResponseEntity<byte[]> getImage(String fileName){
		File file = new File("c:\\upload\\" + fileName);
		ResponseEntity<byte[]> result = null;
		
		try {	
			HttpHeaders header = new HttpHeaders();
			
			header.add("Content-type", Files.probeContentType(file.toPath()));
			
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 이미지 정보 반환
	@GetMapping(value="/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<AttachImageVO>> getAttachList(int bookId){
		
		return new ResponseEntity<List<AttachImageVO>>(attachService.getAttachList(bookId), HttpStatus.OK);
	}
	
	// 상품 검색
	@GetMapping("/search")
	public String searchGoodsGET(Criteria cri, Model model) {
		List<BookVO> list = bookService.getGoodsList(cri);
		if (!list.isEmpty()) {
			model.addAttribute("list", list);
			logger.info("list : " + list);
		} else {
			model.addAttribute("listCheck", "empty");
			return "search";
		}
		
		model.addAttribute("pageMaker", new PageDTO(cri, bookService.goodsGetTotal(cri)));
		model.addAttribute("filterCheck", cri.getType());
		
		String[] typeArr = cri.getType().split("");
		for (String s : typeArr) {
			if (s.equals("T") || s.equals("A")) {
				model.addAttribute("filter_info", bookService.getCateInfoList(cri));
			}
		}
		return "search";
	}
	
	// 상품 상세
	@GetMapping("/goodsDetail/{bookId}")
	public String goodsDetailGET(@PathVariable("bookId") int bookId, Model model) {
		model.addAttribute("goodsInfo", bookService.getGoodsInfo(bookId));
		
		return "/goodsDetail";
	}
	
	// 리뷰 쓰기
	@GetMapping("/reviewEnroll/{memberId}")
	public String reviewEnrollWindowGET(@PathVariable("memberId") String memberId, int bookId, Model model) {
		BookVO book = bookService.getBookName(bookId);
		model.addAttribute("bookInfo", book);
		model.addAttribute("memberId", memberId);
		
		return "/reviewEnroll";
	}
}
