package com.dzf.test.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileUtil {

	public static void createFile(String path, String fileName) {

		// path表示你所创建文件的路径
		// path = "d:/tr/rt";
		File f = new File(path);
		if (!f.exists()) {
			f.mkdirs();
		}
		// fileName表示你创建的文件名；为txt类型；
		// fileName = "test.txt";
		File file = new File(f, fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void createFile(String fileName) {
		try {
			File file = new File(fileName);
			if (!file.exists())
				file.createNewFile();
		} catch (Exception e) {
			System.out.print("创建失败");
		}
	}
	
	public static void copyFile(String source,String destination){
		try {
			createFile(destination);
			FileUtils.copyFile(new File(source), new File(destination));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		createFile("./config/page/accounting/"+FileUtil.class.getSimpleName()+".xml");
	}
}
