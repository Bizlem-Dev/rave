package org.rave.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(name="getSelectedConnection",query="select * from friends where follower_id=:userId",resultClass=Connection.class),
@NamedNativeQuery(name="showRequest",query="select * from friends where followed_id=:userId and status='HOLD'",resultClass=Connection.class),
@NamedNativeQuery(name="getAcceptedConnection",query="select * from friends where follower_id=:userId and status='ACCEPTED'",resultClass=Connection.class)})
@Table(name="friends")
public class Connection {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="friendId")
	private int friendId;
	
	@Column(name="follower_id")
	private String userId;
	
	@Column(name="followed_id")
	private String followedId;
	
	@Column(name="status")
	private String status;
	
	@ManyToOne
	@JoinColumn(name="follower_id",insertable=false,updatable=false)
	private Profile profileJoin;
	
	@ManyToOne
	@JoinColumn(name="followed_id",insertable=false,updatable=false)
	private Profile profileFollowedJoin;

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Profile getProfileFollowedJoin() {
		return profileFollowedJoin;
	}

	public void setProfileFollowedJoin(Profile profileFollowedJoin) {
		this.profileFollowedJoin = profileFollowedJoin;
	}

	public Profile getProfileJoin() {
		return profileJoin;
	}

	public void setProfileJoin(Profile profileJoin) {
		this.profileJoin = profileJoin;
	}


	public String getFollowedId() {
		return followedId;
	}

	public void setFollowedId(String followedId) {
		this.followedId = followedId;
	}

	public int getFriendId() {
		return friendId;
	}

	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	

}
