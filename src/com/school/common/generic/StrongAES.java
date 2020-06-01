package com.school.common.generic;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class StrongAES 
{
	private static String key="Mayank12Mayank12";
	private static Key aesKey=new SecretKeySpec(key.getBytes(), "AES");
	private static Cipher cipher=null;
	
	private static void setConfig()
	{
		try {
			/*key="Mayank12Mayank12";
			aesKey=new SecretKeySpec(key.getBytes(), "AES");*/
			cipher=Cipher.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}
	
	public static String encrypt(String str)
	{
		setConfig();
		try 
		{
			/*String key="Mayank12Mayank12";
			Key aesKey=new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher=Cipher.getInstance("AES");*/
			
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			byte[] encrypted=cipher.doFinal(str.getBytes());
			System.out.println(encrypted.toString());
			String encryptedText=new String(Base64.encodeBase64String(encrypted));
			System.out.println("====="+encryptedText);
			
			/*cipher.init(Cipher.DECRYPT_MODE, aesKey);
			byte[] encrypted2=Base64.decodeBase64(encryptedText.getBytes());
			String decrypted=new String(cipher.doFinal(encrypted2));
			System.out.println("===="+decrypted);*/
			return (encryptedText+"$").concat(getRandomCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	
	public static String encryptAes(String str)
	{
		setConfig();
		try 
		{
			/*String key="Mayank12Mayank12";
			Key aesKey=new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher=Cipher.getInstance("AES");*/
			
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			byte[] encrypted=cipher.doFinal(str.getBytes());
			System.out.println(encrypted.toString());
			
			/*cipher.init(Cipher.DECRYPT_MODE, aesKey);
			byte[] encrypted2=Base64.decodeBase64(encryptedText.getBytes());
			String decrypted=new String(cipher.doFinal(encrypted2));
			System.out.println("===="+decrypted);*/
			return (encrypted.toString()+"$").concat(getRandomCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	
	
	public static String decrypt(String str)
	{
		setConfig();
		try 
		{
			/*String key="Mayank12Mayank12";
			Key aesKey=new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher=Cipher.getInstance("AES");*/

			int index=str.lastIndexOf("$");
			String subStr=str.substring(0, index);
			
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			byte[] decrypted=Base64.decodeBase64(subStr.getBytes());
			String decryptedText=new String(cipher.doFinal(decrypted));
			//System.out.println("decryptedText===="+decryptedText);
			return decryptedText;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	
	public static String decryptAes(String str)
	{
		setConfig();
		try 
		{
			/*String key="Mayank12Mayank12";
			Key aesKey=new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher=Cipher.getInstance("AES");*/

			int index=str.lastIndexOf("$");
			String subStr=str.substring(0, index);
			
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			String decrypted=new String(cipher.doFinal(subStr.getBytes()));
			//System.out.println("decryptedText===="+decryptedText);
			return decrypted;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	private static String getRandomCode(){
		int length=10;
		char[] pass = new char[length];
		StringBuffer accessCode = new StringBuffer(12);
		/*
		 * To  fix Cryptographic Issues ,  SecureRandom used instead Random class
		 */
		SecureRandom rand =  new SecureRandom();
		{
			for (int x = 0; x < length; x++) {
				int randDecimalAsciiVal = rand.nextInt(93) + 33;
				if((randDecimalAsciiVal<48 || randDecimalAsciiVal>57) && (randDecimalAsciiVal<65 || randDecimalAsciiVal>90)
						&& (randDecimalAsciiVal<97 || randDecimalAsciiVal>122)){
					--x;
					continue;
				}
				pass[x] = (char) randDecimalAsciiVal;
				accessCode.append(pass[x]);

			}
			accessCode.append("M_K_");

		}
		return accessCode.toString();
	}
	
	
}
