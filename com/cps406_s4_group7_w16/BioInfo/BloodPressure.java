package com.cps406_s4_group7_w16.BioInfo;

public class BloodPressure {

	private double systolic;
	private double diastolic;
	
	public BloodPressure(){
		this.setSystolic(0);
		this.setDiastolic(0);
	}
	
	public BloodPressure(double sys, double dia){
		this.setSystolic(sys);
		this.setDiastolic(dia);
	}

	public double getSystolic() {
		return systolic;
	}

	public void setSystolic(double systolic) {
		this.systolic = systolic;
	}

	public double getDiastolic() {
		return diastolic;
	}

	public void setDiastolic(double diastolic) {
		this.diastolic = diastolic;
	}
}
