package com.tah.beans;

import java.util.Date;

public class TopicBean {
	private String id;
	private String topic;
	private Date creationDate;
	private Date DisplayTime;
	private String uid;
	
	public TopicBean(){}
	
	public String getTopic() {
		return topic;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setTopic(String value) {
		topic = value;
	}
	public void setCreationDate(Date value) {
		creationDate = value;
	}
	public void setDisplayTime(Date displayTime) {
		DisplayTime = displayTime;
	}
	public Date getDisplayTime() {
		return DisplayTime;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
