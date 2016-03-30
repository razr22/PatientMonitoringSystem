package com.cps406_s4_group7_w16.BioInfo;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class Log {

	// class members.
	private int saveInterval;
	private ArrayList<VitalSign> vitalLog = new ArrayList<VitalSign>();

	public Log(){
		saveInterval = 20;
	}
	
	/**
	 * Method that clears the log. Used mostly after saving the log to avoid
	 * wasted memory.
	 */
	public void clearLog() {
		vitalLog.clear();

	}

	/**
	 * Method that saves the contents of the log to a file.
	 *
	 * @return Integer representing whether file i/o was completed successfully.
	 *         Method returns 1 upon successful completion, 0 if not successful.
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	public int saveLog(String filename) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(filename, "UTF-8");
		
		//formatting for the header so that the data is more organized.
		writer.println("Blood Pressure\tBody Temperature\tHeart Rate\tRespiratory Rate");
		writer.println("-------------------------------------------------------------");
		writer.println();
		
		//printing each vitalSign in log to file.
		for(VitalSign vs : vitalLog){
			writer.print(vs.getBloodPressure() + "\t\t\t");
			writer.print(vs.getBodyTemperature() + "\t\t\t\t");
			writer.print(vs.getHeartRate() + "\t\t");
			writer.print(vs.getRespiratoryRate() + "\t");
			writer.println();
		}
		
		//closing the writer.
		writer.close();
		return 0;
	}

	/**
	 * Method that adds a Vital sign to the end of the log.
	 * 
	 * @param vs
	 *            Vital sign to be added to the log.
	 */
	public void addLogEntry(VitalSign vs) {
		
		//creating a new vitalSign object before adding to log to ensure that the same object is not added to the 
		//more than once.
		VitalSign vs2 = new VitalSign(vs.getHeartRate(), vs.getBloodPressure(), vs.getBodyTemperature(), vs.getRespiratoryRate());
		vitalLog.add(vs2);
	}

	/**
	 * Getter method for the interval in which the log file is to be saved.
	 * 
	 * @return Integer representing quanta of time (in seconds) to pass before
	 *         the vital sign log is saved externally.
	 */
	public int getSaveInterval() {
		return saveInterval;
	}

	/**
	 * Setter method for the interval in which the log file is to be saved.
	 * 
	 * @param saveInterval
	 *            Integer representing the time (in seconds) that should pass
	 *            before the vital sign log is saved.
	 */
	public void setSaveInterval(int saveInterval) {
		this.saveInterval = saveInterval;
	}

}
