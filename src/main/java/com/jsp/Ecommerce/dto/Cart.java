package com.jsp.Ecommerce.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;	
@OneToMany(cascade = CascadeType.ALL)
	private List<OrderCart> orderCarts;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Product> products;
	@OneToOne(cascade = CascadeType.ALL)
	private User user;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<OrderCart> getOrderCarts() {
		return orderCarts;
	}
	public void setOrderCarts(List<OrderCart> orderCarts) {
		this.orderCarts = orderCarts;
	}
	/**
	 * @param id
	 * @param orderCarts
	 * @param products
	 * @param user
	 */
	public Cart(int id, List<OrderCart> orderCarts, List<Product> products, User user) {
		super();
		this.id = id;
		this.orderCarts = orderCarts;
		this.products = products;
		this.user = user;
	}
	/**
	 * 
	 */
	public Cart() {
	
	}
	
}
