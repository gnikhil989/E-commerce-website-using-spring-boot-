package com.jsp.Ecommerce.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int user_id;
private String user_firstname;
private String user_lastname;
private String email;
private String user_password;

@JsonIgnore
@OneToOne(cascade = CascadeType.ALL)
private Wishlist wishlist;
@JsonIgnore
@OneToOne
private Cart cart;
@ManyToOne(cascade = CascadeType.ALL)
private Admin admin;
public Wishlist getWishlist() {
	return wishlist;
}
public void setWishlist(Wishlist wishlist) {
	this.wishlist = wishlist;
}
public int getUser_id() {
	return user_id;
}
public void setUser_id(int user_id) {
	this.user_id = user_id;
}


public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getUser_password() {
	return user_password;
}
public void setUser_password(String user_password) {
	this.user_password = user_password;
}
public String getUser_firstname() {
	return user_firstname;
}
public void setUser_firstname(String user_firstname) {
	this.user_firstname = user_firstname;
}
public String getUser_lastname() {
	return user_lastname;
}
public void setUser_lastname(String user_lastname) {
	this.user_lastname = user_lastname;
}
public Cart getCart() {
	return cart;
}
public void setCart(Cart cart) {
	this.cart = cart;
}
public Admin getAdmin() {
	return admin;
}
public void setAdmin(Admin admin) {
	this.admin = admin;
}

}
