package tech.murilo.javacrypto;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class MD5 {
	
	public static void runSample() throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		
		String message = "Olá mundo!";
		
		System.out.println("Mensagem: " + message);
		
		byte[] data = message.getBytes(StandardCharsets.UTF_8);	        
	    
	    messageDigest.update(data);
	    byte[] md5 = messageDigest.digest();
	    
	    System.out.println("MD5 da mensagem: " + Hex.encodeHexString(md5));
	}

}
