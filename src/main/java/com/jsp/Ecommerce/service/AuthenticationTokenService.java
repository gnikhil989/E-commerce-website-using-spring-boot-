package com.jsp.Ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.Ecommerce.dao.TokenRepository;
import com.jsp.Ecommerce.dto.AuthenticationToken;

@Service
public class AuthenticationTokenService {
@Autowired
TokenRepository tokenRepository;
//@Autowired
//AuthenticationToken authenticationToken;

public void saveConfirmationToken(AuthenticationToken authenticationToken) {
	tokenRepository.save(authenticationToken);
}
//	AuthenticationToken getTokenByUserId(int userId) {
//        return tokenRepository.findByUserUser_id(userId);
//    }
	
}

