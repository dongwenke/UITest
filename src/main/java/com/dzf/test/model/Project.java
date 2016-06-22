package com.dzf.test.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.dzf.test.util.PropertyUtil;
import com.dzf.test.util.XMLUtil;

//项目
@XmlType(name = "project")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "project")
public class Project{
	// 项目Id
	@XmlAttribute
	private int id;
	// 项目名称
	@XmlAttribute
	private String name;
	// 前台地址
	@XmlAttribute
	private String address;
	// 后台地址
	@XmlAttribute
	private String pages;
	// 页面Map
	private Map<String, Page> pageMap;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public Page getPage(String name) {
		loadPages();
		return this.pageMap.get(name);
	}

	private void loadPages() {
		if (pageMap == null || pageMap.size() <= 0) {
			pageMap = new HashMap<String, Page>();
			
			String pagePath = PropertyUtil.BASEPATH + "/page/" + pages;
			ArrayList<String> filelist = XMLUtil.getFileList(pagePath);
			if (filelist == null || filelist.size() <= 0)
				return;
			for (String pagefile : filelist) {
				try {
					Page page = XMLUtil.convert(pagefile, Page.class.getName());
//					page.setDriver(driver);
					page.setUrl(this.getAddress()+page.getUrl());
					pageMap.put(page.getName(), page);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
//	public Page getPage(String pageName) throws Exception {
//		Page page = null;
//		String pagePath = Global.BASEPATH + "/page/" + pages;
//		ArrayList<String> fileList = XMLUtil.getFiles(pagePath);
//		
//		Iterator<String> it =fileList.iterator();
//		while(it.hasNext()){
//			String fileName = it.next();
//			if(fileName.contains(pageName)){
//				page = XMLUtil.convert(fileName, Page.class.getName());
//				page.setDriver(driver);
//				page.setUrl(this.getAddress()+page.getUrl());
//			}
//		}
//		
//		return page;
//	}
	
	

}
