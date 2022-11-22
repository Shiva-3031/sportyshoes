package com.sportyshoesproj.pojo;

public class PurchaseReport {
	
	private int purchaseId;
	private int purchaseUserId;
	private String purchaseUsername;
	private int purchaseProductId;
	private Product product;
	private String date;
	
	public int getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}
	public int getPurchaseUserId() {
		return purchaseUserId;
	}
	public void setPurchaseUserId(int purchaseUserId) {
		this.purchaseUserId = purchaseUserId;
	}
	public String getPurchaseUsername() {
		return purchaseUsername;
	}
	public void setPurchaseUsername(String purchaseUsername) {
		this.purchaseUsername = purchaseUsername;
	}
	public int getPurchaseProductId() {
		return purchaseProductId;
	}
	public void setPurchaseProductId(int purchaseProductId) {
		this.purchaseProductId = purchaseProductId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "PurchaseReport [purchaseId=" + purchaseId + ", purchaseUserId=" + purchaseUserId + ", purchaseUsername="
				+ purchaseUsername + ", purchaseProductId=" + purchaseProductId + ", product=" + product + ", date="
				+ date + "]";
	}	
	

}
