package com.scube.edu.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString


public class UniversityStudentRequest {
	
	private long id;
	private String collegeId;
	private String enrollmentNo;
	private String firstName;
	private String lastName;
	private String passingYearId;
	private String streamId;
	private String OriginalDOCuploadfilePath;
	

	

}
