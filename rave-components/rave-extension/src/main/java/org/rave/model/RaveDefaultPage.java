package org.rave.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="default_page_create")
public class RaveDefaultPage {

	@Id
	@Column(name="entity_id")
	private int entity_id;
	
	@Column(name="page_no")
	private int page_no;
	
	@Column(name="page_layout_id")
	private int page_layout_id;
	
	@Column(name="render_sequence")
	private int  render_sequence;
	
	@Column(name="widget_render")
	private int  widget_render;
	
	@Column(name="widget_id")
	private int  widget_id;

	@Column(name="page_name")
	private String page_name;
	
	
	public String getPage_name() {
		return page_name;
	}
	public void setPage_name(String page_name) {
		this.page_name = page_name;
	}
	public int getEntity_id() {
		return entity_id;
	}
	public void setEntity_id(int entity_id) {
		this.entity_id = entity_id;
	}
	public int getPage_no() {
		return page_no;
	}
	public void setPage_no(int page_no) {
		this.page_no = page_no;
	}
	public int getPage_layout_id() {
		return page_layout_id;
	}
	public void setPage_layout_id(int page_layout_id) {
		this.page_layout_id = page_layout_id;
	}
	public int getRender_sequence() {
		return render_sequence;
	}
	public void setRender_sequence(int render_sequence) {
		this.render_sequence = render_sequence;
	}
	public int getWidget_render() {
		return widget_render;
	}
	public void setWidget_render(int widget_render) {
		this.widget_render = widget_render;
	}
	public int getWidget_id() {
		return widget_id;
	}
	public void setWidget_id(int widget_id) {
		this.widget_id = widget_id;
	}
 	
}
