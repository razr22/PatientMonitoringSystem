package com.cps406_s4_group7_w16.BioInfo;

/**
 * Class that defines a VitalSign data type. 
 * A VitalSign object holds all of the patient's data retrieved from the sensors for a single quanta of time.
 * @author Paul Martins
 *
 */
public class VitalSign {

	// Vital Signs recorded.
	private String timeStamp;
	private double heartRate;
	private int systolicBloodPressure;
	private int diastolicBloodPressure;
	private double bodyTemperature;
	private	double respiratoryRate;
	
	/**
	 * Default Constructor for the VitalSign Class. Initializes all members to default values.
	 */
	public VitalSign() {
		this.timeStamp = "";
		this.heartRate = 0;
		this.systolicBloodPressure = 0;
		this.diastolicBloodPressure = 0;
		this.bodyTemperature = 0;
		this.respiratoryRate = 0;
	}

	/**
	 * Constructor for the VitalSign Class. 
	 * @param timeStamp timeStamp retrieved from clock.
	 * @param heartRate	heart rate reading retrieved from generator
	 * @param systolicBloodPressure Systolic blood pressure reading retrieved from generator
	 * @param diastolicBloodPressure Diastolic blood pressure reading retrieved from generator
	 * @param bodyTemperature	Temperature reading retrieved from generator
	 * @param respiratoryRate respiratory rate reading retrieved from generator
	 */
	public VitalSign(String timeStamp, double heartRate, int systolicBloodPressure, int diastolicBloodPressure, double bodyTemperature, double respiratoryRate) {
		this.timeStamp = timeStamp;
		this.heartRate = heartRate;
		this.systolicBloodPressure = systolicBloodPressure;
		this.diastolicBloodPressure = diastolicBloodPressure;
		this.bodyTemperature = bodyTemperature;
		this.respiratoryRate = respiratoryRate;
	}

	/**
	 * Getter for a VitalSign's heartRate 
	 * @return VitalSign's heartRate value.
	 */
	public double getHeartRate() {
		return heartRate;
	}

	/**
	 * Setter for a VitalSign's heartRate.
	 * @param rate heartRate to be added.
	 */
	public void setHeartRate(double rate) {
		this.heartRate = rate;
	}

	/**
	 * Getter for a VitalSign's bodyTemperature
	 * @return temperature to be returned.
	 */
	public double getBodyTemperature() {
		return bodyTemperature;
	}

	/**
	 * Setter for a VitalSign's bodyTemperature
	 * @param bodyTemperature to be added.
	 */
	public void setBodyTemperature(double bodyTemperature) {
		this.bodyTemperature = bodyTemperature;
	}

	/**
	 * Getter for a VitalSign's systolicBloodPressure
	 * @return systolic blood pressure to be returned.
	 */
	public int getSystolicBloodPressure() {
		return systolicBloodPressure;
	}

	/**
	 * Setter for a VitalSign's systolicBloodPressure
	 * @param systolicBloodPressure
	 */
	public void setSystolicBloodPressure(int systolicBloodPressure) {
		this.systolicBloodPressure = systolicBloodPressure;
	}

	/**
	 * Getter for a VitalSign's diastolicBloodPressure
	 * @return diastolic blood pressure to be returned.
	 */
	public int getDiastolicBloodPressure() {
		return diastolicBloodPressure;
	}

	/**
	 * Setter for a VitalSign's diastolicBloodPressure
	 * @param diastolicBloodPressure diastolic blood pressure to be added.
	 */
	public void setDiastolicBloodPressure(int diastolicBloodPressure) {
		this.diastolicBloodPressure = diastolicBloodPressure;
	}

	/**
	 * Getter for a VitalSign's respiratoryRate
	 * @return respiratoryRate to be returned.
	 */
	public double getRespiratoryRate() {
		return respiratoryRate;
	}

	/**
	 * Setter for a VitalSign's respiratoryRate
	 * @param respiratoryRate respiratory rate to be added.
	 */
	public void setRespiratoryRate(double respiratoryRate) {
		this.respiratoryRate = respiratoryRate;
	}
	
	/**
	 * Getter for a VitalSign's timeStamp
	 * @return timestamp to be returned.
	 */
	public String getTimeStamp() {
		return timeStamp;
	}

	/**
	 * Setter for a VitalSign's timeStamp
	 * @param timeStamp time stamp to be added.
	 */
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	/**
	 * toString representation of VitalSign for debugging purposes.
	 * @return String representation of VitalSign.
	 */
	public String toString(){
		String returnString = "[VitalSign] -> Blood Pressure = " + this.getSystolicBloodPressure() + "/" + this.getDiastolicBloodPressure() + ", Body Temperature = " + this.getBodyTemperature() + ", Heart Rate = " + this.getHeartRate() + ", Respiratory Rate = " + this.getRespiratoryRate();
		return returnString;
	}


	
}
