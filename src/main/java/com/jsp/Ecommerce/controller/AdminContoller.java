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
import org.springframework.web.bind.annotation.RestController;

import com.jsp.Ecommerce.dto.Admin;
import com.jsp.Ecommerce.dto.Login;
import com.jsp.Ecommerce.dto.User;
import com.jsp.Ecommerce.service.AdminService;
import com.jsp.Ecommerce.service.UserService;
import com.jsp.common.ApiResponse;

@RestController
@RequestMapping("/admin")
public class AdminContoller {

	@Autowired
	AdminService adminService;
	@Autowired
	UserService userService;
	


	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addAdmin(@RequestBody Admin admin) {
		Admin admin2 = adminService.addAdmin(admin);
		if (admin2 != null) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Admin Added Successfully"),
					HttpStatus.CREATED);
		}
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "invalid input"), HttpStatus.BAD_REQUEST);

	}

	@PutMapping("/update/{email}")
	public ResponseEntity<ApiResponse> updateAdmin(@PathVariable String email, @RequestBody Admin admin) {
		Admin admin2 = adminService.updateAdmin(email, admin);
		if (admin2 != null) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Admin Added Successfully"),
					HttpStatus.CREATED);
		}
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "invalid input"), HttpStatus.BAD_REQUEST);

	}
	
	@GetMapping("/login")
	public ResponseEntity<ApiResponse> adminLogin(@RequestBody Login login){
		Admin admin=adminService.adminLogin(login);
		if (admin != null) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Admin loged in Successfully"),
					HttpStatus.OK);
		}
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "invalid input"), HttpStatus.BAD_REQUEST);
		
	}

	@GetMapping("/{email}")
	public Admin getAdminByEmail(@PathVariable String email) {
		Admin admin = adminService.getAdminByEmail(email);
		return admin;
	}
	@GetMapping("/")
	public List<Admin> getAllAdmin() {
		List<Admin> admins=adminService.getAllAdmin();
		return admins;
	}
	
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		List<User> users=userService.getAllUser();
		return users;
	}
	@GetMapping("/{useremail}")
	public User getUserByEmail(@PathVariable String useremail){

		System.out.println(useremail);
		User user=userService.getByEmail(useremail);
		if(user!=null ) {
					return user;
							
		}
		return null;
	}	
	
}
