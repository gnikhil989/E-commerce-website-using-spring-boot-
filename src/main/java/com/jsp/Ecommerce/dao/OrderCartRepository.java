package com.jsp.Ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.Ecommerce.dto.OrderCart;

@Repository
public interface OrderCartRepository extends JpaRepository<OrderCart, Integer> {
	OrderCart findByProductid(int productid);

}
