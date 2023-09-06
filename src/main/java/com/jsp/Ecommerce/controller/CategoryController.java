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
import com.jsp.Ecommerce.service.CategoryService;
import com.jsp.common.ApiResponse;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category) {
		categoryService.createCategory(category);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "created the category"), HttpStatus.CREATED);
	}

	@GetMapping("/list")
	public List<Category> listCategory() {
		return categoryService.getCategoryList();

	}

	@GetMapping("/{id}")
	public Category getCategoryById(@PathVariable Integer id) {
		Category category = categoryService.getCategoryById(id);
		return category;
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<ApiResponse> updatedCategory(@PathVariable Integer id, @RequestBody Category category) {
		Category category2 = categoryService.getCategoryById(id);
		if (category2 != null) {
			categoryService.updateCategory(id, category);
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "updated successfully"), HttpStatus.OK);
		} else {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "invaild id"), HttpStatus.EXPECTATION_FAILED);
		}
	}

}
