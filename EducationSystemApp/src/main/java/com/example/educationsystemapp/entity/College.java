package com.example.educationsystemapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "collegeId")
public class College implements Comparable<College> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "College_id")
	private long collegeId;

	@Column(name = "College_Name")
	private String collegeName;

	@Column(name = "Total_student")
	private int totalStudents;

	@JsonIgnore
	@ManyToOne
	private University university;

	public College() {
		// TODO Auto-generated constructor stub
	}

	public College(long collegeId, String collegeName, int totalStudents, University university) {
		this.collegeId = collegeId;
		this.collegeName = collegeName;
		this.totalStudents = totalStudents;
		this.university = university;
	}

	public College(long collegeId, String collegeName, int totalStudents) {
		this.collegeId = collegeId;
		this.collegeName = collegeName;
		this.totalStudents = totalStudents;
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

	public long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(long collegeId) {
		this.collegeId = collegeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (collegeId ^ (collegeId >>> 32));
		result = prime * result + ((collegeName == null) ? 0 : collegeName.hashCode());
		result = prime * result + totalStudents;
		result = prime * result + ((university == null) ? 0 : university.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		College other = (College) obj;
		if (collegeId != other.collegeId)
			return false;
		if (collegeName == null) {
			if (other.collegeName != null)
				return false;
		} else if (!collegeName.equals(other.collegeName))
			return false;
		if (totalStudents != other.totalStudents)
			return false;
		if (university == null) {
			if (other.university != null)
				return false;
		} else if (!university.equals(other.university))
			return false;
		return true;
	}

	@Override
	public int compareTo(College o) {
		
		return (int) (this.getCollegeId()-o.getCollegeId());
	}


}
