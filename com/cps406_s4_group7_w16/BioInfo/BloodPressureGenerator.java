package com.cps406_s4_group7_w16.BioInfo;

import java.util.Random;

/**
 * Class that defines a BloodPressureGenerator data type. Generator is used for creating simulated values of sensor readings.
 * This class is different from the its inherited class because it generates two values, one for the systolic blood pressure
 * and one for the diastolic blood pressure.
 * @author Paul Martins
 *
 */
public class BloodPressureGenerator {
	
	private double systolicMidpoint;
	private int systolicDeviation;
	private double diastolicMidpoint;
	private int diastolicDeviation;
	
	/**
	 * Class constructor
	 * @param sysMid	Systolic midpoint.
	 * @param sysDev	Systolic deviation.
	 * @param diaMid	Diastolic midpoint.
	 * @param diaDev	Diastolic deviation.
	 */
	public BloodPressureGenerator(double sysMid, int sysDev, double diaMid, int diaDev) {
		
		this.setSystolicMidpoint(sysMid);
		this.setSystolicDeviation(sysDev);
		this.setDiastolicMidpoint(diaMid);
		this.setDiastolicDeviation(diaDev);
		
	}

	/**
	 * Method that generates two values for Systolic and Diastolic blood pressure.
	 * @return BloodPressure object generate.
	 */
	public BloodPressure generate() {
		
		double systolicValue = 0;
		double diastolicValue = 0;
		Random random = new Random();
		int highOrLow = random.nextInt(2);
		
		//for systolic rate.
		if(highOrLow == 0){
			systolicValue = systolicMidpoint + (random.nextInt(systolicDeviation));		
			diastolicValue = diastolicMidpoint + (random.nextInt(diastolicDeviation));	
		}
		
		if(highOrLow == 1){
			systolicValue = systolicMidpoint - (random.nextInt(systolicDeviation));
			diastolicValue = diastolicMidpoint - (random.nextInt(diastolicDeviation));
		}
		
		// generating a decimal to add to the systolicValue.
		systolicValue = systolicValue + random.nextDouble();
		// Rounding to two decimal places.
		systolicValue = Math.floor(systolicValue * 100) / 100;


		// generating a decimal to add to the diastolicValue.
		diastolicValue = diastolicValue + random.nextDouble();
		// Rounding to two decimal places.
		diastolicValue = Math.floor(diastolicValue * 100) / 100;
		
		BloodPressure bp = new BloodPressure(systolicValue, diastolicValue);

		return bp;
	}
	
	/**
	 * Setter method for systolicDeviation.
	 * @param sysDev deviation.
	 */
	public void setSystolicDeviation(int sysDev){
		this.systolicDeviation = sysDev;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getSystolicDeviation(){
		return systolicDeviation;
	}
	
	/**
	 * Setter method for diastolicDeviation.
	 * @param diaDev deviation.
	 */
	public void setDiastolicDeviation(int diaDev){
		this.diastolicDeviation = diaDev;
	}

	/**
	 * Getter method for diastolicDeviation
	 * @return diastolicDeviation
	 */
	public int getDiastolicDeviation(){
		return diastolicDeviation;
	}
	
	/**
	 * Setter method for systolicMidpoint.
	 * @param sysMid midpoint.
	 */
	public void setSystolicMidpoint(double sysMid){
		this.systolicMidpoint = sysMid;
	}
	
	/**
	 * Setter method for diastolicMidpoint.
	 * @param diaMid midpoint.
	 */
	public void setDiastolicMidpoint(double diaMid){
		this.diastolicMidpoint = diaMid;
	}
	
	/**
	 * Getter method for diastolicMidpoint
	 * @return diastolicMidpoint
	 */
	public double getDiastolicMidpoint(){
		return diastolicMidpoint;
	}
	
	/**
	 * Getter method systolicMidpoint
	 * @return systolicMidpoint
	 */
	public double getSystolicMidpoint(){
		return diastolicMidpoint;
	}
	
	
}
