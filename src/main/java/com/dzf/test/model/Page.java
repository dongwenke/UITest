package com.dzf.test.model;

import java.util.*;

import javax.xml.bind.annotation.*;


/**
 * 页面元素 将xml中的element转化为java对象
 * 
 * @author Administrator
 * 
 */
@XmlType(name = "page")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "page")
public class Page{
	@XmlAttribute
	private int id;// 页面Id
	
	@XmlAttribute
	private String name;// 页面名称
	
	@XmlAttribute
	private String url;// 页面地址
	
	@XmlAttribute
	private String method;
	
	@XmlAttribute
	private String value;
	
	@XmlElement(name = "element")
	private List<Element> elementList;
	//创建control名称与control的对应关系，通过控件名称拿到属性
	private Map<String, Element> elementMap;
	
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<Element> getElementList() {
		return elementList;
	}

	public void setElementList(List<Element> elementList) {
		this.elementList = elementList;
	}

	//初始化controlMap
	private void loadControlMap() {
		if (elementMap == null || elementMap.size() <= 0) {
			elementMap = new HashMap<String, Element>();
			if (elementList == null || elementList.size() <= 0)
				return;

			for (Element element : elementList) {
				elementMap.put(element.getName(), element);
			}
		}
	}

	public Element getElement(String name){
		loadControlMap();
		if (elementMap.containsKey(name)!=true) {
			return null;
		}
		return elementMap.get(name);
	}
}
