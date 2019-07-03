package org.rave.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="importedcontact")
public class ImportedContacts {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="userId")
	private String userId;
	
	@Column(name="contactEmail")
	private String contactEmail;
	
	@Column(name="contactFirstname")
	private String contactFirstname;
	
	@Column(name="contactLastName")
	private String contactLastName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactFirstname() {
		return contactFirstname;
	}

	public void setContactFirstname(String contactFirstname) {
		this.contactFirstname = contactFirstname;
	}

	public String getContactLastName() {
		return contactLastName;
	}

	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}
	
	

}
