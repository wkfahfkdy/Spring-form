package co.kjm.formPrj.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption { // 단방향 암호화 알고리즘(SHA3 사용)
	
	final char[] hexArray = "v$v17B8%3N1$z@#v".toCharArray(); // 암호화 키. 무조건 16자 해야하는듯??? 아닌가? 처음에 막 치니까 작동되지않았다.
	
	/**
	 * SHA-256으로 해싱하는 메소드
	 * 
	 * @param bytes
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	private byte[] sha256(String msg) throws NoSuchAlgorithmException {
	    MessageDigest md = MessageDigest.getInstance("SHA-256");
	    md.update(msg.getBytes());
	    
	    return md.digest();

	}
	
	/**
	 * 바이트를 헥사값으로 변환한다, type 1
	 * 
	 * @param bytes
	 * @return
	 */
	private String bytesToHex1(byte[] bytes) {
	    StringBuilder builder = new StringBuilder();
	    for (byte b: bytes) {
	      builder.append(String.format("%02x", b));
	    }
	    return builder.toString();
	}

	/**
	 * 바이트를 헥사값으로 변환한다, type 2
	 * 
	 * @param bytes
	 * @return
	 */
	private String bytesToHex2(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}

	// 실행 test. type1
	public String typeOne(String str) throws NoSuchAlgorithmException {
		
		return bytesToHex1(sha256(str));
	}
	
	// 실행 test. type2
	public String typeTwo(String str) throws NoSuchAlgorithmException {
		
		return bytesToHex2(sha256(str));
	}
	
}
