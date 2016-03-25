package com.cps406_s4_group7_w16.BioInfo;

public class VitalSign {

	// Vital Signs
	private int heartRate;
	private int bloodPressure;
	private double bodyTemperature;
	private int respiratoryRate;

	public VitalSign() {
		this.heartRate = 0;
		this.bloodPressure = 0;
		this.bodyTemperature = 0;
		this.respiratoryRate = 0;
	}

	public VitalSign(int heartRate, int bloodPressure, double bodyTemperature, int respiratoryRate) {
		this.heartRate = heartRate;
		this.bloodPressure = bloodPressure;
		this.bodyTemperature = bodyTemperature;
		this.respiratoryRate = 0;
	}

	public int getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(int heartRate) {
		this.heartRate = heartRate;
	}

	public double getBodyTemperature() {
		return bodyTemperature;
	}

	public void setBodyTemperature(double bodyTemperature) {
		this.bodyTemperature = bodyTemperature;
	}

	public int getBloodPressure() {
		return bloodPressure;
	}

	public void setBloodPressure(int bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	public int getRespiratoryRate() {
		return respiratoryRate;
	}

	public void setRespiratoryRate(int respiratoryRate) {
		this.respiratoryRate = respiratoryRate;
	}

}
