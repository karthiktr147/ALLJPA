package com.example.educationsystemapp.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.educationsystemapp.entity.University;

public class CollegeDTO implements Comparable<CollegeDTO> {
	private long collegeId;

	@NotBlank(message = "College Name cant not be empty.")
	private String collegeName;

	@Min(value = 1, message = "Total Students in college can not be empty")
	private int totalStudents;

	private University university;

	public CollegeDTO() {
		// TODO Auto-generated constructor stub
	}

	public CollegeDTO(long collegeId, @NotNull(message = "College Name cant not be empty.") String collegeName,
			int totalStudents, University university) {
		this.collegeId = collegeId;
		this.collegeName = collegeName;
		this.totalStudents = totalStudents;
		this.university = university;
	}

	public CollegeDTO(long collegeId, @NotNull(message = "College Name cant not be empty.") String collegeName,
			int totalStudents) {
		this.collegeId = collegeId;
		this.collegeName = collegeName;
		this.totalStudents = totalStudents;
	}

	public long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(long collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public int getTotalStudents() {
		return totalStudents;
	}

	public void setTotalStudents(int totalStudents) {
		this.totalStudents = totalStudents;
	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	@Override
	public int compareTo(CollegeDTO c) {
		return (int) (this.getCollegeId() - c.getCollegeId());

	}

}
