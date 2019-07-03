package org.rave.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="additionalInfo")
public class AdditionalInfo {
	
	@Column(name="websites")
	private String websites;
	
	@Column(name="webType")
	private String webType;
	
	@Column(name="groupsAssociations")
	private String groupsAssociations;
	
	@Column(name="interests")
	private String interests;
	
	@Column(name="honorsAwards")
	private String honorsAwards;
	
	@Id
	@Column(name="userId")
	private String userId;
	
	@Transient
	private String referringPageId;
	
	
	public String getReferringPageId() {
		return referringPageId;
	}
	public void setReferringPageId(String referringPageId) {
		this.referringPageId = referringPageId;
	}
	
	public String getWebsites() {
		return websites;
	}
	public void setWebsites(String websites) {
		this.websites = websites;
	}
	public String getGroupsAssociations() {
		return groupsAssociations;
	}
	public void setGroupsAssociations(String groupsAssociations) {
		this.groupsAssociations = groupsAssociations;
	}
	public String getInterests() {
		return interests;
	}
	public void setInterests(String interests) {
		this.interests = interests;
	}
	public String getHonorsAwards() {
		return honorsAwards;
	}
	public void setHonorsAwards(String honorsAwards) {
		this.honorsAwards = honorsAwards;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getWebType() {
		return webType;
	}
	public void setWebType(String webType) {
		this.webType = webType;
	}
	
	
	
}
