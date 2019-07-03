package org.rave.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity

@NamedNativeQuery( name="getEducation",query="select * from education where userId=:userId",resultClass=Education.class)
@Table(name="education")
public class Education {
	
	@Id
	@Column(name="pkEducationId")
	private int pkEducationId;
	
	@Column(name="school")
	private String school;
	
	@Column(name="degree")
	private String degree;
	
	@Column(name="fieldStudy")
	private String fieldStudy;
	
	@Column(name="activity")
	private String activity;
	
	@Column(name="toDate")
	private String toDate;
	
	@Column(name="endDate")
	private String endDate;
	
	@Column(name="grade")
	private String grade;
	
	@Column(name="additionalNotes")
	private String additionalNotes;
	
	@Column(name="userId")
	private String userId;
	
	@Transient
	private String referringPageId;


	public int getPkEducationId() {
		return pkEducationId;
	}


	public void setPkEducationId(int pkEducationId) {
		this.pkEducationId = pkEducationId;
	}


	public String getSchool() {
		return school;
	}


	public void setSchool(String school) {
		this.school = school;
	}


	public String getDegree() {
		return degree;
	}


	public void setDegree(String degree) {
		this.degree = degree;
	}


	public String getFieldStudy() {
		return fieldStudy;
	}


	public void setFieldStudy(String fieldStudy) {
		this.fieldStudy = fieldStudy;
	}


	public String getActivity() {
		return activity;
	}


	public void setActivity(String activity) {
		this.activity = activity;
	}


	public String getToDate() {
		return toDate;
	}


	public void setToDate(String toDate) {
		this.toDate = toDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}


	public String getAdditionalNotes() {
		return additionalNotes;
	}


	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getReferringPageId() {
		return referringPageId;
	}


	public void setReferringPageId(String referringPageId) {
		this.referringPageId = referringPageId;
	}
		

	
	

}
