package com.dzf.test.model;

/**
 * @author Administrator
 * 模版科目
 */
public class TemplateSubject {
	private String subject;
	private String direction;
	private String summary;

	public TemplateSubject(String subject, String direction, String summary) {
		this.subject = subject;
		this.direction = direction;
		this.summary = summary;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

}
