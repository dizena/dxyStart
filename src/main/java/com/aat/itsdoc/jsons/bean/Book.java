package com.aat.itsdoc.jsons.bean;

public class Book {
	private String id;
	private String name;
	private Integer pages;
	private Double price;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", pages=" + pages + ", price=" + price + "]";
	}

}
