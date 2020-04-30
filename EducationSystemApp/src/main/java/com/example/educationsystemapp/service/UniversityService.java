package com.example.educationsystemapp.service;

import java.util.List;

import com.example.educationsystemapp.entity.University;
import com.example.educationsystemapp.exception.service.EducationSystemAppServiceException;

public interface UniversityService {

	University saveUniversity(University university) throws EducationSystemAppServiceException;

	List<University> findAllUniversity() throws EducationSystemAppServiceException;

	void writingInExcel() throws EducationSystemAppServiceException;

	List<String> readingFromExcel() throws EducationSystemAppServiceException;

}
