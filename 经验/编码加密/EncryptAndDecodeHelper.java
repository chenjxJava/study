package com.shubo.utils.password;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncryptAndDecodeHelper {
	public static String Base64Decode(String base64, String code)
			throws Exception
	{
		byte[] bytes = (byte[])null;
		String param = "";
		try {
			bytes = new BASE64Decoder().decodeBuffer(base64);
			param = new String(bytes, code);
		} catch (Exception e) {
			throw new Exception("base64解码出错!");
		}
		return param;
	}

	public static String Base64Encode(String param, String code)
			throws Exception
	{
		byte[] bytes = (byte[])null;
		try {
			bytes = param.getBytes(code);
		} catch (UnsupportedEncodingException e) {
			throw new Exception("base64编码出错!");
		}
		String base64 = new BASE64Encoder().encodeBuffer(bytes);
		return base64;
	}

	public static String encoder(String param, String code)
			throws Exception
	{
		return Base64Encode(URLEncoder.encode(param, code), code);
	}

	public static String decoder(String param, String code)
			throws Exception
	{
		return URLDecoder.decode(Base64Decode(param, code), code);
	}
}
