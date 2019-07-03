package org.rave.model;

import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="region")
public class RavePageRegion {
	
	@Id
	@Column(name="entity_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long entity_id;
	
	@Column(name="locked")
	private boolean locked;
	
	@Column(name="render_order")
	private int render_order;
	
	@Column(name="page_id")
	private BigInteger page_id;
	
	
	public long getEntity_id() {
		return entity_id;
	}
	
	public void setEntity_id(long entity_id) {
		this.entity_id = entity_id;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	public int getRender_order() {
		return render_order;
	}
	public void setRender_order(int render_order) {
		this.render_order = render_order;
	}
	public BigInteger getPage_id() {
		return page_id;
	}
	public void setPage_id(BigInteger page_id) {
		this.page_id = page_id;
	}
	
	


	
	
	
}
