package com.scube.edu.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scube.edu.request.StudentDocVerificationRequest;
import com.scube.edu.response.BaseResponse;

import com.scube.edu.response.PriceMasterResponse;



import com.scube.edu.response.StudentVerificationDocsResponse;
import com.scube.edu.service.StudentService;
import com.scube.edu.util.StringsUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/student")
public class StudentController {
	
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

	BaseResponse response = null;
	
	@Autowired
	StudentService	studentService;
	
	@GetMapping("/getVerificationDataByUserid/{userId}")
	public  ResponseEntity<Object> getVerificationDataByUserid(@PathVariable long userId) {
		
		response = new BaseResponse();
		
		    try {

		    	List<StudentVerificationDocsResponse> list = studentService.getVerificationDocsDataByUserid(userId);

					
					response.setRespCode(StringsUtils.Response.SUCCESS_RESP_CODE);
					response.setRespMessage(StringsUtils.Response.SUCCESS_RESP_MSG);
					response.setRespData(list);
					
					return ResponseEntity.ok(response);
						
				}catch (Exception e) {
					
					logger.error(e.getMessage()); //BAD creds message comes from here
					
					response.setRespCode(StringsUtils.Response.FAILURE_RESP_CODE);
					response.setRespMessage(StringsUtils.Response.FAILURE_RESP_MSG);
					response.setRespData(e.getMessage());
					
					return ResponseEntity.badRequest().body(response);
					
				}
			
   }
	
	@GetMapping("/getClosedRequests/{userId}")
	public  ResponseEntity<Object> getClosedRequests(@PathVariable long userId) {
		
		response = new BaseResponse();
		
		    try {
		    	List<StudentVerificationDocsResponse> list = studentService.getClosedRequests(userId);
					
					response.setRespCode(StringsUtils.Response.SUCCESS_RESP_CODE);
					response.setRespMessage(StringsUtils.Response.SUCCESS_RESP_MSG);
					response.setRespData(list);
					
					return ResponseEntity.ok(response);
						
				}catch (Exception e) {
					
					logger.error(e.getMessage()); //BAD creds message comes from here
					
					response.setRespCode(StringsUtils.Response.FAILURE_RESP_CODE);
					response.setRespMessage(StringsUtils.Response.FAILURE_RESP_MSG);
					response.setRespData(e.getMessage());
					
					return ResponseEntity.badRequest().body(response);
					
				}
			
   }
	
	
	@GetMapping("/saveVerificationDocAndCalculateAmount")
	public  ResponseEntity<Object> saveVerificationDocAndCalculateAmount(@RequestBody List<StudentDocVerificationRequest> studentDocReq, HttpServletRequest request) {
		
		response = new BaseResponse();
		
		    try {
		    	HashMap<String, Long> list = studentService.saveVerificationDocAndCalculateAmount(studentDocReq, request);
					
					response.setRespCode(StringsUtils.Response.SUCCESS_RESP_CODE);
					response.setRespMessage(StringsUtils.Response.SUCCESS_RESP_MSG);
					response.setRespData(list);
					
					return ResponseEntity.ok(response);
						
				}catch (Exception e) {
					
					logger.error(e.getMessage()); //BAD creds message comes from here
					
					response.setRespCode(StringsUtils.Response.FAILURE_RESP_CODE);
					response.setRespMessage(StringsUtils.Response.FAILURE_RESP_MSG);
					response.setRespData(e.getMessage());
					
					return ResponseEntity.badRequest().body(response);
					
				}
			
   }
	
	
	
	
	
	
	

}