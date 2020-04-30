package com.example.educationsystemapp.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "universityId")
public class University implements Comparable<University> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "university_id")
	private long universityId;

	@Column(name = "University_name")
	private String universityName;

	@Column(name = "Total_college")
	private int totalColleges;

	@JsonIgnore
	@OneToMany(mappedBy = "university")
	private List<College> collegeList;

	public University() {
		// TODO Auto-generated constructor stub
	}

	public University(long universityId, String universityName, int totalColleges, List<College> collegeList) {
		this.universityId = universityId;
		this.universityName = universityName;
		this.totalColleges = totalColleges;
		this.collegeList = collegeList;
	}

	public University(long universityId, String universityName, int totalColleges) {
		this.universityId = universityId;
		this.universityName = universityName;
		this.totalColleges = totalColleges;
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

	public long getUniversityId() {
		return universityId;
	}

	public void setUniversityId(long universityId) {
		this.universityId = universityId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((collegeList == null) ? 0 : collegeList.hashCode());
		result = prime * result + totalColleges;
		result = prime * result + (int) (universityId ^ (universityId >>> 32));
		result = prime * result + ((universityName == null) ? 0 : universityName.hashCode());
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
		University other = (University) obj;
		if (collegeList == null) {
			if (other.collegeList != null)
				return false;
		} else if (!collegeList.equals(other.collegeList))
			return false;
		if (totalColleges != other.totalColleges)
			return false;
		if (universityId != other.universityId)
			return false;
		if (universityName == null) {
			if (other.universityName != null)
				return false;
		} else if (!universityName.equals(other.universityName))
			return false;
		return true;
	}

	@Override
	public int compareTo(University u) {

		return (int) (this.getUniversityId() - u.getUniversityId());

	}

}
