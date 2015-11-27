package com.aat.dxfy.base.bean;

import java.io.Serializable;

public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String role;
	private String state;
	private String locked;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLocked() {
		return locked;
	}

	public void setLocked(String locked) {
		this.locked = locked;
	}

}
