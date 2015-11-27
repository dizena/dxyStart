package com.aat.dxfy.base.bean;

import java.io.Serializable;
import java.util.Date;

public class OuthToken implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String companyId;
	private String userId;
	private Date startTime;
	private String eename;
	private String flag;//0禁用 1可用

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getEename() {
		return eename;
	}

	public void setEename(String eename) {
		this.eename = eename;
	}


	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
