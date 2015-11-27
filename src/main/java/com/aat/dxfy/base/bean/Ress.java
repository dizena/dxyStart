package com.aat.dxfy.base.bean;

import java.io.Serializable;

public class Ress implements Serializable, Comparable<Ress> {
	private static final long serialVersionUID = 1L;
	private String id;
	private String resType;
	private String resName;
	private String resUrl;
	private Integer resSort;
	private Integer resLevel;
	private String resPid;
	private String resAuth;
	private String resIcon;
	private String locked;

	public int compareTo(Ress o) {
		if (this.getResLevel() < o.getResLevel()) {
			return -1;
		} else if (this.getResLevel() == o.getResLevel()) {
			if (this.getResSort() < o.getResSort()) {
				return -1;
			} else if (this.getResSort() == o.getResSort()) {
				return 0;
			} else if (this.getResSort() > o.getResSort()) {
				return 1;
			}
		} else if (this.getResLevel() > o.getResLevel()) {
			return 1;
		}
		return 0;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getResUrl() {
		return resUrl;
	}

	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}

	public Integer getResSort() {
		return resSort;
	}

	public void setResSort(Integer resSort) {
		this.resSort = resSort;
	}

	public Integer getResLevel() {
		return resLevel;
	}

	public void setResLevel(Integer resLevel) {
		this.resLevel = resLevel;
	}

	public String getResPid() {
		return resPid;
	}

	public void setResPid(String resPid) {
		this.resPid = resPid;
	}

	public String getResAuth() {
		return resAuth;
	}

	public void setResAuth(String resAuth) {
		this.resAuth = resAuth;
	}

	public String getResIcon() {
		return resIcon;
	}

	public void setResIcon(String resIcon) {
		this.resIcon = resIcon;
	}

	public String getLocked() {
		return locked;
	}

	public void setLocked(String locked) {
		this.locked = locked;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ress other = (Ress) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
