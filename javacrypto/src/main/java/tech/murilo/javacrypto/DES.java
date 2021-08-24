package tech.murilo.javacrypto;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DES {
	
	public static void runSample() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, 
		IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance("DES");
		SecretKey key = new SecretKeySpec("12345678".getBytes(StandardCharsets.US_ASCII), "DES");
		
		String message = "Olá mundo!";
		
		System.out.println("Mensagem: " + message);
		
		byte[] data = message.getBytes(StandardCharsets.UTF_8);
		
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encrypted = cipher.doFinal(data);
		System.out.println("Mensagem encriptada : " + new String(encrypted, StandardCharsets.UTF_8));
		
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decrypted = cipher.doFinal(encrypted);
		System.out.println("Mensagem decriptada: " + new String(decrypted, StandardCharsets.UTF_8));
	}

}
