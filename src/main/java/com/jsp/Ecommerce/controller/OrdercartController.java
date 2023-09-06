package com.jsp.Ecommerce.controller;

import java.lang.invoke.CallSite;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.Ecommerce.dto.OrderCart;
import com.jsp.Ecommerce.dto.Product;
import com.jsp.Ecommerce.service.OrderCartService;
import com.jsp.common.ApiResponse;

@RestController
@RequestMapping("/ordercart")
public class OrdercartController {
@Autowired
OrderCartService orderCartService;

@GetMapping("/{email}")
public List<OrderCart> getUserOrderCart(@PathVariable String email){
	List<OrderCart> orderCarts=orderCartService.getListOfOrderCartByEmail(email); 
	if(orderCarts!=null) {
	return orderCarts;
	}else {
		return null;
	}
}
}
