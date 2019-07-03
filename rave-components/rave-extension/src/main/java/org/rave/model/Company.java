package org.rave.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="company")
public class Company {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="companyId")
	int companyId;
	
	@Column(name="name")
	String name;
	
	@Column(name="website")
	String website;
	
	@Column(name="logoPic")
	String logoPic;
	
	@Column(name="purchase")
	String purchase;
	
	@Column(name="sell")
	String sell;
	
	@Column(name="rawMaterial")
	String rawMaterial;
	
	@Column(name="equipmentUsed")
	String equipmentUsed;
	
	@Column(name="inquiries")
	String inquiries;
	
	@Column(name="career")
	String career;
	
	@Column(name="import")
	String majorImport;
	
	@Column(name="export")
	String export;
	
	@Column(name="description")
	String description;
	
	@Column(name="industries")
	String industries;
	
	@Column(name="employeesNum")
	String employeesNum;
	
	@Column(name="type")
	String type;
	
	@Column(name="turnOver")
	String turnOver;
	
	@Column(name="founded")
	String founded;
	
	@Column(name="headQuater")
	String headQuater;
	
	@Column(name="address")
	String address;
	
	@Column(name="city")
	String city;
	
	@Column(name="postalCode")
	String postalCode;
	
	@Column(name="workNumber")
	String workNumber;
	
	@Column(name="mobileNumber")
	String mobileNumber;
	
	@Column(name="contactEmailId")
	String contactEmailId;
	
	@Column(name="creatorEmailId")
	String creatorEmailId;
	
	@Column(name="title")
	String title;
	
	@Column(name="joiningDate")
	String joiningDate;

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getLogoPic() {
		return logoPic;
	}

	public void setLogoPic(String logoPic) {
		this.logoPic = logoPic;
	}

	public String getPurchase() {
		return purchase;
	}

	public void setPurchase(String purchase) {
		this.purchase = purchase;
	}

	public String getSell() {
		return sell;
	}

	public void setSell(String sell) {
		this.sell = sell;
	}

	public String getRawMaterial() {
		return rawMaterial;
	}

	public void setRawMaterial(String rawMaterial) {
		this.rawMaterial = rawMaterial;
	}

	public String getEquipmentUsed() {
		return equipmentUsed;
	}

	public void setEquipmentUsed(String equipmentUsed) {
		this.equipmentUsed = equipmentUsed;
	}

	public String getInquiries() {
		return inquiries;
	}

	public void setInquiries(String inquiries) {
		this.inquiries = inquiries;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getMajorImport() {
		return majorImport;
	}

	public void setMajorImport(String majorImport) {
		this.majorImport = majorImport;
	}

	public String getExport() {
		return export;
	}

	public void setExport(String export) {
		this.export = export;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIndustries() {
		return industries;
	}

	public void setIndustries(String industries) {
		this.industries = industries;
	}

	public String getEmployeesNum() {
		return employeesNum;
	}

	public void setEmployeesNum(String employeesNum) {
		this.employeesNum = employeesNum;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTurnOver() {
		return turnOver;
	}

	public void setTurnOver(String turnOver) {
		this.turnOver = turnOver;
	}

	public String getFounded() {
		return founded;
	}

	public void setFounded(String founded) {
		this.founded = founded;
	}

	public String getHeadQuater() {
		return headQuater;
	}

	public void setHeadQuater(String headQuater) {
		this.headQuater = headQuater;
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

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getWorkNumber() {
		return workNumber;
	}

	public void setWorkNumber(String workNumber) {
		this.workNumber = workNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getContactEmailId() {
		return contactEmailId;
	}

	public void setContactEmailId(String contactEmailId) {
		this.contactEmailId = contactEmailId;
	}

	public String getCreatorEmailId() {
		return creatorEmailId;
	}

	public void setCreatorEmailId(String creatorEmailId) {
		this.creatorEmailId = creatorEmailId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}
	
	

}
