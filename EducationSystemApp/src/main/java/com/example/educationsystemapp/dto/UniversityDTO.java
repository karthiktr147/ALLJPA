package com.example.educationsystemapp.dto;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.educationsystemapp.entity.College;

public class UniversityDTO implements Comparable<UniversityDTO> {

	private long universityId;

	@NotBlank(message = "University Name Can not Be Empty.")
	private String universityName;

	@Min(value = 1, message = "Total College in university Can not be null")
	private int totalColleges;

	private List<College> collegeList;

	public UniversityDTO() {
		// TODO Auto-generated constructor stub
	}

	public UniversityDTO(long universityId,
			@NotNull(message = "University Name Can not Be Empty.") String universityName, int totalColleges,
			List<College> collegeList) {
		this.universityId = universityId;
		this.universityName = universityName;
		this.totalColleges = totalColleges;
		this.collegeList = collegeList;
	}

	public long getUniversityId() {
		return universityId;
	}

	public void setUniversityId(long universityId) {
		this.universityId = universityId;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public int getTotalColleges() {
		return totalColleges;
	}

	public void setTotalColleges(int totalColleges) {
		this.totalColleges = totalColleges;
	}

	public List<College> getCollegeList() {
		return collegeList;
	}

	public void setCollegeList(List<College> collegeList) {
		this.collegeList = collegeList;
	}

	public UniversityDTO(long universityId,
			@NotNull(message = "University Name Can not Be Empty.") String universityName, int totalColleges) {
		this.universityId = universityId;
		this.universityName = universityName;
		this.totalColleges = totalColleges;
	}

	@Override
	public int compareTo(UniversityDTO o) {
		return (int) (this.getUniversityId() - o.getUniversityId());
	}

}
