package beans;

import java.util.Date;

public class TopicBean {
	private int topicID;
	private String topic;
	private int uID;
	private Date creationDate;
	private Date DisplayTime;
	
	public TopicBean(){}
	
	public String getTopic() {
		return topic;
	}
	public int getTopicID() {
		return topicID;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public int getuID() {
		return uID;
	}
	public void setTopicID(int value) {
		topicID = value;
	}
	public void setuID(int value) {
		uID = value;
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
}
