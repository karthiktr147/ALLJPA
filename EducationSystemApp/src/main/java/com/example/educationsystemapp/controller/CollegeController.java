package com.example.educationsystemapp.controller;

import java.util.Set;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.educationsystemapp.dto.CollegeDTO;
import com.example.educationsystemapp.dto.ResponseBody;
import com.example.educationsystemapp.entity.College;
import com.example.educationsystemapp.exception.EducationSystemAppException;
import com.example.educationsystemapp.service.CollegeService;

@RestController
public class CollegeController {

	@Autowired
	private CollegeService collegeService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/college/{universityId}")
	public ResponseEntity<?> addCollege(@PathVariable long universityId, @Valid @RequestBody CollegeDTO college)
			throws EducationSystemAppException {

		return new ResponseEntity<ResponseBody<CollegeDTO>>(new ResponseBody<CollegeDTO>(
				modelMapper.map(collegeService.saveCollege(universityId, modelMapper.map(college, College.class)),
						CollegeDTO.class),
				null, "College Added Sucessfully", true), HttpStatus.OK); 
	}

	@GetMapping("/college/{universityId}")
	public ResponseEntity<?> getCollege(@PathVariable long universityId) throws EducationSystemAppException {

		return new ResponseEntity<ResponseBody<Set<CollegeDTO>>>(new ResponseBody<Set<CollegeDTO>>(modelMapper
				.map(collegeService.findCollegesByCollegeId(universityId), new TypeToken<Set<CollegeDTO>>() {
				}.getType()), null, "College found In university", true), HttpStatus.OK);
	}

	@GetMapping("/collegeinrange/{universityName}")
	public ResponseEntity<?> getCollegeOfUniversity(@PathVariable String universityName) throws EducationSystemAppException {

		return new ResponseEntity<ResponseBody<Set<CollegeDTO>>>(new ResponseBody<Set<CollegeDTO>>(modelMapper
				.map(collegeService.getCollegeOfUniversity(universityName), new TypeToken<Set<CollegeDTO>>() {
				}.getType()), null, "College With Total student above 100 and less 200 in University "+universityName+" is found" , true), HttpStatus.OK);
	}
}
