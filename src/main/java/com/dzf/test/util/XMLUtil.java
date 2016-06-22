package com.dzf.test.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class XMLUtil {

	/**
	 * 把XML文件转化成java对象
	 * 
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public static <T> T convert(String xml, Class<T> clazz) throws Exception {
		return convert(xml,clazz.getName());
	}
	
	public static <T> T convert(String xml, String className) throws Exception {
		JAXBContext jaxb = JAXBContext.newInstance(Class.forName(className));
		Unmarshaller unmarshaller = jaxb.createUnmarshaller();
		InputStreamReader reader = new InputStreamReader(new FileInputStream(xml), "UTF-8");
		T obj = (T) unmarshaller.unmarshal(reader);
		reader.close();
		return obj;
	}
	
	public static ArrayList<String> getFileList(String filePath) {
		ArrayList<String> filelist = new ArrayList<String>();
		File root = new File(filePath);
		File[] files = root.listFiles();
		if(files==null||files.length<=0) return null;
		for (File file : files) {
			if (file.isDirectory()) {
				/*
				 * 递归调用
				 */
				getFileList(file.getAbsolutePath());
				
			} else
			{
				filelist.add(file.getAbsolutePath());
			}
		}
		return filelist;
	}
}
