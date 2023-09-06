package com.jsp.Ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jsp.Ecommerce.dto.Admin;
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
	@Query("SELECT a FROM Admin  a WHERE a.email = ?1")
    Admin findByEmail(String email);
}
