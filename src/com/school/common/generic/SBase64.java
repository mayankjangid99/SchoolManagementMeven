package com.school.common.generic;

import java.security.SecureRandom;

import org.apache.commons.codec.binary.Base64;

public class SBase64
{

	public static String encodeBase64String(String str) {
		return Base64.encodeBase64String(str.getBytes()).toString() + "$".concat(getRandomCode());

	}

	
	public static String decodeBase64(String str) {
		if(str != null && !"".equals(str))
		{
			String string = str.toString();
			int index = string.lastIndexOf("$");
			String subStr = string.substring(0, index);
			return new String( Base64.decodeBase64(subStr));
		}
		return null;
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
