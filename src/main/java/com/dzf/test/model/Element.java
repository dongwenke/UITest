package com.dzf.test.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 页面元素 将xml中的element转化为java对象
 */
@XmlType(name = "element")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "element")
public class Element {
	// 控件标识
	@XmlAttribute
	private int id;
	// 控件名称
	@XmlAttribute
	private String name;
	// 定位控件方法
	@XmlAttribute
	private String method;
	// 定位控件值
	@XmlAttribute
	private String value;

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

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
