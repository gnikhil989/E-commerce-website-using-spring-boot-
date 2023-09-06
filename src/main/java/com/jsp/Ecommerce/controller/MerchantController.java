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

import com.jsp.Ecommerce.dao.ProductRepositry;
import com.jsp.Ecommerce.dto.Category;
import com.jsp.Ecommerce.dto.Login;
import com.jsp.Ecommerce.dto.Merchant;
import com.jsp.Ecommerce.dto.Product;
import com.jsp.Ecommerce.service.CategoryService;
import com.jsp.Ecommerce.service.MerchantService;
import com.jsp.Ecommerce.service.ProductService;
import com.jsp.common.ApiResponse;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

	@Autowired
	MerchantService merchantService;
	
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addMerchant(@RequestBody Merchant merchant){
		Merchant merchant2=merchantService.addMerchant(merchant);
		if (merchant2 != null) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Merchant Added Successfully"),
					HttpStatus.CREATED);
		}
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "invalid input"), HttpStatus.BAD_REQUEST);

	}
	
	@GetMapping("/{email}")
	public Merchant getByEmail(@PathVariable String email) {
		Merchant merchant=merchantService.findByEmail(email);
		return merchant;
	}
@PutMapping("/email")
public ResponseEntity<ApiResponse> updateMerchant(@PathVariable String email, @RequestBody Merchant merchant){
	Merchant merchant2=merchantService.updateMerchantByEmail(email,merchant);
	if (merchant2 != null) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Merchant updated Successfully"),
				HttpStatus.CREATED);
	}
	return new ResponseEntity<ApiResponse>(new ApiResponse(false, "invalid input"), HttpStatus.BAD_REQUEST);
}
@GetMapping("/")
public List<Merchant> getAllMerchants(){
	List<Merchant> merchants=merchantService.getAllMerchants();
	return merchants;
}
@GetMapping("/product/{id}")
public Product getProductbyEmail(@PathVariable Integer id){
	Product product=productService.getProductByid(id);
	return product;
}

@GetMapping("/login")
public ResponseEntity<ApiResponse> merchantLogin(@RequestBody Login  login){
	Merchant merchant=merchantService.merchantLogin(login);
	if (merchant != null) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Merchant login Successfully"),
				HttpStatus.OK);
	}
	return new ResponseEntity<ApiResponse>(new ApiResponse(false, "invalid input"), HttpStatus.BAD_REQUEST);

	
}


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
@GetMapping("/productlist")
public List<Product> getAllProduct() {
	List<Product> products = productService.getAllProduct();
	return products;
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
