package tech.murilo.javacrypto;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class RSA {
	
	public static void runSample() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, 
		IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance("RSA");
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(512);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		
		String message = "Olá mundo!";
		
		System.out.println("Mensagem: " + message);
		
		byte[] data = message.getBytes(StandardCharsets.UTF_8);
		
		cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPrivate());
		byte[] encrypted = cipher.doFinal(data);
		System.out.println("Mensagem encriptada: " + new String(encrypted, StandardCharsets.UTF_8));
		
		cipher.init(Cipher.DECRYPT_MODE, keyPair.getPublic());
		byte[] decrypted = cipher.doFinal(encrypted);
		System.out.println("Mensagem decriptada: " + new String(decrypted, StandardCharsets.UTF_8));
	
	}
	
	public static void runDigitalSignatureSample() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, 
		IllegalBlockSizeException, BadPaddingException {
		String message = "Olá mundo!";
		
		System.out.println("Mensagem: " + message);
		
		byte[] data = message.getBytes(StandardCharsets.UTF_8);
		
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(data);
	    byte[] md5 = messageDigest.digest();
	    System.out.println("MD5 da mensagem: " + Hex.encodeHexString(md5));
		
	    Cipher cipher = Cipher.getInstance("RSA");
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(512);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		
		cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
		byte[] encrypted = cipher.doFinal(md5);
		System.out.println("Assinatura digital da mensagem: " + Hex.encodeHexString(encrypted));
		
		cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
		byte[] decrypted = cipher.doFinal(encrypted);
		System.out.println("MD5 da mensagem para verificação: " + Hex.encodeHexString(decrypted));
	}
	
	public static void runSampleWithBigData() throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, 
		IllegalBlockSizeException, BadPaddingException {
		String message = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
		
		System.out.println("Mensagem: " + message);
		
		byte[] data = message.getBytes(StandardCharsets.UTF_8);
		
		Cipher cipher3DES = Cipher.getInstance("TripleDES/ECB/PKCS5Padding");
		KeyGenerator keyGenerator  = KeyGenerator.getInstance("TripleDES");
		keyGenerator.init(112);
		SecretKey key3DES = keyGenerator.generateKey();
		byte[] key3DESData = key3DES.getEncoded();
		
		System.out.println("Chave 3DES intermediária: " + new String(key3DESData));
		
		cipher3DES.init(Cipher.ENCRYPT_MODE, key3DES);
		byte[] encryptedMessage = cipher3DES.doFinal(data);
		
		System.out.println("Mensagem encriptada com chave intermediária: " + new String(encryptedMessage, StandardCharsets.UTF_8));
		
		Cipher cipherRSA = Cipher.getInstance("RSA");
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(512);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		
		cipherRSA.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
		byte[] encrypted = cipherRSA.doFinal(key3DESData);
		System.out.println("Chave 3DES encriptada: " + new String(encrypted));
		
		cipherRSA.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
		byte[] decrypted = cipherRSA.doFinal(encrypted);
		System.out.println("Chave 3DES decriptada: " + new String(decrypted));
		
		SecretKey decryptedKey = new SecretKeySpec(decrypted, "TripleDES");
		
		cipher3DES.init(Cipher.DECRYPT_MODE, decryptedKey);
		byte[] decryptedMessage = cipher3DES.doFinal(encryptedMessage);
		System.out.println("Mensagem decriptada: " + new String(decryptedMessage, StandardCharsets.UTF_8));
	}

}
