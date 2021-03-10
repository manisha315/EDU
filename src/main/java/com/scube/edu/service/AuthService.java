package com.scube.edu.service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import com.scube.edu.request.LoginRequest;
import com.scube.edu.request.UserAddRequest;
import com.scube.edu.response.BaseResponse;

public interface AuthService {
	
	public BaseResponse authenticateUser(LoginRequest loginRequest , HttpServletRequest request) throws Exception;
	
	
	public Boolean addUser(UserAddRequest userRequest) throws Exception;


	public BaseResponse resetPasswordByMail(HttpServletRequest request) throws MessagingException;

}