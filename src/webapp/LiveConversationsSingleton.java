package webapp;

import java.util.HashMap;
import java.util.Map;

import beans.LiveConversationBean;


public class LiveConversationsSingleton {
	private static LiveConversationsSingleton liveConversations = new LiveConversationsSingleton();
	private Map<Integer, LiveConversationBean> mLiveConversations = new HashMap<Integer, LiveConversationBean>();
	
	private LiveConversationsSingleton() {}
	  
	public static LiveConversationsSingleton getReference() { 
		return liveConversations; 
	}
	  
	public void removeConversation(Integer tID)
	{
		mLiveConversations.remove(tID);
	}  
	public void addConversation(Integer tID, LiveConversationBean c)
	{
		mLiveConversations.put(tID, c);
	}	
	public LiveConversationBean getConversationMatch(Integer tID)
	{
		return mLiveConversations.get(tID);
	}
	public Map<Integer, LiveConversationBean> getLiveConversationMap()
	{
		return mLiveConversations;
	}
}
