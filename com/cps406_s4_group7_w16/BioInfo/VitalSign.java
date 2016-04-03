package com.cps406_s4_group7_w16.BioInfo;

public class VitalSign {

	// Vital Signs
	private String timeStamp;
	private double heartRate;
	private int systolicBloodPressure;
	private int diastolicBloodPressure;
	private double bodyTemperature;
	private	double respiratoryRate;
	

	public VitalSign() {
		this.timeStamp = "";
		this.heartRate = 0;
		this.systolicBloodPressure = 0;
		this.diastolicBloodPressure = 0;
		this.bodyTemperature = 0;
		this.respiratoryRate = 0;
	}

	public VitalSign(String timeStamp, double heartRate, int systolicBloodPressure, int diastolicBloodPressure, double bodyTemperature, double respiratoryRate) {
		this.timeStamp = timeStamp;
		this.heartRate = heartRate;
		this.systolicBloodPressure = systolicBloodPressure;
		this.diastolicBloodPressure = diastolicBloodPressure;
		this.bodyTemperature = bodyTemperature;
		this.respiratoryRate = respiratoryRate;
	}

	public double getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(double d) {
		this.heartRate = d;
	}

	public double getBodyTemperature() {
		return bodyTemperature;
	}

	public void setBodyTemperature(double bodyTemperature) {
		this.bodyTemperature = bodyTemperature;
	}

	public int getSystolicBloodPressure() {
		return systolicBloodPressure;
	}

	public void setSystolicBloodPressure(int systolicBloodPressure) {
		this.systolicBloodPressure = systolicBloodPressure;
	}

	public int getDiastolicBloodPressure() {
		return diastolicBloodPressure;
	}

	public void setDiastolicBloodPressure(int diastolicBloodPressure) {
		this.diastolicBloodPressure = diastolicBloodPressure;
	}

	public double getRespiratoryRate() {
		return respiratoryRate;
	}

	public void setRespiratoryRate(double respiratoryRate) {
		this.respiratoryRate = respiratoryRate;
	}
	
	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public String toString(){
		String returnString = "[VitalSign] -> Blood Pressure = " + this.getSystolicBloodPressure() + "/" + this.getDiastolicBloodPressure() + ", Body Temperature = " + this.getBodyTemperature() + ", Heart Rate = " + this.getHeartRate() + ", Respiratory Rate = " + this.getRespiratoryRate();
		return returnString;
	}


	
}
