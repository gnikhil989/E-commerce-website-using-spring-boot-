package com.jsp.Ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.Ecommerce.dto.Product;
import com.jsp.Ecommerce.dto.Wishlist;
import com.jsp.Ecommerce.service.WishlistService;
import com.jsp.common.ApiResponse;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
@Autowired
WishlistService wishlistService;

// to save wishlist
@PostMapping("/{email}/{id}")
public ResponseEntity<ApiResponse> addToWishlist(@PathVariable int id, @PathVariable String email){
 Wishlist wishlist =	wishlistService.addToWishlist(id,email);
 if(wishlist!=null) {
	return new ResponseEntity<ApiResponse>(new ApiResponse(true, "addedd to wishlist"), HttpStatus.CREATED);
}return new ResponseEntity<ApiResponse>(new ApiResponse(false, "invaild email or product "), HttpStatus.BAD_REQUEST);
}
@GetMapping("/{email}")
public List<Product> getWishlist(@PathVariable String email){
	List<Product> products=wishlistService.getWishlist(email);
	return products;
}

}