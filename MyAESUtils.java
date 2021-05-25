package security;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class MyAESUtils {
	private static final String ENCODING = "UTF-8";
	static String ivstr="abcdefghijklmnop";
	static byte[] iv=ivstr.getBytes();
	public static byte[] encrypt(String content) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");  
			kgen.init(128,new SecureRandom("1234567890123456".getBytes()));
			SecretKey secretKey = kgen.generateKey();
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey,new IvParameterSpec(iv));
			byte[] result = cipher.doFinal(byteContent);
			return result;
		} catch (Exception e) {
		e.printStackTrace();
		}
		return null;
	}
	
	public static byte[] decrypt(byte[] content) {
		try {
			KeyGenerator kgen=KeyGenerator.getInstance("AES");  
			kgen.init(128, new SecureRandom("1234567890123456".getBytes())); 
			SecretKey secretKey = kgen.generateKey();
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKey,new IvParameterSpec(iv));
			byte[] result = cipher.doFinal(content);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
			return null;
	}
	public static byte[] toByteArray(InputStream in) throws IOException {
		 
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    byte[] buffer = new byte[1024 * 4];
	    int n = 0;
	    while ((n = in.read(buffer)) != -1) {
	        out.write(buffer, 0, n);
	    }
	    return out.toByteArray();
	}
}


