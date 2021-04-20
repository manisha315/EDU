package com.scube.edu.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scube.edu.model.DocumentMaster;
import com.scube.edu.model.PassingYearMaster;
import com.scube.edu.model.StreamMaster;
import com.scube.edu.model.UserMasterEntity;
import com.scube.edu.model.VerificationRequest;
import com.scube.edu.repository.UniversityVerifierRepository;
import com.scube.edu.repository.UserRepository;
import com.scube.edu.repository.VerificationRequestRepository;
import com.scube.edu.response.BaseResponse;
import com.scube.edu.response.RequestTypeResponse;
import com.scube.edu.response.UniversityVerifierResponse;
import com.scube.edu.response.VerificationResponse;

@Service
public class UniversityVerifierServiceImpl implements UniversityVerifierService{
private static final Logger logger = LoggerFactory.getLogger(UniversityStudentDocServiceImpl.class);
	
	BaseResponse	baseResponse	= null;
	
	@Autowired
	UniversityVerifierRepository universityVerifierRepository;
	
	
	@Autowired
	VerificationRequestRepository verificationReqRepository;
	
	@Autowired 
	StreamService streamService;
	 
	@Autowired 
	RequestTypeService reqTypeService;
	
	@Autowired
	YearOfPassingService yearOfPassService;
	
	@Autowired
	DocumentService	documentService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	UserService userService;
	@Override
	public List<UniversityVerifierResponse> getUniversityVerifierRequestList() throws Exception {
	logger.info("*******UniversityVerifierServiceImpl getUniversityVerifierRequestList*******");
		
	
	List<UniversityVerifierResponse> responseList = new ArrayList<>();
	
	List<VerificationRequest> list = universityVerifierRepository.findByStatus();
	logger.info("uvlist"+list);
	for(VerificationRequest req: list) {
		
		UniversityVerifierResponse resp = new UniversityVerifierResponse();
		
		PassingYearMaster year = yearOfPassService.getYearById(req.getYearOfPassingId());
		
		DocumentMaster doc = documentService.getNameById(req.getDocumentId());
		
		StreamMaster stream = streamService.getNameById(req.getStreamId());
				
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		Date date=new Date();
		if(doc!=null) {
		resp.setStatus(req.getDocStatus());
		resp.setDocName(doc.getDocumentName()); 
		}
		if(year!=null) {
		resp.setYearofPassing(year.getYearOfPassing());	
		}
		if(stream!=null) {
		resp.setStream(stream.getStreamName());
		}
		resp.setFullName(req.getFirstName() + " " + req.getLastName());
		
		responseList.add(resp);
		
	}		
		return responseList;
	}
	

}