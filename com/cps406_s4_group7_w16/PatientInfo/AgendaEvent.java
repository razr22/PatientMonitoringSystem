package com.cps406_s4_group7_w16.PatientInfo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class AgendaEvent {

	private final SimpleStringProperty time;
	private final SimpleStringProperty event;
	
	public AgendaEvent(String time, String event){
		this.time = new SimpleStringProperty(time);
		this.event = new SimpleStringProperty(event);
	}

	public String getTime(){
		return time.get();
	}
	
	public void setTime(String t){
		time.set(t);
	}
	
	public String getEvent(){
		return event.get();
	}
	
	public void setEvent(String e){
		event.set(e);
	}
	
}
