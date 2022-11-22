package com.sportyshoesproj.pojo;

public class Category {
	
	private static int categoryKeyValue = 1;
	private int categoryId;
	private String categoryName;

	public static int getCategoryKeyValue() {
		return categoryKeyValue;
	}

	public static void setCategoryKeyValue(int categoryKeyValue) {
		Category.categoryKeyValue = categoryKeyValue;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
