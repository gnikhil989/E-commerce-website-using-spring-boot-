package com.jsp.Ecommerce.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.Ecommerce.dao.ProductRepositry;
import com.jsp.Ecommerce.dao.UserRepository;
import com.jsp.Ecommerce.dao.WishlistRepository;
import com.jsp.Ecommerce.dto.Product;
import com.jsp.Ecommerce.dto.User;
import com.jsp.Ecommerce.dto.Wishlist;

@Service
public class WishlistService {
	@Autowired
	WishlistRepository wishlistRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ProductRepositry productRepositry;

	public Wishlist addToWishlist(int id, String email) {
		System.out.println("======1================");
		User user = userRepository.findByEmail(email);
		System.out.println("=========2============");
		int id1 = user.getUser_id();
		System.out.println(id1);
		try {
			Wishlist wishlist = wishlistRepository.findById(id1).get();
			wishlist.setCreatedDate(LocalDate.now());

			System.out.println("=====3===========");
			List<Product> products = wishlist.getProducts();
			Product product = productRepositry.findById(id).get();
			products.add(product);
			wishlist.setProducts(products);
			user.setWishlist(wishlist);
			userRepository.save(user);
		
			Wishlist wishlist3 = wishlistRepository.save(wishlist);
			System.out.println("====4=====");
			return wishlist3;
		} catch (Exception e) {
			System.out.println("=================55=========");
			Wishlist wishlist1 = new Wishlist();
			List<Product> products = new ArrayList();
			Product product = productRepositry.findById(id).get();
			products.add(product);
			wishlist1.setProducts(products);
			System.out.println("=555555555=555555=");
			user.setWishlist(wishlist1);
			userRepository.save(user);
			Wishlist wishlist2 = wishlistRepository.save(wishlist1);
			System.out.println("===========6=============");
			return wishlist2;
		}

	}

	public List<Product> getWishlist(String email) {
		User user = userRepository.findByEmail(email);
		int id1 = user.getUser_id();
		Wishlist wishlist = wishlistRepository.findById(id1).get();
		List<Product> products = wishlist.getProducts();
		return products;

	}
}