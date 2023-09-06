	package com.jsp.Ecommerce.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "products")
public class Product {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String name;
private String description;
private double price;
private long quantity;
@ManyToOne
private Category category;
@ManyToMany(cascade =  CascadeType.ALL)
private List<Merchant> merchants;
@JsonIgnore
@ManyToMany(mappedBy = "products")
private List<Wishlist> wishlists;
@JsonIgnore
@ManyToMany(mappedBy = "products")
private List<Cart> carts;

public List<Cart> getCarts() {
	return carts;
}
public void setCarts(List<Cart> carts) {
	this.carts = carts;
}
public List<Wishlist> getWishlists() {
	return wishlists;
}
public void setWishlists(List<Wishlist> wishlists) {
	this.wishlists = wishlists;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public Category getCategory() {
	return category;
}
public void setCategory(Category category) {
	this.category = category;
}
public long getQuantity() {
	return quantity;
}
public void setQuantity(long quantity) {
	this.quantity = quantity;
}
public List<Merchant> getMerchants() {
	return merchants;
}
public void setMerchants(List<Merchant> merchants) {
	this.merchants = merchants;
}

}
