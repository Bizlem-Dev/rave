package org.rave.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="recommendation")
public class Recommendation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pkRecommendId")
	private int recommendId;
	
	@Column(name="recommendFor")
	private String recommendFor;
	
	@Column(name="recommendBy")
	private String recommendBy;
	
	@Column(name="description")
	private String desc;
	
	@Column(name="recommendType")
	private String recommendType;
	
	@Column(name="recommendByTitle")
	private String recommendByTitle;

	@Column(name="status")
	private String status;
	
	@Column(name="recommendForTitle")
	private String recommendForTitle;
	
	@ManyToOne
	@JoinColumn(name="recommendFor",insertable=false,updatable=false)
	private Profile profileRecommendJoin;
	
	@ManyToOne
	@JoinColumn(name="recommendBy",insertable=false,updatable=false)
	private Profile profileRecommendByJoin;
	
	@Column(name="fk_key_by_exp")
	private int fk_key_by_exp;
	
	@Column(name="fk_key_for_exp")
	private int fk_key_for_exp;
	
	@Column(name="hide")
	private String hide;
	
	
	
	public Profile getProfileRecommendByJoin() {
		return profileRecommendByJoin;
	}

	public void setProfileRecommendByJoin(Profile profileRecommendByJoin) {
		this.profileRecommendByJoin = profileRecommendByJoin;
	}

	public int getFk_key_by_exp() {
		return fk_key_by_exp;
	}

	public void setFk_key_by_exp(int fk_key_by_exp) {
		this.fk_key_by_exp = fk_key_by_exp;
	}

	public int getFk_key_for_exp() {
		return fk_key_for_exp;
	}

	public void setFk_key_for_exp(int fk_key_for_exp) {
		this.fk_key_for_exp = fk_key_for_exp;
	}

	public String getHide() {
		return hide;
	}

	public void setHide(String hide) {
		this.hide = hide;
	}

	public Profile getProfileRecommendJoin() {
		return profileRecommendJoin;
	}

	public void setProfileRecommendJoin(Profile profileRecommendJoin) {
		
		this.profileRecommendJoin = profileRecommendJoin;
	}

	public String getRecommendForTitle() {
		return recommendForTitle;
	}

	public void setRecommendForTitle(String recommendForTitle) {
		this.recommendForTitle = recommendForTitle;
	}

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getRecommendId() {
		return recommendId;
	}

	public void setRecommendId(int recommendId) {
		this.recommendId = recommendId;
	}

	public String getRecommendFor() {
		return recommendFor;
	}

	public void setRecommendFor(String recommendFor) {
		this.recommendFor = recommendFor;
	}

	public String getRecommendBy() {
		return recommendBy;
	}

	public void setRecommendBy(String recommendBy) {
		this.recommendBy = recommendBy;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getRecommendType() {
		return recommendType;
	}

	public void setRecommendType(String recommendType) {
		this.recommendType = recommendType;
	}

	public String getRecommendByTitle() {
		return recommendByTitle;
	}

	public void setRecommendByTitle(String recommendByTitle) {
		this.recommendByTitle = recommendByTitle;
	}
	
	

}
