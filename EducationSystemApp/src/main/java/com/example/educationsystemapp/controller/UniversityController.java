package com.example.educationsystemapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.educationsystemapp.dto.ResponseBody;
import com.example.educationsystemapp.dto.UniversityDTO;
import com.example.educationsystemapp.entity.University;
import com.example.educationsystemapp.exception.EducationSystemAppException;
import com.example.educationsystemapp.service.UniversityService;

@RestController
public class UniversityController {

	@Autowired
	private UniversityService universityService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/university")
	public ResponseEntity<?> addUniversity(@Valid @RequestBody UniversityDTO university)
			throws EducationSystemAppException {

		return new ResponseEntity<ResponseBody<UniversityDTO>>(new ResponseBody<UniversityDTO>(
				modelMapper.map(universityService.saveUniversity(modelMapper.map(university, University.class)),
						UniversityDTO.class),
				null, "University Added sucessfully", true), HttpStatus.OK);
	}

	@GetMapping("/university")
	public ResponseEntity<?> getUniversity() throws EducationSystemAppException {

		return new ResponseEntity<ResponseBody<List<UniversityDTO>>>(new ResponseBody<List<UniversityDTO>>(
				modelMapper.map(universityService.findAllUniversity(), new TypeToken<List<UniversityDTO>>() {
				}.getType()), null, "universities is Found ", true), HttpStatus.OK);

	}

	@GetMapping("/university/writtinginexcel")
	public ResponseEntity<?> writtingIntoExcel() throws EducationSystemAppException {

		universityService.writingInExcel();

		return new ResponseEntity<ResponseBody<Void>>(
				new ResponseBody<Void>(null, null, "Writting Into Excel Is Done", true), HttpStatus.OK);

	}

	@GetMapping("/university/readingfromexcel")
	public ResponseEntity<?> readingFromExcel() throws EducationSystemAppException {

		return new ResponseEntity<ResponseBody<List<String>>>(new ResponseBody<List<String>>(
				universityService.readingFromExcel(), null, "Reading From Excel is Done", true), HttpStatus.OK);

	}

}
