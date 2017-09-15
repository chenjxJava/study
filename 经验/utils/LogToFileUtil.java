package com.chenjx.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: chenjx
 * @Description:
 * @Date: Created in 15:15 2017-09-01
 * @Modified By:
 */
public class LogToFileUtil {
	private static String PROJECT_DIR = System.getProperty("user.dir");
	/**
	 * 写入文件
	 *
	 * @return
	 */
	public static void  writeToFile(String content) {
		File file = new File(getProjectDir());

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
		/*String projectDir = getProjectDir();
		System.out.println(projectDir);*/

		writeToFile("测试test");
	}

	private static String getProjectDir() {
		File dir = new File(PROJECT_DIR + "//ssm");
		if(!dir.exists()) {
			dir.mkdir();
		}
		StringBuffer sb = new StringBuffer();
		String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		sb.append(PROJECT_DIR).append("//ssm//log").append(currentDate).append(".txt");
		return sb.toString();
	}

}
