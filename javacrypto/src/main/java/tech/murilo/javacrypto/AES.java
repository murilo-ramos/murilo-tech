package tech.murilo.javacrypto;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
	
	public static void runSample() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, 
		InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		SecretKey key = new SecretKeySpec("abcdefghijklmnopqrstuvwx".getBytes(StandardCharsets.US_ASCII), "AES");
		IvParameterSpec iv = new IvParameterSpec(new SecureRandom().generateSeed(16));
		
		String message = "Olá mundo!";
		
		System.out.println("Mensagem: " + message);
		
		byte[] data = message.getBytes(StandardCharsets.UTF_8);
		
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		byte[] encrypted = cipher.doFinal(data);
		System.out.println("Mensagem encriptada: " + new String(encrypted, StandardCharsets.UTF_8));
		
		cipher.init(Cipher.DECRYPT_MODE, key, iv);
		byte[] decrypted = cipher.doFinal(encrypted);
		System.out.println("Mensagem decriptada: " + new String(decrypted, StandardCharsets.UTF_8));
	}
}
