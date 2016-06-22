package com.dzf.test;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.gargoylesoftware.htmlunit.javascript.host.file.File;

public class Main {
	public static void main(String[] args) {
//		XmlSuite suite = new XmlSuite();
//		List<String> fileList  = new ArrayList<>();
//		fileList.add("config/process1.xml");
//		suite.setSuiteFiles(fileList);

//		XmlTest test = new XmlTest(suite);
//		test.setXmlSuite(suite);
//		test.setName("TmpTest");
		
//		List<XmlClass> classes = new ArrayList<XmlClass>();
//		classes.add(new XmlClass("test.failures.Child"));
//		test.setXmlClasses(classes) ;
//		然后你可以将XmlSuite传递给TestNG：

//		List<XmlSuite> suites = new ArrayList<XmlSuite>();
//		suites.add(suite);
		TestNG tng = new TestNG();
//		tng.setXmlSuites(suites);
		tng.run();
		}
}
