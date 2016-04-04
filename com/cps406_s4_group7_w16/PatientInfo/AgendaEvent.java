package com.cps406_s4_group7_w16.PatientInfo;

import javafx.beans.property.SimpleStringProperty;

/**
 * Class that defines an AgendaEvent data type.
 * An AgendaEvent is a Patient's event such as an MRI or an XRay to be done.
 * @author Paul Martins
 *
 */
public class AgendaEvent {

	private final SimpleStringProperty time;
	private final SimpleStringProperty event;
	
	/**
	 * Constructor for an AgendaEvent.
	 * @param time Time of event.
	 * @param event Event name
	 */
	public AgendaEvent(String time, String event){
		this.time = new SimpleStringProperty(time);
		this.event = new SimpleStringProperty(event);
	}

	/**
	 * Getter for an AgendaEvent's time
	 * @return time to be returned.
	 */
	public String getTime(){
		return time.get();
	}
	
	/**
	 * Setter for an AgendaEvent's time
	 * @param t Time to be set.
	 */
	public void setTime(String t){
		time.set(t);
	}
	
	/**
	 * Getter for an AgendaEvent's event name
	 * @return Name of event
	 */
	public String getEvent(){
		return event.get();
	}
	
	/**
	 * Setter for an AgendaEvent's event name
	 * @param e Name to be set.
	 */
	public void setEvent(String e){
		event.set(e);
	}
	
}
