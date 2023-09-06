package com.jsp.Ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.Ecommerce.dao.CartRepository;
import com.jsp.Ecommerce.dao.OrderCartRepository;
import com.jsp.Ecommerce.dao.UserRepository;
import com.jsp.Ecommerce.dto.Cart;
import com.jsp.Ecommerce.dto.OrderCart;
import com.jsp.Ecommerce.dto.Product;
import com.jsp.Ecommerce.dto.User;

@Service
public class OrderCartService {
	@Autowired
	OrderCartRepository orderCartRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CartRepository cartRepository;

	public OrderCart getProductByProductId(int id) {
		return orderCartRepository.findByProductid(id);
	}

	public OrderCart saveProduct(OrderCart orderCart) {
		return orderCartRepository.save(orderCart);
	}

	public List<OrderCart> getListOfOrderCartByEmail(String email) {
		User user = userRepository.findByEmail(email);
		Cart cart = cartRepository.findById(user.getUser_id()).get();
		List<OrderCart> orderCarts = cart.getOrderCarts();
		return orderCarts;
	}
}