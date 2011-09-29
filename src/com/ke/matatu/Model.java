package com.ke.matatu;

public class Model {
	
	public Model() {}
	
	public Model(String icon, String title, String body) {
		this.icon = icon;
		this.title = title;
		this.body = body;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getIcon() {
		return icon;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}

	public void setBody(String body) {
		this.body = body;
	}
	public String getBody() {
		return body;
	}

	private String icon;
	private String title;
	private String body;
}
