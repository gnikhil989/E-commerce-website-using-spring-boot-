package com.jsp.Ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.Ecommerce.dto.Product;
import com.jsp.Ecommerce.dto.User;
import com.jsp.Ecommerce.dto.Wishlist;
import com.jsp.Ecommerce.service.UserService;
import com.jsp.Ecommerce.service.WishlistService;
import com.jsp.common.ApiResponse;

@RestController
@RequestMapping("/user")
public class UserController {
@Autowired
UserService userService;
@Autowired
WishlistService wishlistService;
	

// to signup
	@PostMapping("/signup")
	public ResponseEntity<ApiResponse> signup(@RequestBody User user){
		User user2=  userService.signup(user);
		if(user2!=null) {
		 return new ResponseEntity<ApiResponse>(new ApiResponse(true, "user data saved"), HttpStatus.CREATED);
	}else {
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "invalid input or user already exists in database"), HttpStatus.BAD_REQUEST);
	}
}
	
//	to update user
	@PutMapping("/update/{email}")
	public ResponseEntity<ApiResponse> update(@PathVariable String email  ,@RequestBody User user){
		User user2=userService.updateByEmail(email,user);
		if(user2!=null) {
			 return new ResponseEntity<ApiResponse>(new ApiResponse(true, "user data updated"), HttpStatus.CREATED);
		}else {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "invalid input or user already exists in database"), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
//	to sign in
	
	@GetMapping("/signin")
	public ResponseEntity<ApiResponse> signin(@RequestParam String email, @RequestParam String user_password ){

		System.out.println(email);
		String token=userService.signin(email,user_password);
		if(token.equals("login successfull") ) {
					return new ResponseEntity<ApiResponse>(new ApiResponse(true, token), HttpStatus.OK);
		}
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, token), HttpStatus.BAD_REQUEST);
	}
	@GetMapping("/{email}")
	public User getByEmail(@PathVariable String email){

		System.out.println(email);
		User user=userService.getByEmail(email);
		if(user!=null ) {
					return user;
							
		}
		return null;
	}	
	

	@GetMapping("/list")
	public List<User> getAllUser() {
		List<User> users=userService.getAllUser();
		return users;
	}
}