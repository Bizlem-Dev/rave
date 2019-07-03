package org.rave.model;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="page_user")
public class RavePageUser {

	@Id
	@Column(name="entity_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long entity_id;
	
	@Column(name="editor")
	private boolean editor;
	
	@Column(name="page_status")
	private String page_status;
	
	@Column(name="render_sequence")
	private BigInteger render_sequence;
	
	@Column(name="user_id")
	private BigInteger user_id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "page_id")
	private RavePage page;
	
	
	public RavePage getPage() {
		return page;
	}

	public void setPage(RavePage page) {
		this.page = page;
	}

	public long getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(long entity_id) {
		this.entity_id = entity_id;
	}

	public boolean isEditor() {
		return editor;
	}

	public void setEditor(boolean editor) {
		this.editor = editor;
	}

	public String getPage_status() {
		return page_status;
	}

	public void setPage_status(String page_status) {
		this.page_status = page_status;
	}

	public BigInteger getRender_sequence() {
		return render_sequence;
	}

	public void setRender_sequence(BigInteger render_sequence) {
		this.render_sequence = render_sequence;
	}

	public BigInteger getUser_id() {
		return user_id;
	}

	public void setUser_id(BigInteger user_id) {
		this.user_id = user_id;
	}
	
	
}
