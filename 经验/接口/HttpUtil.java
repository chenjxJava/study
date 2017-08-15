package com.yl.oms.api.protocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.yl.oms.api.util.DESUtil;

/**
 * 发送Http请求工具类
 * @author Tolk
 */
public class HttpUtil {
	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	private static int TIME_OUT = 15000;//超时时间
	
	public final static String APP_KEY = "075587fef18911e594980800277a9591";
	
	public static String sendPostByOneParamNoException(String url , String paramName, String param){
		try{
			return sendPostByOneParam(url, paramName, param);
		}catch(Exception e){
			logger.error("sendPostByOneParamError, Message: " + e.toString() + ", url: " + url + ", paramName: " + paramName + ", param: " + param);
			return null;
		}
	}
	
	/**
	 * Post发送一个参数的请求
	 * @param url
	 * @param paramName
	 * @param param
	 * @return
	 * @throws IOException 
	 */
	public static String sendPostByOneParam(String url , String paramName, String param) throws IOException {
		String result = null;
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add(paramName, param);
		result = restTemplate.postForObject(url,map,String.class);
        return result;
	}
	
	/**
	 * Post发送各个参数的请求(JSON转化格式)
	 * @param url
	 * @param paramName
	 * @param param
	 * @return
	 * @throws IOException 
	 */
	public static String sendPostParamByJson(String url , String jsonStr) throws IOException {
		StringBuffer body = new StringBuffer();
		JSONObject json = JSONObject.fromObject(jsonStr);
		
		for (Object key : json.keySet()) {
			if(body.length() > 0){
				body.append("&");
			}
			body.append(key).append("=").append(json.optString(key.toString()));
		}
        return sendPostParamToBody(url, body.toString());
	}
	
	/**
	 * Post发送带参数的请求
	 * @param url
	 * @param body
	 * @return
	 * @throws IOException 
	 */
	public static String sendPostParamToBody(String url , String body) throws IOException {
		String result = null;
		PrintWriter out = null;
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(TIME_OUT);
            conn.setReadTimeout(TIME_OUT);
            out = new PrintWriter(conn.getOutputStream());
            out.print(body);
            out.flush();
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuffer sb = new StringBuffer();
            while ((line = in.readLine()) != null) {
            	sb.append(line);
            }
            result = sb.toString();
        } finally{
            try{
                if(out!=null) out.close();
                if(in!=null) in.close();
            } catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
	}
	
	/**
	 * 从Body中发送Post请求
	 * @throws IOException 
	 */
	public static String sendPostByBody(String url, String param) throws IOException  {
		String result = null;
		OutputStreamWriter out = null;
        BufferedReader in = null;
        try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url)
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setConnectTimeout(TIME_OUT);
			connection.setReadTimeout(TIME_OUT);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestMethod("POST"); // 设置请求方式
			connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
			connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
			connection.connect();
			out = new OutputStreamWriter(
					connection.getOutputStream(), "UTF-8"); // utf-8编码
			out.append(param);
			out.flush();
			out.close();
			// 读取响应
			int length = (int) connection.getContentLength();// 获取长度
			InputStream is = connection.getInputStream();
			if (length != -1) {
				byte[] data = new byte[length];
				byte[] temp = new byte[512];
				int readLen = 0;
				int destPos = 0;
				while ((readLen = is.read(temp)) > 0) {
					System.arraycopy(temp, 0, data, destPos, readLen);
					destPos += readLen;
				}
				result = new String(data, "UTF-8"); // utf-8编码
			}
        } finally{
            try{
                if(out!=null) out.close();
                if(in!=null) in.close();
            } catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
	
	/**
	 * 从Body中发送Post请求
	 */
	public static String sendPostByBodyNoException(String url, String param)  {
		String result = null;
		try{
			result = sendPostByBody(url, param);
		}catch(Exception e){
			logger.error("sendPostByBodyError, Message: " + e.toString());
		}
        return result;
    }
	
	/**
	 * DL Http 对接方式
	 * <br/>从Body中发送Post请求
	 * @param url Post链接
	 * @param param Post参数
	 * @throws IOException 
	 */
	public static String sendDLPost(String url, String param) throws IOException  {
		return sendDLPost(url, param, APP_KEY, true);
	}
	
	/**
	 * DL Http 对接方式
	 * <br/>从Body中发送Post请求, 默认加密
	 * @param url Post链接
	 * @param param Post参数
	 * @param param AppKey参数
	 */
	public static String sendDLPost(String url, String param, String appKey) throws IOException  {
		return sendDLPost(url, param, appKey, true);
	}
	
	/**
	 * DL Http 对接方式
	 * <br/>从Body中发送Post请求
	 * @param url Post链接
	 * @param param Post参数
	 * @param param AppKey参数
	 * @param needEncrypt 是否需要加密参数
	 * @throws IOException 
	 */
	public static String sendDLPost(String url, String param, String appKey, boolean needEncrypt) throws IOException  {
		String result = null;
		OutputStreamWriter out = null;
        BufferedReader in = null;
        String secretKeyStr = appKey;
        try {
        	String sParam = null;
        	String sApiHash = null;
        	try {
        		if(appKey.length() < 32){
        			secretKeyStr = DESUtil.string2MD5(appKey);
        		}
        		if(needEncrypt){
        			sParam = DESUtil.encrypt(param, secretKeyStr );
        		}else{
        			sParam = param; 
        		}
        		sApiHash = DESUtil.string2MD5(appKey + sParam);
			} catch (Exception e) {
				e.printStackTrace();
			}
			HttpURLConnection connection = (HttpURLConnection) new URL(url)
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setConnectTimeout(TIME_OUT);
			connection.setReadTimeout(TIME_OUT);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestMethod("POST"); // 设置请求方式
			connection.setRequestProperty("Dapi-App-Key", appKey);
			connection.setRequestProperty("Dapi-Encrypt", needEncrypt + "");
			connection.setRequestProperty("Dapi-Hash", sApiHash);
			connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
			connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Accept-Charset", "utf-8");
			connection.setRequestProperty("contentType", "utf-8");
			connection.setRequestProperty("Charset", "utf-8");
			connection.connect();
			out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
			out.append(sParam);
			out.flush();
			out.close();
			// 读取响应
			int length = (int) connection.getContentLength();// 获取长度
			InputStream is = connection.getInputStream();
			if (length != -1) {
				byte[] data = new byte[length];
				byte[] temp = new byte[512];
				int readLen = 0;
				int destPos = 0;
				while ((readLen = is.read(temp)) > 0) {
					System.arraycopy(temp, 0, data, destPos, readLen);
					destPos += readLen;
				}
				result = new String(data, "UTF-8"); // utf-8编码
				try {
					result = DESUtil.decrypt(result, secretKeyStr);
				} catch (Exception e) {
					logger.error("解密失败,Url: " + url + ", Message: " + e.toString());
					e.printStackTrace();
				}
			}
        } finally{
            try{
                if(out!=null) out.close();
                if(in!=null) in.close();
            } catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
}
