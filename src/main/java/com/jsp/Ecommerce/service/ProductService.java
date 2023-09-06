package com.jsp.Ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.Ecommerce.dao.CategoryRepositry;
import com.jsp.Ecommerce.dao.ProductRepositry;
import com.jsp.Ecommerce.dto.Category;
import com.jsp.Ecommerce.dto.Product;
import com.jsp.common.ApiResponse;

@Service
public class ProductService {

	@Autowired
	ProductRepositry productRepositry;
	@Autowired
	CategoryRepositry categoryRepositry;
	public Object getAllProduct;

	public void addProduct(Integer categoryid, Product product) {
		Category category = categoryRepositry.findById(categoryid).get();
		product.setCategory(category);
		productRepositry.save(product);
	}

	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		System.out.println("==============");
		List<Product> products = productRepositry.findAll();
		return products;
	}

	public Product getProductByid(Integer id) {
		Product product = productRepositry.findById(id).get();
		if (product != null) {
			return product;
		} else {
			return null;
		}
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
}
