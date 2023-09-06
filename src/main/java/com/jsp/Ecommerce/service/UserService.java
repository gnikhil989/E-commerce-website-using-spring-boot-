package com.jsp.Ecommerce.service;

import java.net.PasswordAuthentication;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.Ecommerce.controller.UserController;
import com.jsp.Ecommerce.dao.AdminRepository;
import com.jsp.Ecommerce.dao.UserRepository;
import com.jsp.Ecommerce.dao.WishlistRepository;
import com.jsp.Ecommerce.dto.AuthenticationToken;
import com.jsp.Ecommerce.dto.User;
import com.jsp.Ecommerce.dto.Wishlist;
import com.jsp.Ecommerce.exception.CustomException;
import com.jsp.common.ApiResponse;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	AuthenticationTokenService authenticationTokenService;
	
	@Autowired
	AdminRepository adminRepository;



	
	
//  to save user if user is new 
	@Transactional
	public User signup(User user) {
		if (Objects.nonNull(userRepository.findByEmail(user.getEmail()))) {
			throw new CustomException("user already exixts");
		}

//		to save user if not present already

//	hashing the password
//		String encryptpasswoprd = user.getUser_password();
//		try {System.out.println("=========================");
//			encryptpasswoprd = hashPassword(user.getUser_password());
//System.out.println("=========passowrd========");
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//			throw new CustomException(encryptpasswoprd);
//		}
//		user.setUser_password(encryptpasswoprd);
		user.setAdmin(adminRepository.findById(1).get());
		User user2 = userRepository.save(user);

//		 to create token
//		final AuthenticationToken authenticationToken= new AuthenticationToken(user2);
//		authenticationTokenService.saveConfirmationToken(authenticationToken);
//		
		return user2;
	}
	
	private String hashPassword(String user_password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(user_password.getBytes());
		byte[] digest = md.digest();
		String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		return hash;
	}

	public String signin(String email, String user_password) {
		// TODO Auto-generated method stub
		
		try {
			User user = userRepository.findByEmail(email);

			System.out.println(user.getEmail());
		
		if (user != null) {

			if (user.getUser_password().equals(user_password)) {

				return "login successfull";
			}
		}}catch (Exception e) {
			// TODO: handle exception
			return "invalid login ceredentials";
		}
		return "invaild";
	}

	public User getByEmail(String email) {
	try {
		User user=userRepository.findByEmail(email);
		System.out.println(user.getEmail());
		if(user!=null) {
			return user;
		}
	} catch (Exception e) {
		e.printStackTrace();
		
	}return null;
	}

	public List<User> getAllUser() {
		List<User> users=userRepository.findAll();
		return users;
	}

	public User updateByEmail(String email, User user) {
		User user2=userRepository.findByEmail(email);
		if(user2!=null) {
			user2.setEmail(user.getEmail());
			user2.setUser_firstname(user.getUser_firstname());
			user2.setUser_lastname(user.getUser_lastname());
			user2.setUser_password(user.getUser_password());
			user2.setCart(user.getCart());
			user2.setWishlist(user.getWishlist());
			user2.setAdmin(user.getAdmin());
			userRepository.save(user2);
		}
		return null;
	}

//	public String signin(String email, String password) {
//		User user=userRepository.findByEmail(email);
//		if(user!=null) {
//			if(user.getUser_password().equals(password)) {
////			AuthenticationToken authenticationToken	=authenticationTokenService.tokenRepository.findByUserUser_id(user.getUser_id());
////				return authenticationToken.getToken();
////			}
//	
//			}
//	
//	}return "invalid credentials";
}
