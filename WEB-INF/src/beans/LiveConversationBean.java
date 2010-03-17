package beans;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LiveConversationBean {
	private TopicBean topic;
	private Map<Integer, TalkerBean> mTalkers = new HashMap<Integer, TalkerBean>();
	private Date StartTime = new Date();
		
	public LiveConversationBean(){}

	public void setTalkers(Map<Integer, TalkerBean> mTalkers) {
		this.mTalkers = mTalkers;
	}
	public Map<Integer, TalkerBean> getTalkers() {
		return mTalkers;
	}
	public void removeTalker(Integer tID)
	{
		mTalkers.remove(tID);
	}  
	
	public void addTalker(Integer tID, TalkerBean c)
	{
		mTalkers.put(tID, c);
	}	
	
	public TalkerBean getConversationMatch(Integer tID)
	{
		return mTalkers.get(tID);
	}

	public void setTopic(TopicBean topic) {
		this.topic = topic;
	}

	public TopicBean getTopic() {
		return topic;
	}

	public void setStartTime(Date startTime) {
		StartTime = startTime;
	}

	public Date getStartTime() {
		return StartTime;
	}
	
}	