package com.scube.edu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scube.edu.model.BranchMasterEntity;
import com.scube.edu.model.PassingYearMaster;
import com.scube.edu.model.SemesterEntity;
import com.scube.edu.repository.BranchMasterRepository;
import com.scube.edu.repository.SemesterRepository;
import com.scube.edu.response.BaseResponse;
import com.scube.edu.response.BranchResponse;
import com.scube.edu.response.SemesterResponse;

@Service
public class SemesterServiceImpl implements SemesterService{
	private static final Logger logger = LoggerFactory.getLogger(CollegeSeviceImpl.class);

	BaseResponse	baseResponse	= null;

    @Autowired
    SemesterRepository semesterRepository;
	
	@Override
	public List<SemesterResponse> getSemList(Long id, HttpServletRequest request) {
		logger.info("ID"+id);
		 List<SemesterResponse> branchList = new ArrayList<>();
			
			List<SemesterEntity> branchEntities    = semesterRepository.getSembyStreamId(id);
			for(SemesterEntity entity : branchEntities) {
				
				SemesterResponse response = new SemesterResponse();

				response.setId(entity.getId());
				response.setSemester(entity.getSemester());
				response.setStreamId(entity.getStreamId());
				response.setUniversityId(entity.getUniversityId());
				
				branchList.add(response);
			}
			return branchList;
	}

	@Override
	public SemesterEntity getSemById(Long id) {
		System.out.println(id);
		Optional<SemesterEntity> semEntity    = semesterRepository.findById(id);
		SemesterEntity branchEnt = semEntity.get();
		System.out.println("yearEnt---"+ branchEnt);
		
				return branchEnt;
}
	@Override
	public SemesterEntity getSemIdByNm(String sem ) {
		System.out.println(sem);
		SemesterEntity semEntity = semesterRepository.findBySemester(sem);
		System.out.println("yearEnt---"+ semEntity);
		
				return semEntity;
}
}
