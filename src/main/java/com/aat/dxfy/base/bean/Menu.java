package com.aat.dxfy.base.bean;

import java.io.Serializable;
import java.util.List;

public class Menu implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String url;
	private Integer sort;
	private String icon;
	private List<Menu> subs;

	public Menu(String name, String url, Integer sort, String icon) {
		this.name = name;
		this.url = url;
		this.sort = sort;
		this.icon = icon;
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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<Menu> getSubs() {
		return subs;
	}

	public void setSubs(List<Menu> subs) {
		this.subs = subs;
	}

}