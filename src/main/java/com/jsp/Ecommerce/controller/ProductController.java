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

import com.jsp.Ecommerce.dto.Category;
import com.jsp.Ecommerce.dto.Product;
import com.jsp.Ecommerce.service.CategoryService;
import com.jsp.Ecommerce.service.ProductService;
import com.jsp.common.ApiResponse;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;

	@PostMapping("/addproduct/{categoryid}")
	public ResponseEntity<ApiResponse> createProduct(@PathVariable Integer categoryid, @RequestBody Product product) {
		Category category = categoryService.getCategoryById(categoryid);
		if (category != null) {
			productService.addProduct(categoryid, product);
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "product added successfully"),
					HttpStatus.CREATED);
		} else {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "invalid category id"),
					HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/list")
	public List<Product> getAllProduct() {
		List<Product> products = productService.getAllProduct();
		return products;
	}

	@GetMapping("/{id}")
	public Product getProductById(@PathVariable Integer id) {
		Product product = productService.getProductByid(id);
		return product;
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateProductById(@PathVariable Integer id, @RequestBody Product product) {
		Product product2 = productService.updateProductById(id, product);
		if (product2 != null) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "product updated successfully"),
					HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "invaild id"), HttpStatus.BAD_REQUEST);
		}
	}
}
