package com.cps406_s4_group7_w16.BioInfo;

import java.util.*;

public class Log {
	
	//class members.
	private int saveInterval;
	private ArrayList<VitalSign> vitalLog = new ArrayList<VitalSign>();
	
	/**
	 * Method that clears the log. Used mostly after saving the log to avoid wasted memory.
	 */
	public void clearLog(){
		vitalLog.clear();
	
	}
	
	/**
	 * Method that saves the contents of the log to a file.
	 * 
	 * METHOD IS UNFINISHED.
	 * 
	 * @return	Integer representing whether file i/o was completed successfully. Method returns 1 upon successful completion, 0 if not successful.
	 */
	public int saveLog(){
		return 1;
	}
	
	/**
	 * Method that adds a Vital sign to the end of the log.
	 * @param vs Vital sign to be added to the log.
	 */
	public void addLogEntry(VitalSign vs){
		vitalLog.add(vs); 
	}

	/**
	 * Getter method for the interval in which the log file is to be saved.
	 * @return	Integer representing quanta of time (in seconds) to pass before the vital sign log is saved externally.
	 */
	public int getSaveInterval() {
		return saveInterval;
	}

	/**
	 * Setter method for the interval in which the log file is to be saved.
	 * @param saveInterval	Integer representing the time (in seconds) that should pass before the vital sign log is saved.
	 */
	public void setSaveInterval(int saveInterval) {
		this.saveInterval = saveInterval;
	}
	
}
