package org.rave.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="summary")
public class Summary {
	
	@Column(name="goals")
	private String goals;
	
	@Column(name="specialities")
	private String specialities;
	
	@Column(name="skills")
	private String skills;
	
	@Column(name="level")
	private String level;
	
	@Id
	@Column(name="userId")
	private String userName;
	
	@Transient
	private String referringPageId;
	
	
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getReferringPageId() {
		return referringPageId;
	}
	public void setReferringPageId(String referringPageId) {
		
		this.referringPageId = referringPageId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGoals() {
		return goals;
	}
	public void setGoals(String goals) {
		this.goals = goals;
	}
	public String getSpecialities() {
		return specialities;
	}
	public void setSpecialities(String specialities) {
		this.specialities = specialities;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	
	
	
}
