package org.rave.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="serviceprovider")
public class ServiceProvider {


	@Id
	@Column(name="id")
	private String id;
	
	
	@Column(name="userId")
	private String userId;
	
	@Column(name="providerId")
	private String providerId;

	@Column(name="providerUserName")
	private String providerUserName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getProviderUserName() {
		return providerUserName;
	}

	public void setProviderUserName(String providerUserName) {
		this.providerUserName = providerUserName;
	}
	
	
	
}
