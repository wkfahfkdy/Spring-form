package co.kjm.formPrj.member.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import co.kjm.formPrj.member.service.MemberService;
import co.kjm.formPrj.member.vo.MemberVO;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberDao;
	
	@RequestMapping("memberInputForm")
	public String memberInputForm(@ModelAttribute("member") MemberVO vo ,Model model) {
		
		List<String> loginType = new ArrayList<String>();
		loginType.add("일반 회원");
		loginType.add("기업 회원");
		loginType.add("기타 회원");
		
		model.addAttribute("loginType" , loginType);
		
		return "member/memberInputForm";
	}
	
	@RequestMapping("memberRegister")
	public String memberRegister(MemberVO vo, Model model) throws IOException  {
		
		System.out.println(vo);
		
		// Maven - commons-fileupload 잊지말기
		MultipartFile file = vo.getFile(); // 파일 객체 받기
		String fileName = file.getOriginalFilename();
		
		System.out.println(fileName);
		
		UUID fileUuid = UUID.randomUUID();
		String aliasFileName = fileUuid.toString();
		vo.setFileUuid(aliasFileName);
		
		// UUID 이름으로 파일 받을 경로
		File target = new File("C:\\temp", aliasFileName);
		file.transferTo(target);
		
		// DB 입력
		vo.setFileName(fileName);
		System.out.println(vo);
		int n = memberDao.memberInsert(vo);
		
		String message = "";
		
		if(n != 0) {
			message = "입력ㅇ";
		} else {
			message = "입력x";
		}
		
		model.addAttribute("message", message);
		
		return "home";
	}
	
	@RequestMapping("step1")
	public String step1() {
		
		return "step1";
	}
	
	@RequestMapping("memberLoginForm")
	public String memberLoginForm(@ModelAttribute("member") MemberVO vo, HttpSession session, Model model) {
		String id = (String) session.getAttribute("id");
		model.addAttribute("id",id);
		return "member/memberLoginForm";
	}
	
	@PostMapping("memberLogin")
	public String memberLogin(MemberVO vo ,Model model, HttpSession session) {
		
		vo = memberDao.memberLogin(vo);
		if(vo != null) {
			session.setAttribute("id", vo.getEmail());
			session.setAttribute("name", vo.getName());
		} else {
			vo = new MemberVO();
			vo.setEmail("Guest");
		}
		
		model.addAttribute("member", vo);
		
		return "member/memberLogin";
	}
	
}
