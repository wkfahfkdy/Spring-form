package co.kjm.formPrj.common;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class ChatContorller {
	
	@GetMapping("/chat")
	public void chat(Model model, HttpSession session) {

		String id = (String) session.getAttribute("id");
		
		log.info("======================");
		log.info("@ChatController, GET Chat / ID : " + id);
		
		
	}
}
