package com.shubo.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import org.apache.log4j.Logger;

/**
 * @Author: chenjx
 * @Description:
 * @Date: Created in 16:48 2017-08-25
 * @Modified By:
 */
public class BankInterfaceUtil {
	/*	public static String ServerIPAddress="172.16.100.53";
	public static int ServerPort=7072;

	public static String Qydm="3068";
	public static String SupAcctId="11014906518004";*/
	private static Logger logger = Logger.getLogger(BankInterfaceUtil.class);

	public static String ServerIPAddress="172.16.100.68";
	public static int ServerPort=7072;

	public static String Qydm="3081";
	public static String SupAcctId="11014792560009";

	public static HashMap newHashMap(String tranFunc) {
		HashMap parmaKeyDict = new HashMap();//用于存放生成向银行请求报文的参数
		//第二步：填写报文参数
		/*报文参数赋值*/
		parmaKeyDict.put("TranFunc", tranFunc);   //交易码，此处以【6000】接口为例子
		parmaKeyDict.put("Qydm", Qydm);       //企业代码
		parmaKeyDict.put("ThirdLogNo", getThirdLogNo()); //请求流水号
		parmaKeyDict.put("SupAcctId", SupAcctId); //资金汇总账号
		return parmaKeyDict;
	}

	public static HashMap getHashMap(HashMap parmaKeyDict) {
		//第三步：创建API对象，调用函数生成请求银行的报文
		/*获取请求报文*/
		ZJJZ_API_GW msg=new ZJJZ_API_GW ();
		String tranMessage=msg.getTranMessage(parmaKeyDict);//调用函数生成报文

		//__________________发报文_________________

		//第一步：准备入参变量
		//String ServerIPAddress="172.16.100.68";/*设置银行客户端端地址和端口*/
		//int ServerPort=7072; /*设置银行客户端端地址和端口*/
		HashMap retKeyDict = new HashMap();//用于存放银行返回报文的参数

		//第二步：创建API对象，调用函数发送。
		msg.SendTranMessage(tranMessage,ServerIPAddress,ServerPort,retKeyDict);

		//第三步：获取银行返回的报文
		String recvMessage=(String)retKeyDict.get("RecvMessage");//银行返回的报文

		//__________________解析报文_________________
		//第一步：创建API对象，解析返回报文
		retKeyDict= msg.parsingTranMessageString(recvMessage);
		logger.info(retKeyDict);
		return retKeyDict;
	}

	/**
	 * 获取请求流水号
	 *
	 * @return
	 */
	public static String getThirdLogNo() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
		String rdNum = df.format(new Date());
		Random random = new Random();
		int ird = random.nextInt(999999);
		String srd = String.format("%06d", ird);
		String thirdLogNo = rdNum + srd;
		System.out.println("ThirdLogNo:" + thirdLogNo);
		return thirdLogNo;
	}

	/**
	 * 写入文件
	 *
	 * @return
	 */
	public static void  writeToFile(String content) {
		File file = new File("c://shubo/log.txt");

		try (FileOutputStream fop = new FileOutputStream(file, true)) {

			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			content += "\r\n";
			// get the content in bytes
			byte[] contentInBytes = content.getBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		writeToFile("炸了啊");
	}
}
