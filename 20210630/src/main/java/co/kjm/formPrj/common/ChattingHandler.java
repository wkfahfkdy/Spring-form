package co.kjm.formPrj.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.log4j.Log4j;

@Log4j
public class ChattingHandler extends TextWebSocketHandler {

	private List<WebSocketSession> sessionList = new ArrayList<>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
		log.info("#ChattingHandler, afterConnectionEstablished");
		sessionList.add(session);
		//log.info(session.getPrincipal().getName() + "님이 입장");
	}
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		Map<String, Object> map = session.getAttributes();
		String name = (String) map.get("name");
		
		log.info("#ChattingHandler, handleMessage");
		log.info(session.getId() + ": " + message);
		System.out.println(message);
		for(WebSocketSession s : sessionList) {
			s.sendMessage(new TextMessage(name + ":" + message.getPayload()));
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		
		log.info("#chattingHandler, afterConnectionClosed");
		
		sessionList.remove(session);
		
		//log.info(session.getPrincipal().getName() + "님이 퇴장");
	}
}
