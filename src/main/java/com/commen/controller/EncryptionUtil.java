package com.commen.controller;

import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.tomcat.util.codec.binary.Base64;

public class EncryptionUtil {
	public static String decryptPassJS(final String t) {
		String t1 = t;
		t1 = t1.replace("\\\\", "\\");
		String res = ""; 
		String key = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789/";
		//key=key*7;		
		int len=key.length();
		for(char a : t1.toCharArray()) {
			if(a == 125) {
				a = 37;
			}
			for(int i = 0; i < len; i++) {
				if(!(a >= 123 || a < 31)) {
					if(a-1 != 31)
						a -= 1;
					else
						a = 122;
				} else {
					break;
				}
			}
			res += a;
		}
		return res;
	}
	
  // some random salt
  private static final byte[]	SALT= { (byte) 0x21, (byte) 0x21, (byte) 0xF0, (byte) 0x55, (byte) 0xC3, (byte) 0x9F, (byte) 0x5A, (byte) 0x75};
	private final static int	ITERATION_COUNT	= 31;
	public static String encodeorgn(final String input) {
		if (input == null) {
			throw new IllegalArgumentException();
		}
		try {
			KeySpec keySpec = new PBEKeySpec(null, SALT, ITERATION_COUNT);
			AlgorithmParameterSpec paramSpec = new PBEParameterSpec(SALT, ITERATION_COUNT);
			SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
			Cipher ecipher = Cipher.getInstance(key.getAlgorithm());
			ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
			byte[] enc = ecipher.doFinal(input.getBytes());
			String res = new String(Base64.encodeBase64(enc));
			// escapes for url
			res = res.replace('+', '-').replace('/', '_').replace("%", "%25").replace("\n", "%0A");
			return res;
		}
		catch (Exception e) {
		}
		return "";
	}
	
	public static String encode(final String input) {
		if (input == null) {
			throw new IllegalArgumentException();
		}
		try {
			KeySpec keySpec = new PBEKeySpec(null, SALT, ITERATION_COUNT);
			AlgorithmParameterSpec paramSpec = new PBEParameterSpec(SALT, ITERATION_COUNT);
			SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
			Cipher ecipher = Cipher.getInstance(key.getAlgorithm());
			ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
			byte[] enc = ecipher.doFinal(input.getBytes());
			String res = new String(Base64.encodeBase64(enc));
			// escapes for url
			res = res.replace('+', '-').replace('/', '_').replace("%", "%25").replace("\n", "%0A").replace('=', ' ').trim();
			return res;
		}
		catch (Exception e) {
		}

		return "";
	}

	public static String decode(final String token) {
		if (token == null) {
			return null;
		}
		try {
			String input = token.replace("%0A", "\n").replace("%25", "%").replace('_', '/').replace('-', '+').replace('=', ' ').trim();
			//String input = token.replace("%0A", "\n").replace("%25", "%").replace('_', '/').replace('-', '+').replace('=', ' ');
			
			byte[] dec = Base64.decodeBase64(input.getBytes());
			KeySpec keySpec = new PBEKeySpec(null, SALT, ITERATION_COUNT);
			AlgorithmParameterSpec paramSpec = new PBEParameterSpec(SALT, ITERATION_COUNT);

			SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
			Cipher dcipher = Cipher.getInstance(key.getAlgorithm());
			dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
			byte[] decoded = dcipher.doFinal(dec);
			String result = new String(decoded);
			return result;
		}
		catch (Exception e) {
      // use logger in production code
			e.printStackTrace();
		}
		return null;
	}
	public static void main(final String args[]) {
		String a=EncryptionUtil.encode("dhankharss476@gmail.com");
		System.out.println(a);
		System.out.println(EncryptionUtil.decode("4bWmZ6vVSQfhNpRtK2R8vg"));
	}
}