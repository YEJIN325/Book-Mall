package com.javalec.controller;

import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javalec.model.MemberVO;
import com.javalec.service.MemberService;

@Controller
@RequestMapping(value="/member")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberservice;
	
	@Autowired
	private JavaMailSenderImpl mailSender;
	
	@Autowired
	private BCryptPasswordEncoder pwEncoder;
	
	// 로그인 페이지 이동
	@RequestMapping(value="login", method=RequestMethod.GET)
	public void loginGET() {
		logger.info("로그인 페이지 진입");
	}
	
	// 로그인
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String loginPOST(HttpServletRequest request, MemberVO member, RedirectAttributes rttr) throws Exception {
		HttpSession session = request.getSession();
		String rawPw = "";
		String encodePw = "";
		MemberVO lvo = memberservice.memberLogin(member);
		
		if (lvo != null) {
			rawPw = member.getMemberPw();
			encodePw = lvo.getMemberPw();
			
			if (pwEncoder.matches(rawPw, encodePw) == true) {
				lvo.setMemberPw("");
				session.setAttribute("member", lvo);
				return "redirect:/main";
				
			} else {
				rttr.addFlashAttribute("result", 0);
				return "redirect:/member/login";
			}
		} else {
			rttr.addFlashAttribute("result", 0);
			return "redirect:/member/login";
		}
			
		
	}
	
	// 회원가입 페이지 이동
	@RequestMapping(value="join", method=RequestMethod.GET)
	public void joinGET() {
		logger.info("회원가입 페이지 진입");
	}
	
	// 회원가입
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String joinPOST(MemberVO member) throws Exception {
		
		String rawPw = "";
		String encodePw = "";
		
		rawPw = member.getMemberPw();
		encodePw = pwEncoder.encode(rawPw);
		member.setMemberPw(encodePw);
		
		memberservice.memberJoin(member);
		
		return "redirect:/main";
	}
	
	// 아이디 중복 검사
	@RequestMapping(value="/memberIdChk", method = RequestMethod.POST)
	@ResponseBody  // 이거 뭔지 ?
	public String memberIdChkPOST(String memberId) throws Exception {
		// logger.info("memberIdChk() 진입");
		int result = memberservice.idCheck(memberId);
		logger.info("결과값 = " + result);
		if (result != 0) {
			return "fail";
		} else {
			return "success";
		}
	}
	
	// 이메일 인증
	@RequestMapping(value="/mailCheck", method=RequestMethod.GET)
	@ResponseBody
	public String mailCheckGET(String email) throws Exception {
		logger.info("이메일 데이터 전송 확인");
		logger.info("인증번호 : " + email);
		
		// 인증번호(난수) 생성
		Random random = new Random();
		int checkNum = random.nextInt(999999);
		logger.info("인증번호" + checkNum);
		
		String title = "회원가입 인증 이메일 입니다.";
		String content = 
				"홈페이지를 방문해주셔서 감사합니다." +
				"<br><br>" +
				"인증 번호는 " + checkNum + " 입니다." +
				"<br>" +
				"해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
		String from = "support@bookmall.com";
		String to = email;
		
		try {
			MimeMessage mail = mailSender.createMimeMessage();
			MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true, "UTF-8");
			
			mailHelper.setFrom(from);
			mailHelper.setTo(to);
			mailHelper.setSubject(title);
			mailHelper.setText(content, true);
			
			mailSender.send(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String num = Integer.toString(checkNum);
		
		return num;
	}
	
	// 로그아웃
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logoutMainGET(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "redirect:/main";
	}
	
	// 비동기방식 로그아웃
	@RequestMapping(value="logout", method=RequestMethod.POST)
	@ResponseBody  // ajax를 통해 서버에 요청을 하는 방식이기 때문
	public void logoutPOST(HttpServletRequest request) throws Exception {
		logger.info("비동기 로그아웃 메서드 진입");
		HttpSession session = request.getSession();
		session.invalidate();
	}
}
