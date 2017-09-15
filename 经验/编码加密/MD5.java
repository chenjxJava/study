package com.shubo.utils.password;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	public static String md5(String str)
	{
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
			return null;
		}
		byte[] resultByte = messageDigest.digest(str.getBytes());
		StringBuffer result = new StringBuffer();

		for (int offset = 0; offset < resultByte.length; offset++) {
			int i = resultByte[offset];
			if (i < 0)
				i += 256;
			if (i < 16)
				result.append("0");
			result.append(Integer.toHexString(i));
		}
		return result.toString().toUpperCase();
	}

	public static void main(String[] args) {
		System.out.println(md5("admin_shb"));
	}
}
