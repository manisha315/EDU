package com.scube.edu.util;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lowagie.text.BadElementException;
import com.scube.edu.model.UserMasterEntity;
import com.scube.edu.model.VerificationRequest;
import com.scube.edu.repository.UserRepository;
import com.scube.edu.repository.VerificationRequestRepository;
import com.scube.edu.service.EmailService;

@Component
public class sendRecordOverdueMail {
	
	private static final Logger logger = LoggerFactory.getLogger(sendRecordOverdueMail.class);
	
	@Autowired
	VerificationRequestRepository verificationReqRepo;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EmailService emailService;
	
	@Value("${file.imagepath-dir}")
    private String imageLocation;
	
	@Scheduled(cron = "${sendRecordOverdueMail.cronTime}")	
	//@Scheduled(cron = "*/60 * * * * *")
	public void removeUnverifiedUser() throws Exception {
		
		logger.info("send mail if 7 days have passed after Verifier status change");
		
		List<VerificationRequest> list = verificationReqRepo.findByStatusForUniversityMail();
		
		for(VerificationRequest verReq: list) {
			
			Date date = new Date();
			Date closedDate = verReq.getClosedDate();
			
			long difference_In_Time =  date.getTime() - closedDate.getTime() ;
			
			long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;
			
			logger.info(difference_In_Time +" "+difference_In_Days);
			
			if(difference_In_Days >= 999) {
				
				long userId = verReq.getUserId();
				UserMasterEntity ume = userRepository.findById(userId);
				logger.info("send mail here  " + ume.getEmailId() + " " + verReq.getDocStatus());
				
				if(verReq.getDocStatus().equalsIgnoreCase("Approved_Pass") || verReq.getDocStatus().equalsIgnoreCase("SV_Approved_Pass")) {
					
					verReq.setDocStatus("Uni_Auto_Approved_Pass");
					verReq.setClosedDate(date);
					verReq.setUniAutoVerActionDate(date);
					verReq.setUniAutoVerStatus("Uni_Auto_Approved_Pass");
					verificationReqRepo.save(verReq);
					
					emailService.sendStatusMail(verReq.getAltEmail(), ume.getEmailId(), verReq.getId(), verReq.getDocStatus(), imageLocation);
					
				}
              if(verReq.getDocStatus().equalsIgnoreCase("Approved_Fail") || verReq.getDocStatus().equalsIgnoreCase("SV_Approved_Fail")) {
					
					verReq.setDocStatus("Uni_Auto_Approved_Fail");
					verReq.setClosedDate(date);
					verReq.setUniAutoVerActionDate(date);
					verReq.setUniAutoVerStatus("Uni_Auto_Approved_Fail");
					verificationReqRepo.save(verReq);
					
					emailService.sendStatusMail(verReq.getAltEmail(), ume.getEmailId(), verReq.getId(), verReq.getDocStatus(), imageLocation);
					
				}
				if(verReq.getDocStatus().equalsIgnoreCase("Rejected") || verReq.getDocStatus().equalsIgnoreCase("SV_Rejected") ){
					
					verReq.setDocStatus("Uni_Auto_Rejected");
					verReq.setClosedDate(date);
					verReq.setUniAutoVerActionDate(date);
					verReq.setUniAutoVerStatus("Uni_Auto_Rejected");
					verificationReqRepo.save(verReq);
					
					emailService.sendStatusMail(verReq.getAltEmail(),ume.getEmailId(), verReq.getId(), verReq.getDocStatus(), imageLocation);

					
					
					
				}							
				
				
			}
			
		}
		
	}

}
