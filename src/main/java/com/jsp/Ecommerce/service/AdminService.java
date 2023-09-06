package com.jsp.Ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.Ecommerce.dao.AdminRepository;
import com.jsp.Ecommerce.dao.UserRepository;
import com.jsp.Ecommerce.dto.Admin;
import com.jsp.Ecommerce.dto.Login;
import com.jsp.Ecommerce.dto.User;

@Service
public class AdminService {
	@Autowired
	AdminRepository adminRepository;
@Autowired
UserRepository userRepository;

	public Admin addAdmin(Admin admin) {
		
		Admin admin2 = adminRepository.save(admin);
		if (admin2 != null) {
			return admin2;
		}
		return null;
	}

	public Admin updateAdmin(String email, Admin admin) {
		Admin admin2=adminRepository.findByEmail(email);
		if(admin2!=null) {
			admin2.setEmail(admin.getEmail());
			admin2.setMerchants(admin.getMerchants());
			admin2.setName(admin.getName());
			admin2.setPassword(admin.getPassword());
			admin2.setUsers(admin.getUsers());
			Admin admin3=adminRepository.save(admin2);
			return admin3;
		}
		
		return null;
	}

	public Admin getAdminByEmail(String email) {
		Admin admin=adminRepository.findByEmail(email);
		return admin;
	}

	public List<Admin> getAllAdmin() {
		List<Admin>admins=adminRepository.findAll();
		return admins;
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

	public Admin adminLogin(Login login) {
		Admin admin=adminRepository.findByEmail(login.getEmail());
		if(admin.getPassword().equals(login.getPassword())) {
			return admin;
		}
		return null;
	}
}
