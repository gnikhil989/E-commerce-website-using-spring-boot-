package com.jsp.Ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.Ecommerce.dao.AdminRepository;
import com.jsp.Ecommerce.dao.CartRepository;
import com.jsp.Ecommerce.dao.CategoryRepositry;
import com.jsp.Ecommerce.dao.MerchantRepository;
import com.jsp.Ecommerce.dao.ProductRepositry;
import com.jsp.Ecommerce.dto.Category;
import com.jsp.Ecommerce.dto.Login;
import com.jsp.Ecommerce.dto.Merchant;
import com.jsp.Ecommerce.dto.Product;

@Service
public class MerchantService {

	@Autowired
	MerchantRepository merchantRepository;
	
	@Autowired
	CategoryRepositry  categoryRepositry;;
	@Autowired
	ProductRepositry productRepositry;
	@Autowired
	AdminRepository adminRepository;
	
	public Merchant addMerchant(Merchant merchant) {
		merchant.setAdmin(adminRepository.findById(1).get());
		Merchant merchant2=merchantRepository.save(merchant);
		return merchant2;
	}


	public Merchant findByEmail(String email) {
		Merchant merchant=merchantRepository.findByEmail(email);
		return merchant;
	}


	public Merchant updateMerchantByEmail(String email, Merchant merchant) {
		Merchant merchant2=merchantRepository.findByEmail(email);
		if(merchant2!=null) {
			merchant2.setEmail(merchant.getEmail());
			merchant2.setName(merchant.getName());
			merchant2.setPassword(merchant.getPassword());
		Merchant merchant3=merchantRepository.save(merchant2);
		return merchant3;
		}
		return null;
	}


	public List<Merchant> getAllMerchants() {
		List<Merchant> merchants=merchantRepository.findAll();
		return merchants;
	}

	public void addProduct(Integer categoryid, Product product) {
		Category category = categoryRepositry.findById(categoryid).get();
		product.setCategory(category);
		product.setMerchants(product.getMerchants());
		productRepositry.save(product);
	}
	
	
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		System.out.println("==============");
		List<Product> products = productRepositry.findAll();
		return products;
	}
	public Product updateProductById(Integer id, Product product) {
		Product product2 = productRepositry.findById(id).get();
		if (product2 != null) {
			product2.setCategory(product.getCategory());
			product2.setDescription(product.getDescription());
			product2.setName(product.getName());
			product2.setPrice(product.getPrice());
			product2.setQuantity(product.getQuantity());
			product2.setMerchants(product.getMerchants());
			product2.setCarts(product.getCarts());
			product2.setWishlists(product.getWishlists());
			return productRepositry.save(product2);
		} else {
			return null;
		}

	}


	public Merchant merchantLogin(Login login) {
		Merchant merchant=merchantRepository.findByEmail(login.getEmail());
		if(merchant.getPassword().equals(login.getPassword())) {
			return merchant;
		}
		return null;
	}
}
