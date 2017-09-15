package com.shubo.utils.password;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
public class Snippet {
	/**
	 * 参数解密,并转换成json字符串
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public static JSONObject DecryptionToJson(String param) throws Exception {
		param = EncryptAndDecodeHelper.decoder(param, "UTF-8");
		return JSONObject.parseObject(param);
	}

	/**
	 * 参数加密
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String formtParam(Object obj) throws Exception {
		String json = JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd", new SerializerFeature[0]);
		return EncryptAndDecodeHelper.encoder(json, "GBK");
	}

	/**
	 * 参数加密
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String formtParam1(Object obj) throws Exception {
		String json = JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd HH:mm:ss", new SerializerFeature[0]);
		return EncryptAndDecodeHelper.encoder(json, "GBK");
	}

	public static String formtDate(String obj) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 小写的mm表示的是分钟
		Date date = sdf.parse(obj);
		return sdf.format(date);
	}

	// 序列化
	public static byte[] serialize(Object obj) {
		ObjectOutputStream obi = null;
		ByteArrayOutputStream bai = null;
		try {
			bai = new ByteArrayOutputStream();
			obi = new ObjectOutputStream(bai);
			obi.writeObject(obj);
			byte[] byt = bai.toByteArray();
			return byt;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 反序列化
	public static Object unserizlize(byte[] byt) {
		ObjectInputStream oii = null;
		ByteArrayInputStream bis = null;
		bis = new ByteArrayInputStream(byt);
		try {
			oii = new ObjectInputStream(bis);
			Object obj = oii.readObject();
			return obj;
		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}
}
