package com.cps406_s4_group7_w16.BioInfo;

import java.util.Random;

public class BloodPressureGenerator {
	
	private double systolicMidpoint;
	private int systolicDeviation;
	private double diastolicMidpoint;
	private int diastolicDeviation;
	
	public BloodPressureGenerator(double sysMid, int sysDev, double diaMid, int diaDev) {
		
		this.setSystolicMidpoint(sysMid);
		this.setSystolicDeviation(sysDev);
		this.setDiastolicMidpoint(diaMid);
		this.setDiastolicDeviation(diaDev);
		
	}

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
	
	public void setSystolicDeviation(int sysDev){
		this.systolicDeviation = sysDev;
	}
	
	public int getSystolicDeviation(){
		return systolicDeviation;
	}
	
	public void setDiastolicDeviation(int diaDev){
		this.diastolicDeviation = diaDev;
	}

	public int getDiastolicDeviation(){
		return diastolicDeviation;
	}
	
	public void setSystolicMidpoint(double sysMid){
		this.systolicMidpoint = sysMid;
	}
	
	public void setDiastolicMidpoint(double diaMid){
		this.diastolicMidpoint = diaMid;
	}
	
	public double getDiastolicMidpoint(){
		return diastolicMidpoint;
	}
	
	public double getSystolicMidpoint(){
		return diastolicMidpoint;
	}
	
	
}
