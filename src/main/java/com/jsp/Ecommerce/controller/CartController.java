package com.jsp.Ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.Ecommerce.dto.Cart;
import com.jsp.Ecommerce.dto.TempProduct;
import com.jsp.Ecommerce.service.CartService;
import com.jsp.common.ApiResponse;

@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired
	CartService cartService;
	
	@PostMapping("/addto")
	public ResponseEntity<ApiResponse> addToCart(@RequestBody TempProduct tempProduct){
		Cart cart=cartService.addToCart(tempProduct);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "addedd successfully"), HttpStatus.CREATED);
	}
	@PutMapping("/update")
    public ResponseEntity<ApiResponse> updateCart(@RequestBody TempProduct tempProduct) {
        try {
            Cart cart = cartService.updateCart(tempProduct);

            if (cart != null) {
                return new ResponseEntity<>(new ApiResponse(true, "Cart updated successfully"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse(false, "Failed to update cart"), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            // Handle exceptions
            return new ResponseEntity<>(new ApiResponse(false, "An error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
