package com.aat.dxfy.base.bean;

import java.io.Serializable;

public class Urlauth implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String uname;
	private String url;
	private String roles;
	private String auths;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getAuths() {
		return auths;
	}

	public void setAuths(String auths) {
		this.auths = auths;
	}

}
