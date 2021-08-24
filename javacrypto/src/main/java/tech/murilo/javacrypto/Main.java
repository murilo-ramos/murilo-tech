package tech.murilo.javacrypto;

public class Main {
	
	public static void main(String[] args) {
		try {
			System.out.println("DES");
			DES.runSample();
			System.out.println("\n");
			
			System.out.println("TripleDES");
			TripleDES.runSample();
			System.out.println("\n");
			
			System.out.println("AES");
			AES.runSample();
			System.out.println("\n");
			
			System.out.println("RSA");
			RSA.runSample();
			System.out.println("\n");
			
			System.out.println("MD5");
			MD5.runSample();
			System.out.println("\n");
			
			System.out.println("Assinatura digital");
			RSA.runDigitalSignatureSample();
			System.out.println("\n");
			
			System.out.println("RSA com dados grandes");
			RSA.runSampleWithBigData();
			System.out.println("\n");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
}
