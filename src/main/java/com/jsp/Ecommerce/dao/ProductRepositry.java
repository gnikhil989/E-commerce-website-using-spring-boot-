package com.jsp.Ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.Ecommerce.dto.Product;

@Repository
public interface ProductRepositry extends JpaRepository<Product, Integer> {

}
