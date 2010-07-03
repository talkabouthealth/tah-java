package com.tah.web.webapp;

import java.util.Calendar;
import java.util.Date;

import com.tah.dao.LoginHistoryDAO;

public class InsertLoginRecordThread implements Runnable {
	
	private String talkerId;
	
	public InsertLoginRecordThread(String talkerId) {
		this.talkerId = talkerId;
	}
	public void run(){
		Date now = Calendar.getInstance().getTime(); 
		LoginHistoryDAO.save(talkerId, now);
	}
}
