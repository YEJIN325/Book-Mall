package com.javalec.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javalec.model.AttachImageVO;
import com.javalec.model.AuthorVO;
import com.javalec.model.BookVO;
import com.javalec.model.Criteria;
import com.javalec.model.PageDTO;
import com.javalec.service.AdminService;
import com.javalec.service.AuthorService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private AdminService adminService;
	
	
	@RequestMapping(value="main", method = RequestMethod.GET)
	public void adminMainGET() throws Exception{
		logger.info("관리자 페이지 이동");
	}
	
	// 상품 목록
	@RequestMapping(value="goodsManage", method=RequestMethod.GET)
	public void goodsManageGET(Criteria cri, Model model) throws Exception {
		logger.info("상품 목록 페이지 접속");
		
		// 상품 리스트 데이터
		List list = adminService.goodsGetList(cri);
		if (!list.isEmpty()) {
			model.addAttribute("list", list);
		} else {
			model.addAttribute("listCheck", "empty");
			return;
		}
		
		// 페이지 인터페이스 데이터
		model.addAttribute("pageMaker", new PageDTO(cri, adminService.goodsGetTotal(cri)));
		
	}
	
	// 상품 등록
	@RequestMapping(value="goodsEnroll", method=RequestMethod.GET)
	public void goodsEnrollGET(Model model) throws Exception {
		logger.info("상품 등록 페이지 접속");
		
		ObjectMapper objm = new ObjectMapper();
		List list = adminService.cateList();
		String cateList = objm.writeValueAsString(list);
		model.addAttribute("cateList", cateList);
	}
	
	@PostMapping("/goodsEnroll")
	public String goodsEnrollPOST(BookVO book, RedirectAttributes rttr) {
		logger.info("goodsEnrollPOST......" + book);
		adminService.bookEnroll(book);
		rttr.addFlashAttribute("enroll_result", book.getBookName());
		return "redirect:/admin/goodsManage";
	}
	
	// 상품 조회, 수정
	@GetMapping({"/goodsDetail", "/goodsModify"})
	public void goodsGetInfoGET(int bookId, Criteria cri, Model model) throws JsonProcessingException {
		logger.info("goodsGetInfo()......" + bookId);
		
		ObjectMapper mapper = new ObjectMapper();
		List list = adminService.cateList();
		String cateList = mapper.writeValueAsString(list);
		model.addAttribute("cateList", cateList);
		
		model.addAttribute("cri", cri);
		model.addAttribute("goodsInfo", adminService.goodsGetDetail(bookId));
	}
	
	// 상품 정보 수정
	@PostMapping("/goodsModify")
	public String goodsModifyPOST(BookVO book, RedirectAttributes rttr) {
		int result = adminService.goodsModify(book);
		rttr.addFlashAttribute("modify_result", result);
		return "redirect:/admin/goodsManage";
	}
	
	// 상품 정보 삭제
	@PostMapping("/goodsDelete")
	public String goodsDeletePOST(int bookId, RedirectAttributes rttr) {
		int result = adminService.goodsDelete(bookId);
		rttr.addFlashAttribute("delete_result", result);
		return "redirect:/admin/goodsManage";
	}
	
	// 작가 등록
	@RequestMapping(value="authorEnroll", method=RequestMethod.GET)
	public void authorEnrollGET() throws Exception {
		logger.info("작가 등록 페이지 접속");
	}
	
	// 작가 관리 페이지 접속
	@RequestMapping(value="authorManage", method=RequestMethod.GET)
	public void authorManageGET(Criteria cri, Model model) throws Exception {
		logger.info("작가 관리 페이지 접속......" + cri);
		
		List list = authorService.authorGetList(cri);
		
		if (!list.isEmpty()) {
			model.addAttribute("list", list);
		} else {
			model.addAttribute("listCheck", "empty");
		}
		
		// 페이지 이동 인터페이스 데이터
		int total = authorService.authorGetTotal(cri);
		PageDTO pageMaker = new PageDTO(cri, total);
		model.addAttribute("pageMaker", pageMaker);
		
	}
	
	//작가 등록
	@RequestMapping(value="authorEnroll.do", method=RequestMethod.POST)
	public String authorEnrollPOST(AuthorVO author, RedirectAttributes rttr) throws Exception {
		
		logger.info("authorEnroll : " + author);
		
		authorService.authorEnroll(author);
		rttr.addFlashAttribute("enroll_result", author.getAuthorName());
		
		return "redirect:/admin/authorManage";
	}
	
	// 작가 상세 페이지
	@GetMapping({"/authorDetail", "/authorModify"})
	public void authorGetInfoGET(int authorId, Criteria cri, Model model) throws Exception {
		logger.info("authorDetail......" + authorId);
		// 작가 관리 페이지 정보
		model.addAttribute("cri", cri);
		
		// 선택 작가 정보
		model.addAttribute("authorInfo", authorService.authorGetDetail(authorId));
	}
	
	// 작가 정보 수정
	@PostMapping("/authorModify")
	public String authorModifyPOST(AuthorVO author, RedirectAttributes rttr) throws Exception {
		int result = authorService.authorModify(author);
		rttr.addFlashAttribute("modify_result", result);
		
		return "redirect:/admin/authorManage";
	}
	
	/* 작가 정보 삭제 */
	@PostMapping("/authorDelete")
	public String authorDeletePOST(int authorId, RedirectAttributes rttr) {
		int result = 0;
		try {
			result = authorService.authorDelete(authorId);
		} catch (Exception e) {
			e.printStackTrace();
			result = 2;
			rttr.addFlashAttribute("delete_result", result);
			
			return "redirect:/admin/authorManage";
		}
		
		rttr.addFlashAttribute("delete_result", result);
		return "redirect:/admin/authorManage";
	}
	
	// 작가 검색 팝업창
	@GetMapping("/authorPop")
	public void authorPopGET(Criteria cri, Model model) throws Exception {
		logger.info("authorPopGET......");
		
		cri.setAmount(5);
		List list = authorService.authorGetList(cri);
		
		if (!list.isEmpty()) {
			model.addAttribute("list", list);
		} else {
			model.addAttribute("listCheck", "empty");
		}
		
		// 페이지 이동 인터페이스 데이터
		int total = authorService.authorGetTotal(cri);
		PageDTO pageMaker = new PageDTO(cri, total);
		model.addAttribute("pageMaker", pageMaker);
	}
	
	// 첨부 파일 업로드
	@PostMapping(value="/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<AttachImageVO>> uploadAjaxActionPOST(MultipartFile[] uploadFile) {
		
		// 이미지 파일 체크
		for (MultipartFile multipartFile: uploadFile) {
			File checkfile = new File(multipartFile.getOriginalFilename());
			String type = null;
			
			try {
				type = Files.probeContentType(checkfile.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if (!type.startsWith("image")) {
				List<AttachImageVO> list = null;
				return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
			}
		}
		
		String uploadFolder = "C:\\upload";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		String datePath = str.replace("-", File.separator);
		
		File uploadPath = new File(uploadFolder, datePath);
		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		}
		
		// 이미지 정보 담는 객체
		List<AttachImageVO> list = new ArrayList();
		
		for (MultipartFile multipartFile : uploadFile) {
			
			AttachImageVO ivo = new AttachImageVO();
			
			// 파일 이름
			String uploadFileName = multipartFile.getOriginalFilename();
			ivo.setFileName(uploadFileName);
			ivo.setUploadPath(datePath);
			
			// uuid 적용 파일 이름
			String uuid = UUID.randomUUID().toString();
			ivo.setUuid(uuid);
			
			uploadFileName = uuid + "_" + uploadFileName;
			
			File saveFile = new File(uploadPath, uploadFileName);
			
			// 파일 저장
			try {
				multipartFile.transferTo(saveFile);
				
				// 썸네일 생성
				File thumbnailFile = new File(uploadPath, "s_" + uploadFileName);
				
				BufferedImage b_image = ImageIO.read(saveFile);
				
				// 비율
				double ratio = 3;
				int width = (int) (b_image.getWidth() / ratio);
				int height = (int) (b_image.getHeight() / ratio);
				
				BufferedImage bt_image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);   // 넓이, 높이, 이미지 타입
				
				Graphics2D graphic = bt_image.createGraphics();
				graphic.drawImage(b_image, 0, 0, width, height, null);
				
				ImageIO.write(bt_image, "jpg", thumbnailFile);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			list.add(ivo);
			
		}
		
		ResponseEntity<List<AttachImageVO>> result = new ResponseEntity<List<AttachImageVO>>(list, HttpStatus.OK);
		
		return result;
	}
	
	// 이미지 파일 삭제
	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(String fileName){
		
		File file = null;
		try {
			// 썸네일 파일 삭제
			file = new File("c:\\upload\\" + URLDecoder.decode(fileName, "UTF-8"));
			file.delete();
			
			// 원본 파일 삭제
			String originFileName = file.getAbsolutePath().replace("s_", "");
			file = new File(originFileName);
			file.delete();
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("fail", HttpStatus.NOT_IMPLEMENTED);
		}
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
}
