package co.kjm.formPrj.member.web;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import co.kjm.formPrj.common.Encryption;
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
	public String memberRegister(MemberVO vo, Model model) throws IOException, NoSuchAlgorithmException  {
		
		System.out.println("Register : " + vo);
		
		// password 암호화
		Encryption enc = new Encryption(); // 암호화 모듈 호출
		vo.setPassword(enc.typeTwo(vo.getPassword()));	// 암호화
		
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
		
		// memberDao.memberInsert(vo); // 2번 insert 적어둔 이유 : transaction 보기 위해서. 여기서는 insert에 성공했지만
		int n = memberDao.memberInsert(vo);	// 여기에서 실패했기 때문에 transaction이 일어나서 commit이 된게 아니라 rollback이 일어난다. 그래서 DB에 입력되지 않는다.
											// 그런데 그래도 두번째에서 실패하더라도 insert를 하고싶다면 dataSource-context에서 설정한 transaction 부분을 빼면 된다.
											// 아무튼 transaction 기능이 이런 것
		
		if(n != 0) {
			model.addAttribute("message", "회원가입ㅇ");
		} else {
			model.addAttribute("message", "회원가입x");
		}
		
		return "member/memberRegister";
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
	public String memberLogin(MemberVO vo ,Model model, HttpSession session) throws NoSuchAlgorithmException {
		
		// password 암호화
		Encryption enc = new Encryption(); // 암호화 모듈 호출
		vo.setPassword(enc.typeTwo(vo.getPassword()));
		
		vo = memberDao.memberLogin(vo);
		if(vo != null) {
			session.setAttribute("id", vo.getEmail());
			session.setAttribute("name", vo.getName());
		} else {
			session.invalidate();
			vo = new MemberVO();
			vo.setEmail("Guest");
		}
		
		model.addAttribute("member", vo);
		
		return "member/memberLogin";
	}
	
	@RequestMapping("ajax/intron")
	@ResponseBody
	public String intron() {
		
		
		
		return null;
	}
	
}
