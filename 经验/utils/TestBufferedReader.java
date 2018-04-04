package com.yuelan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

/**
 * @Author: chenjx
 * @Description: 逐行读取文件
 * @Date: Created in 9:22 2018/4/3
 * @Modified By:
 */
public class TestBufferedReader {

	@Test
	public void read() {

		long before = new Date().getTime();
		ArrayList<String> arrayList = new ArrayList<>();
		try {
			FileReader fileReader = new FileReader(new File("D:\\0402.txt"));
			BufferedReader br = new BufferedReader(fileReader);
			String line = "";
			while ((line = br.readLine()) != null) {
				 arrayList.add(line);
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(arrayList);
		

		long now = new Date().getTime();
		System.out.println(now - before);

	}
}
