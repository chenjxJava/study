package com.freemark.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.freemark.utils.FreeMarkerUtil;

/**
 * Created by admin on 2017-06-30.
 */

	//模拟数据，进行测试
	public class TestFreeMarker {
	FreeMarkerUtil fu;
	Map<String, Object> root = null;

	@Before
	public void setUp() {
		fu = new FreeMarkerUtil();
		root = new HashMap<String, Object>();
	}

	@Test
	public void test01() {
		//1、创建数据模型
		Map<String, Object> root = new HashMap<String, Object>();
		//2、为数据模型添加值
		root.put("username", "张三");
		//3、将数据模型和模板组合的数据输出到控制台
		fu.print("test.ftl", root);
		fu.fprint("test.ftl", root, "01.html");
	}

	@Test
	public void test02() {
		//1、创建数据模型
		Map<String, Object> root = new HashMap<String, Object>();
		//2、为数据模型添加值
		root.put("username", "张三");
		root.put("password", "赵四");
		//3、将数据模型和模板组合的数据输出到控制台
		fu.print("01.ftl", root);
		//fu.fprint("01.ftl", root, "02.html");
	}
}
