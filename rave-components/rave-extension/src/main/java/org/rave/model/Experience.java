package org.rave.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Transient;
import javax.persistence.Table;


@Entity

@NamedNativeQuery(name="getExperience",query="select * from experience where userId=:userId",resultClass=Experience.class)
@Table(name="experience")
public class Experience {
	
	@Id
	@Column(name="pkExperienceId")
	private int pkExperienceId; 
	
	@Column(name="userId")
	private String userName;
	
	@Column(name="CompanyName")
	private String companyName;
	
	@Column(name="companyLocation")
	private String companyLocation;
	
	@Column(name="companyDesc")
	private String companyDesc;
	
	@Column(name="companyWebsite")
	private String companyWebsite;
	
	@Column(name="startDate")
	private String startDate;

	@Column(name="endDate")
	private String endDate;
	
	@Column(name="currentlyWork")
	private String currentlyWork;
	
	@Column(name="jobTitle")
	private String jobTitle;
	
	@Column(name="jobDesc")
	private String jobDesc;
	
	@Transient
	private String referringPageId;
	
	
	public String getReferringPageId() {
		return referringPageId;
	}
	public void setReferringPageId(String referringPageId) {
		this.referringPageId = referringPageId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyLocation() {
		return companyLocation;
	}
	public void setCompanyLocation(String companyLocation) {
		this.companyLocation = companyLocation;
	}
	public String getCompanyDesc() {
		return companyDesc;
	}
	public void setCompanyDesc(String companyDesc) {
		this.companyDesc = companyDesc;
	}
	public String getCompanyWebsite() {
		return companyWebsite;
	}
	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getCurrentlyWork() {
		return currentlyWork;
	}
	public void setCurrentlyWork(String currentlyWork) {
		this.currentlyWork = currentlyWork;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getJobDesc() {
		return jobDesc;
	}
	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getPkExperienceId() {
		return pkExperienceId;
	}
	public void setPkExperienceId(int pkExperienceId) {
		this.pkExperienceId = pkExperienceId;
	}
	
	
}
