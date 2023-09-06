package com.jsp.Ecommerce.dto;

import javax.persistence.Entity;


public class TempProduct {
private int id;
private String email;

private long quantity;
private int category;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}


public int getCategory() {
	return category;
}
public void setCategory(int category) {
	this.category = category;
}
public long getQuantity() {
	return quantity;
}
public void setQuantity(long quantity) {
	this.quantity = quantity;
}
}
