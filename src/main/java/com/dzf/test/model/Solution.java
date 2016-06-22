package com.dzf.test.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

//项目
@XmlType(name = "solution")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "solution")
public class Solution{

	@XmlAttribute
	private int id;
	@XmlAttribute
	private String name;
	@XmlElement(name = "project")
	private List<Project> projectList;
	// 项目Map
	private Map<String, Project> projectMap;

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

	public List<Project> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}

	/**
	 * 将项目集对象中项目集合，转换为Map
	 * 
	 * @return
	 * @throws Exception
	 */
	private void loadProjectMap() {
		if (projectMap == null || projectMap.size() <= 0) {
			projectMap = new HashMap<String, Project>();
			
			for (Project project : this.getProjectList()) {
//				project.setDriver(driver);
				projectMap.put(project.getName(), project);
			}
		}
	}

	// 通过项目名称取得项目
	public Project getProject(String name) {
		loadProjectMap();
		return projectMap.get(name);
	}

}
