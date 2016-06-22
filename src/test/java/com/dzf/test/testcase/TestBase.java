package com.dzf.test.testcase;

import com.dzf.test.model.Handler;
import com.dzf.test.model.Project;
import com.dzf.test.model.Solution;
import com.dzf.test.model.WebDriverModel;
import com.dzf.test.util.PropertyUtil;
import com.dzf.test.util.XMLUtil;

public class TestBase {
	
//	public static Solution solution;
//	public static Project currentProject;
	public static WebDriverModel webdriver;
	
	public TestBase(){
//		if(solution == null){
//			try {
//				solution =XMLUtil.convert(PropertyUtil.BASEPATH+"/solution.xml",Solution.class.getName());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		
		if(webdriver == null ){
			webdriver = new WebDriverModel();
		}
	}

	

}
