package com.example.educationsystemapp.service.serviceimplementation;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.educationsystemapp.entity.College;
import com.example.educationsystemapp.entity.University;
import com.example.educationsystemapp.exception.service.EducationSystemAppServiceException;
import com.example.educationsystemapp.exception.service.custom.CollegeAlreadyExistsException;
import com.example.educationsystemapp.exception.service.custom.CollegeCountExceededException;
import com.example.educationsystemapp.exception.service.custom.CollegeNotFoundException;
import com.example.educationsystemapp.exception.service.custom.UniversityNotFoundException;
import com.example.educationsystemapp.repository.CollegeRepository;
import com.example.educationsystemapp.repository.UniversityRepository;
import com.example.educationsystemapp.service.CollegeService;

@Service
public class CollegeServiceImpl implements CollegeService {

	@Autowired
	private CollegeRepository collegeRepository;

	@Autowired
	private UniversityRepository universityRepository;

	@Override
	public College saveCollege(long id, College college) throws EducationSystemAppServiceException {

		University university = universityRepository.findById(id)
				.orElseThrow(() -> new UniversityNotFoundException("University not found."));

		if (collegeRepository.findByCollegeName(college.getCollegeName()).isPresent()) {
			throw new CollegeAlreadyExistsException("College Already Exists.");
		}

		if (university.getCollegeList().size() < university.getTotalColleges()) {

			university.getCollegeList().add(college);

			college.setUniversity(university);

			collegeRepository.save(college);

			return college;
		} else {
			throw new CollegeCountExceededException("College Count Exceeded");
		}

	}

	@Override
	public Set<College> findCollegesByCollegeId(long id) throws EducationSystemAppServiceException {
		University university = universityRepository.findById(id)
				.orElseThrow(() -> new UniversityNotFoundException("University not found."));

		university.getCollegeList().stream().findAny()
				.orElseThrow(() -> new CollegeNotFoundException("College Not Found."));

		return university.getCollegeList().stream().filter(c -> c.getTotalStudents() > 2).collect(Collectors.toSet());

	}

	public Set<College> getCollegeOfUniversity(String universityName) throws EducationSystemAppServiceException {

		University university = universityRepository.findByUniversityName(universityName)
				.orElseThrow(() -> new UniversityNotFoundException("University Is Not Found"));

		university.getCollegeList().stream().findAny()
				.orElseThrow(() -> new CollegeNotFoundException("College Not Found."));

		return university.getCollegeList().stream()
				.filter(c -> c.getTotalStudents() >= 100 && c.getTotalStudents() <= 200).collect(Collectors.toSet());

	}

}
