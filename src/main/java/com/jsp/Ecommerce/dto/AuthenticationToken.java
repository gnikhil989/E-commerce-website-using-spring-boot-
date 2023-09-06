package com.jsp.Ecommerce.dto;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tokens")
public class AuthenticationToken {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int token_id;
	private String token;
	private Date created_date;
	@OneToOne(targetEntity = User.class  ,fetch = FetchType.EAGER)
	private User user;
	public int getToken_id() {
		return token_id;
	}
	public void setToken_id(int token_id) {
		this.token_id = token_id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @param user
	 */
	public AuthenticationToken(User user) {
		this.user = user;
	this.created_date= new Date();
	this.token=UUID.randomUUID().toString();
	
	}
	
	
}
