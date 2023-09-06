package com.jsp.Ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.Ecommerce.dto.AuthenticationToken;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer> {
//	 AuthenticationToken findByUserUser_id(int user_id);
}
