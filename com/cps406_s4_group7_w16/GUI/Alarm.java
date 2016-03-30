package com.cps406_s4_group7_w16.GUI;

public class Alarm {
	private double upperBound;
	private double lowerBound;
	
	public Alarm(double upper, double lower){
		this.setUpperBound(upper);
		this.setLowerBound(lower);
	}
	
	
	public void soundAlarm(){
		//TODO: what happens when the alarm is sounded ie. window pop-ups.
	}

	public double getUpperBound() {
		return upperBound;
	}

	public void setUpperBound(double upperBound) {
		this.upperBound = upperBound;
	}

	public double getLowerBound() {
		return lowerBound;
	}

	public void setLowerBound(double lowerBound) {
		this.lowerBound = lowerBound;
	}
	
	
}
