package com.jsp.Ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.Ecommerce.dto.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{

}
