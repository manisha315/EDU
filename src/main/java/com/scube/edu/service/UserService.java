package com.scube.edu.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.scube.edu.request.UserAddRequest;
import com.scube.edu.response.BaseResponse;
import com.scube.edu.response.UserResponse;

 
public interface UserService {
	
	public List<UserResponse> getUserList();

	public UserResponse getUserInfoById(Long userId);

	public Boolean addNewUser(UserAddRequest userRequest) throws Exception;

	public boolean deleteUserById(long id, HttpServletRequest request) throws Exception;

	public boolean updateUserById(UserAddRequest userRequest, HttpServletRequest request) throws Exception;

}
