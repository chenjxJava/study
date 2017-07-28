package com.freemark.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import freemarker.template.Version;

public class Freemarkers {
	private static Freemarkers fk;;
	private static Configuration cfg;

	private Freemarkers() {
	}
	/**
	 *
	 * @param freemarkerVersionNo freemarker的版本号
	 * @param templatePath 模版加载路径
	 * @return
	 */
	public static Freemarkers getInstance(String freemarkerVersionNo, String templatePath) {
		if (null == fk) {
			cfg = new Configuration(new Version(freemarkerVersionNo));
			cfg.setClassForTemplateLoading(Freemarkers.class, templatePath);
			fk=new Freemarkers();
		}
		return fk;
	}
	/**
	 *
	 * @param templateName 根据模版名称加载对应模版
	 * @return
	 */
	private Template getTemplate(String templateName){
		try {
			return cfg.getTemplate(templateName);
		} catch (TemplateNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedTemplateNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
	 *
	 * @param dataModel 数据模型
	 * @param templateName 输出模版
	 */
	public void sprint(Map<String, Object> dataModel,String templateName){
		try {
			this.getTemplate(templateName).process(dataModel, new PrintWriter(System.out));
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param dataModel 数据模型
	 * @param templateName 输出模版
	 * @param filePath  输出文件路径
	 */
	public void fprint(Map<String, Map<String, ArrayList>> dataModel, String templateName, String filePath){
		try {
			this.getTemplate(templateName).process(dataModel, new FileWriter(new File(filePath)));
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}