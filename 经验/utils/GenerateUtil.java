package com.chenjx.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Author: chenjx
 * @Description:
 * @Date: Created in 15:26 2017-09-01
 * @Modified By:
 */
public class GenerateUtil {
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

}
