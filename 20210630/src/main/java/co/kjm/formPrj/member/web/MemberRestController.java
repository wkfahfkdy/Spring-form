package co.kjm.formPrj.member.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.kjm.formPrj.member.service.MemberService;
import co.kjm.formPrj.member.vo.MemberVO;

// @RestController = @Controller + @ResponseBody
@RestController	// 비동기 데이터 통신을 위한 컨트롤러
//@Controller
//@ResponseBody	
public class MemberRestController {
	// 비동기 통신을 위한 메소드를 사용 가능
	
	@Autowired
	private MemberService memberDao;
	
	// 그냥 Controller만 써도 된다. 그 경우에는
	//@RequestMapping("ajax/intron")
	//@ResponseBody
	//public String ~~~~
	
	@GetMapping("api/members")
	public List<MemberVO> members() {

		return memberDao.memberSelectList();
	}
	
	
}
