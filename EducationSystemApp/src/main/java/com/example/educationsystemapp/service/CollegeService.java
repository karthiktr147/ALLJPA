package com.example.educationsystemapp.service;

import java.util.Set;

import com.example.educationsystemapp.entity.College;
import com.example.educationsystemapp.exception.service.EducationSystemAppServiceException;

public interface CollegeService {

	College saveCollege(long universityId, College college) throws EducationSystemAppServiceException;

	Set<College> findCollegesByCollegeId(long universityId) throws EducationSystemAppServiceException;

	Set<College> getCollegeOfUniversity(String universityName) throws EducationSystemAppServiceException;

}
