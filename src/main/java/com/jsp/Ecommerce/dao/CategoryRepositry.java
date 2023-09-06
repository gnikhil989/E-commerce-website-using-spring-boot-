package com.jsp.Ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.Ecommerce.dto.Category;

@Repository
public interface CategoryRepositry extends JpaRepository<Category, Integer> {

}
