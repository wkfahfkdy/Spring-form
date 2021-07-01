package co.kjm.formPrj;

import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.kjm.formPrj.common.BouncyEncryption;
import co.kjm.formPrj.common.Encryption;

@Controller
public class HomeController {
	
	@RequestMapping("home")
	public String home(Model model) throws NoSuchAlgorithmException {
		
		Encryption enc = new Encryption();	// SHA3 암호화 메소드 생성
		model.addAttribute("encData", enc.typeOne("go?tohome"));
		model.addAttribute("encStr", enc.typeTwo("go?tohome"));
		
		BouncyEncryption bn = new BouncyEncryption(); // Bouncy Castle ~~ 암호화 메소드 생성
		String plainText = "3468324-03242";	// 평문
		String cyperText = bn.encrypt(plainText).toString();	// 암호문
		
		String decryptionText = bn.decrypt(cyperText).toString(); // 복호문 << 이라고 적어주셨는데 내 생각에는 Bouncy Castle 암/복호문은 어떤 file에 대한 잠금 / 잠금해제 상태변경 느낌인듯?
																	// 암호화할때는 true 상태로, 복호화할때는 false 상태로하는 그런 것?
																	// 이렇게 생각한 이유가 예시를 봤을때 key, path 값이 암호문일 때랑 복호문일 때가 동일했기 때문임.
																	// 만약에 교수님이 원하는 방향이었다면 key, path 값이 동일하지 않고
																	// 복호문의 path 값에는 암호문 결과문에 대한 값이 들어갔어야 했을 것이라고 추측했기 때문
		
		model.addAttribute("plainText", plainText);
		model.addAttribute("cyperText", cyperText);
		model.addAttribute("decryptionText", decryptionText);
		
		return "home";
	}
	
}
