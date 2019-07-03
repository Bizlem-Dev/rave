package org.rave.model;


import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="person")
public class RavePerson {
	
	@Id
	@Column(name="entity_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private BigInteger entity_id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="email")
	private String email;
	
	@Column(name="status")
	private String status;
	
	@Column(name="given_name")
	private String given_name;
	
	@Column(name="password")
	private String password;
	
	@Column(name="locked")
	private boolean locked;
	
	@Column(name="plaintext_password")
	private String plaintext_password;
	
	@Column(name="encrypt_password")
	private String encrypt_password;

	@Column(name="display_name")
	private String display_name;
	
	@Column(name="family_name")
	private String family_name;
	
	@Column(name="DTYPE")
	private String DTYPE;
	
	@Column(name="enabled")
	private boolean enabled;
	
	@Column(name="expired")
	private boolean expired;
	
	@Column(name="default_page_layout_id")
	private BigInteger default_page_layout_id;
	

	
	
	
	
	public String getEncrypt_password() {
		return encrypt_password;
	}

	public void setEncrypt_password(String encrypt_password) {
		this.encrypt_password = encrypt_password;
	}
	
	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPlaintext_password() {
		return plaintext_password;
	}

	public void setPlaintext_password(String plaintext_password) {
		this.plaintext_password = plaintext_password;
	}

	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

	public String getFamily_name() {
		return family_name;
	}

	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}

	public String getDTYPE() {
		return DTYPE;
	}

	public void setDTYPE(String dTYPE) {
		DTYPE = dTYPE;
	}


	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public BigInteger getDefault_page_layout_id() {
		return default_page_layout_id;
	}

	public void setDefault_page_layout_id(BigInteger default_page_layout_id) {
		this.default_page_layout_id = default_page_layout_id;
	}

	public BigInteger getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(BigInteger entity_id) {
		this.entity_id = entity_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGiven_name() {
		return given_name;
	}

	public void setGiven_name(String given_name) {
		this.given_name = given_name;
	}
	
	
	
}
