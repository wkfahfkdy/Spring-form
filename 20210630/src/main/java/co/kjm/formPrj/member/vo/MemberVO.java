package co.kjm.formPrj.member.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MemberVO {
	
	private String email;
	private String name;
	private String password;
	private String state;
	private String fileName;
	private String directory;
	private String fileUuid;
	private String gubun;
	private MultipartFile file;
	
}
