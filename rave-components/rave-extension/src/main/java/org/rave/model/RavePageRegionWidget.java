package org.rave.model;

import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="region_widget")
public class RavePageRegionWidget {
	@Id
	@Column(name="entity_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long entity_id;
	
	@Column(name="collapsed")
	private boolean collapsed;
	
	@Column(name="hide_chrome")
	private boolean hide_chrome;
	
	@Column(name="locked")
	private boolean locked;
	
	@Column(name="render_order")
	private int render_order;
	
	@Column(name="render_position")
	private String render_position;
	
	@Column(name="region_id")
	private BigInteger region_id;
	
	@Column(name="widget_id")
	private BigInteger widget_id;
	
	public long getEntity_id() {
		return entity_id;
	}
	public void setEntity_id(long entity_id) {
		this.entity_id = entity_id;
	}
	public boolean isCollapsed() {
		return collapsed;
	}
	public void setCollapsed(boolean collapsed) {
		this.collapsed = collapsed;
	}
	public boolean isHide_chrome() {
		return hide_chrome;
	}
	public void setHide_chrome(boolean hide_chrome) {
		this.hide_chrome = hide_chrome;
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
	public String getRender_position() {
		return render_position;
	}
	public void setRender_position(String render_position) {
		this.render_position = render_position;
	}
	public BigInteger getRegion_id() {
		return region_id;
	}
	public void setRegion_id(BigInteger region_id) {
		this.region_id = region_id;
	}
	public BigInteger getWidget_id() {
		return widget_id;
	}
	public void setWidget_id(BigInteger widget_id) {
		this.widget_id = widget_id;
	}
		
}
