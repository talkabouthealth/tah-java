package com.tah.web.webapp;


import com.tah.web.webapp.LiveConversationsSingleton;

public class RunTalkmi implements Runnable {
	   
	public void run()
	{
		System.out.println("***Preparing Talkmi!!");
		System.out.println("Initiating Live Conversation Data Structure!");
		LiveConversationsSingleton lcs = LiveConversationsSingleton.getReference();
		
		System.out.println("***Talkmi Ready!!");
		
		boolean run = true;
    	while(run == true)
        {
        	try {
				Thread.sleep(600000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
	}
}
