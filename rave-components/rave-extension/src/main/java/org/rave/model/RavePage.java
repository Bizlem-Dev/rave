package org.rave.model;

import java.math.BigInteger;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="page")
public class RavePage {

	@Id
	@Column(name="entity_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long entity_id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="page_type")
	private String page_type;
	
	@Column(name="owner_id")
	private BigInteger owner_id;
	
	@Column(name="page_layout_id")
	private BigInteger page_layout_id;
	
	@Column(name="parent_page_id")
	private BigInteger parent_page_id;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "page")
	private Set<RavePageUser> pageUser;

	
	
	

	public long getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(long entity_id) {
		this.entity_id = entity_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPage_type() {
		return page_type;
	}

	public void setPage_type(String page_type) {
		this.page_type = page_type;
	}

	public BigInteger getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(BigInteger owner_id) {
		this.owner_id = owner_id;
	}

	public BigInteger getPage_layout_id() {
		return page_layout_id;
	}

	public void setPage_layout_id(BigInteger page_layout_id) {
		this.page_layout_id = page_layout_id;
	}

	public BigInteger getParent_page_id() {
		return parent_page_id;
	}

	public void setParent_page_id(BigInteger parent_page_id) {
		this.parent_page_id = parent_page_id;		
	}
	
	
	
}
