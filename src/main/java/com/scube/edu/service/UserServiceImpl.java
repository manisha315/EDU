package com.scube.edu.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scube.edu.controller.UserController;
import com.scube.edu.model.RoleMaster;
import com.scube.edu.model.UserMasterEntity;
import com.scube.edu.repository.UserRepository;
import com.scube.edu.request.UserAddRequest;
import com.scube.edu.response.BaseResponse;
import com.scube.edu.response.UserResponse;
import com.scube.edu.util.StringsUtils;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	RoleService roleService;
	
	 @Value("${file.url-dir}")
     private String url;
	
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public List<UserResponse> getUserList() {
		
		List<UserResponse> userList = new ArrayList<>();
		
		List<UserMasterEntity> userEntities    = userRepository.findAll();
		
		for(UserMasterEntity entity : userEntities) {
			
			UserResponse userResponse = new UserResponse();
			
			RoleMaster role = roleService.getNameById(entity.getRoleId());

			userResponse.setId(entity.getId());
			userResponse.setCompany_name(entity.getCompanyName());
			userResponse.setEmail(entity.getEmailId());
//			userResponse.setGstno(entity.getGSTNo());
			userResponse.setRole_id(entity.getRoleId());
			userResponse.setRole_name(role.getRoleName());
			userResponse.setFirst_name(entity.getFirstName());
			userResponse.setLast_name(entity.getLastName());
			userResponse.setPhone_no(entity.getPhoneNo());
			
			userList.add(userResponse);
		}
		
		return userList;
	
	}
	
	public UserResponse getUserInfoById(Long userId) {
			
			System.out.println("********UserServiceImpl getUserInfoById********"+ userId);
			
			UserResponse user = new UserResponse();
			
			Optional<UserMasterEntity> ent = userRepository.findById(userId);
			UserMasterEntity entt = ent.get();
			
			user.setEmail(entt.getEmailId());
			user.setPhone_no(entt.getPhoneNo());
			user.setId(entt.getId());
			user.setCompany_name(entt.getCompanyName());
			user.setContact_person_phone(entt.getContactPersonPhone());
			user.setCompany_email(entt.getCompanyEmailId());
			if(entt.getLastName() != null) {
				user.setName(entt.getFirstName() +" " + entt.getLastName());
			}else {
				user.setName(entt.getFirstName());
			}
			user.setContact_person_name(entt.getContactPersonName());
			user.setFirst_name(entt.getFirstName());
			user.setLast_name(entt.getLastName());
			user.setRole_id(entt.getRoleId());
			
			return user;
		
		}

	@Override
	public Boolean addNewUser(UserAddRequest userReq) throws Exception {
		
		logger.info("*******UserServiceImpl addNewUser*******"+ userReq.getEmailId());
		
		UserMasterEntity userMasterEntity  =new  UserMasterEntity();
		 
		UserMasterEntity userEntities  = userRepository.findByEmailId(userReq.getEmailId());
		
			if(userEntities != null) {
				
				throw new Exception("This email id already exists");
			}
			
//			RoleMaster role = roleService.getNameById(userReq.getRoleId());
		
			userMasterEntity.setFirstName(userReq.getFirstName());
			userMasterEntity.setLastName(userReq.getLastName());
			userMasterEntity.setEmailId(userReq.getEmailId());
			userMasterEntity.setPhoneNo(userReq.getPhoneNumber());
			userMasterEntity.setPassword(encoder.encode(userReq.getPassword()));
			userMasterEntity.setEmailVerificationStatus("N");
			userMasterEntity.setUniversityId(userReq.getUniversityId());
			userMasterEntity.setUsername(userReq.getEmailId());
			userMasterEntity.setRoleId(userReq.getRoleId());
			userMasterEntity.setIsactive("Y");
			
			emailService.sendVerificationEmail(userReq.getEmailId(), url);
			
			userRepository.save(userMasterEntity);
			
		
		return true;
	}

	
	


	

}
