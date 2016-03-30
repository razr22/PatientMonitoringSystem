package com.cps406_s4_group7_w16.BioInfo;

public class VitalSign {

	// Vital Signs
	private double heartRate;
	private double bloodPressure;
	private double bodyTemperature;
	private	double respiratoryRate;

	public VitalSign() {
		this.heartRate = 0;
		this.bloodPressure = 0;
		this.bodyTemperature = 0;
		this.respiratoryRate = 0;
	}

	public VitalSign(double heartRate, double bloodPressure, double bodyTemperature, double respiratoryRate) {
		this.heartRate = heartRate;
		this.bloodPressure = bloodPressure;
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

	public double getBloodPressure() {
		return bloodPressure;
	}

	public void setBloodPressure(double d) {
		this.bloodPressure = d;
	}

	public double getRespiratoryRate() {
		return respiratoryRate;
	}

	public void setRespiratoryRate(double respiratoryRate) {
		this.respiratoryRate = respiratoryRate;
	}

	public String toString(){
		String returnString = "[VitalSign] -> Blood Pressure = " + this.getBloodPressure() + ", Body Temperature = " + this.getBodyTemperature() + ", Heart Rate = " + this.getHeartRate() + ", Respiratory Rate = " + this.getRespiratoryRate();
		return returnString;
	}
}
