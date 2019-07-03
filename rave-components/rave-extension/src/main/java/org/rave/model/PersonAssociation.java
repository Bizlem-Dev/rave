package org.rave.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="friends")
public class PersonAssociation {
	
	@Id
	@Column(name="friendId")
	private String friendId;
	
	@Column(name="followed_id")
	private String followed_id;
	
	@Column(name="status")
	private String status;
	
	@Column(name="follower_id")
	private String follower_id;

	public String getFriendId() {
		return friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}

	public String getFollowed_id() {
		return followed_id;
	}

	public void setFollowed_id(String followed_id) {
		this.followed_id = followed_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFollower_id() {
		return follower_id;
	}

	public void setFollower_id(String follower_id) {
		this.follower_id = follower_id;
	}
	
	

}
