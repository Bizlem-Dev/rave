package org.rave.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
@Table(name="profile")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class Profile {
	
	@Id
	@Column(name="userId")
	private String userId;
	
	@Column(name="picture")
	private String picture;
	
	@Transient
	private CommonsMultipartFile fileData;
	
	@Column(name="firstName")
	private String name;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column(name="maidenName")
	private String maidenName;
	
	
	@Column(name="headline")
	private String headline;
	
	@Column(name="status")
	private String status;
	
	@Column(name="birthDay")
	private String birthDay;
	
	@Column(name="anniversary")
	private String anniversary;
	
	@Column(name="numberType")
	private String numberType;
	
	@Column(name="imType")
	private String imType;
	
	@Column(name="number")
	private String number;
	
	@Column(name="imCode")
	private String im;
	
	@Column(name="email")
	private String email;
	
	@Column(name="industry")
	private String industry;
	
	@Column(name="address")
	private String address;

	@Column(name="city")
	private String city;
	
	@Column(name="pinCode")
	private String pinCode;
	
	@Transient
	private String referringPageId; 
	
	@Transient
	private int countRequest;
	
	@Transient
	private int countConnection;
	
	@OneToMany(mappedBy="profileJoin",fetch=FetchType.LAZY)
	private Set<Connection> connectionJoin;
	
	
	@OneToMany(mappedBy="profileFollowedJoin",fetch=FetchType.LAZY)
	private Set<Connection> connectionFollowedJoin;
	
	@OneToMany(mappedBy="profileRecommendJoin",fetch=FetchType.LAZY)
	private Set<Recommendation> recommendForJoin;
	
	@OneToMany(mappedBy="profileRecommendByJoin",fetch=FetchType.LAZY)
	private Set<Recommendation> recommendByJoin;
	
	
	
	public Set<Recommendation> getRecommendByJoin() {
		return recommendByJoin;
	}
	public void setRecommendByJoin(Set<Recommendation> recommendByJoin) {
		this.recommendByJoin = recommendByJoin;
	}
	public Set<Recommendation> getRecommendForJoin() {
		return recommendForJoin;
	}
	public void setRecommendForJoin(Set<Recommendation> recommendForJoin) {
		this.recommendForJoin = recommendForJoin;
	}
	public int getCountRequest() {
		return countRequest;
	}
	public void setCountRequest(int countRequest) {
		this.countRequest = countRequest;
	}
	public int getCountConnection() {
		return countConnection;
	}
	public void setCountConnection(int countConnection) {
		this.countConnection = countConnection;
	}
	public Set<Connection> getConnectionFollowedJoin() {
		return connectionFollowedJoin;
	}
	public void setConnectionFollowedJoin(Set<Connection> connectionFollowedJoin) {
		this.connectionFollowedJoin = connectionFollowedJoin;
	}
	public Set<Connection> getConnectionJoin() {
		return connectionJoin;
	}
	public void setConnectionJoin(Set<Connection> connectionJoin) {
		this.connectionJoin = connectionJoin;
	}
	public CommonsMultipartFile getFileData() {
		return fileData;
	}
	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMaidenName() {
		return maidenName;
	}
	public void setMaidenName(String maidenName) {
		this.maidenName = maidenName;
	}
	public String getNumberType() {
		return numberType;
	}
	public void setNumberType(String numberType) {
		this.numberType = numberType;
	}
	public String getImType() {
		return imType;
	}
	public void setImType(String imType) {
		this.imType = imType;
	}
	public String getReferringPageId() {
		return referringPageId;
	}
	public void setReferringPageId(String referringPageId) {
		this.referringPageId = referringPageId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public String getAnniversary() {
		return anniversary;
	}
	public void setAnniversary(String anniversary) {
		this.anniversary = anniversary;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getIm() {
		return im;
	}
	public void setIm(String im) {
		this.im = im;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	

	
}
