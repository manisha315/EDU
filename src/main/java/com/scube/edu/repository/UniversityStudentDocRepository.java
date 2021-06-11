package com.scube.edu.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.Convert;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scube.edu.model.UniversityStudentDocument;
import com.scube.edu.model.UserMasterEntity;
import com.scube.edu.response.UniversityStudentDocumentResponse;

@Repository
public interface UniversityStudentDocRepository extends JpaRepository<UniversityStudentDocument, Long> {

	@Query(value = "SELECT * FROM university_studentdocument where enrollment_no = ?1 "
			+ "and passing_year_id = ?2 and semester_id = ?3 and stream_id = ?4" , nativeQuery = true)
//	@Query(value = "SELECT * FROM university_studentdocument where enrollment_no = ?1 ", nativeQuery = true)
	UniversityStudentDocument getDocDataByFourFields(String enrollmentNo , String yearOfPassing, Long semId, Long streamId);

	UniversityStudentDocument findByEnrollmentNoAndStreamIdAndPassingYearIdAndSemId(String enrollmentNo,Long stream,Long passyr,Long semId);

	
	@Query(value = "SELECT * FROM university_studentdocument where enrollment_no LIKE %?1% and passing_year_id LIKE %?2% and stream_id LIKE %?3% and semester_id LIKE %?4% " , nativeQuery = true)
	List<UniversityStudentDocument> searchByEnrollmentNoLikeAndPassingYearIdLikeAndStreamIdLikeAndSemIdLike(
			String enrollmentno, String yearofpassid, String streamid,String semId);



}
