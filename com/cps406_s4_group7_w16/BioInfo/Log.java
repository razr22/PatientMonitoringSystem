package com.cps406_s4_group7_w16.BioInfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.util.*;

/**
 * Class that defines a Log data type. Log holds a VitalSign for every quanta of time.
 * this list is eventually saved to a file.
 * @author Paul Martins
 *
 */
public class Log {

	// class members.
	private ArrayList<VitalSign> vitalLog = new ArrayList<VitalSign>();
	
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
		
		//formatting for the header so that the data is more organized.
		writer.write("\nVITAL SIGN LOG");
		writer.write("\nTime\t\tBlood Pressure\t\t\tBody Temperature\t\tHeart Rate\t\t\tRespiratory Rate\n");
		writer.write("-----------------------------------------------------------------------------------------------------------\n");
		
		//printing each vitalSign in log to file.
		for(VitalSign vs : vitalLog){
			writer.write(vs.getTimeStamp() + "\t");
			writer.write(vs.getSystolicBloodPressure() + "/" + vs.getDiastolicBloodPressure() + " (mm Hg)\t\t\t");
			writer.write(vs.getBodyTemperature() + " (�C)\t\t\t\t");
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
}
