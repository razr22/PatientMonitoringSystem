package com.cps406_s4_group7_w16.BioInfo;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
	 * @throws IOException 
	 */
	public int saveLog(String filename) throws IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
		
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		
		//formatting for the header so that the data is more organized.
		writer.write("\nSave Time: " + dateformat.format(calendar.getTime()) );
		writer.write("\nTime\t\tBlood Pressure\t\t\tBody Temperature\t\tHeart Rate\t\t\tRespiratory Rate\n");
		writer.write("-----------------------------------------------------------------------------------------------------------\n");
		
		//printing each vitalSign in log to file.
		for(VitalSign vs : vitalLog){
			writer.write(vs.getTimeStamp() + "\t");
			writer.write(vs.getSystolicBloodPressure() + "/" + vs.getDiastolicBloodPressure() + " (mm Hg)\t\t\t");
			writer.write(vs.getBodyTemperature() + " (°C)\t\t\t\t");
			writer.write(vs.getHeartRate() + " (BPM)\t\t\t");
			writer.write(vs.getRespiratoryRate() + " (RPM)\t\n");
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
		VitalSign vs2 = new VitalSign(vs.getTimeStamp(), vs.getHeartRate(), vs.getSystolicBloodPressure(), vs.getDiastolicBloodPressure(), vs.getBodyTemperature(), vs.getRespiratoryRate());
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
