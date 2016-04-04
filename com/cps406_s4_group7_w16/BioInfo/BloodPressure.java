package com.cps406_s4_group7_w16.BioInfo;

/**
 * Class that defines the BloodPressure data type. A BloodPressure object holds a systolic and diastolic value.
 * @author Paul Martins
 *
 */
public class BloodPressure {

	private double systolic;
	private double diastolic;
	
	/**
	 * Default Class Constructor.
	 */
	public BloodPressure(){
		this.setSystolic(0);
		this.setDiastolic(0);
	}
	
	/**
	 * Class Constructor.
	 * @param sys Systolic value to be added.
	 * @param dia Diastolic value to be added
	 */
	public BloodPressure(double sys, double dia){
		this.setSystolic(sys);
		this.setDiastolic(dia);
	}

	/**
	 * Getter method for systolic
	 * @return systolic value.
	 */
	public double getSystolic() {
		return systolic;
	}

	/**
	 * Setter method for systolic
	 * @param systolic Systolic value to be set.
	 */
	public void setSystolic(double systolic) {
		this.systolic = systolic;
	}

	/**
	 * Getter method for diastolic
	 * @return Diastolic value.
	 */
	public double getDiastolic() {
		return diastolic;
	}

	/**
	 * Setter method for diastolic 
	 * @param diastolic Diastolic value to be set.
	 */
	public void setDiastolic(double diastolic) {
		this.diastolic = diastolic;
	}
}
