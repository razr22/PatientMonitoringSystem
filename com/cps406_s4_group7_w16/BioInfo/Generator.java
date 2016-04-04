package com.cps406_s4_group7_w16.BioInfo;

import java.util.Random;
import java.lang.Math;

public class Generator {
	
	protected boolean usesMetricSystem = true;
	protected double midpoint;
	protected int deviation;

	/**
	 * Class constructor that uses an upper and lower bound as well as a boolean
	 * to determine which unit system to use.
	 * 
	 * @param lower
	 *            lower bound of number to be generated.
	 * @param upper
	 *            upper bound of number to be generated.
	 * @param usesMetricStystem
	 *            boolean that holds the unit of which values are to be
	 *            generated. true for metric system, false for imperial system.
	 */
	public Generator(double mid, int dev, boolean um) {
		this.setDeviation(dev);
		this.setMidpoint(mid);
		this.usesMetricSystem = um;
	}

	/**
	 * Class constructor that uses an upper bound and a lower bound.
	 * 
	 * @param lower
	 *            lower bound of number to be generated.
	 * @param upper
	 *            upper bound of number to be generated,
	 */
	public Generator(double mid, int dev) {
		this.setDeviation(dev);
		this.setMidpoint(mid);
	}

	/**
	 * Generates a Double value from upper to lower bound as specified by the
	 * generator object.
	 * 
	 * @return Double value in between lower and upper bounds.
	 */
	public double generate() {
		double value = 0;
		Random random = new Random();
		int highOrLow = random.nextInt(2);
		
		// number from lowerBound to UpperBound.
		//value = (random.nextInt((int) upperBound - (int) lowerBound)) + (int) lowerBound;
		
		//go higher to the midpoint
		if(highOrLow == 0){
			value = midpoint + (random.nextInt(deviation));		
		}
		
		if(highOrLow == 1){
			value = midpoint - (random.nextInt(deviation));
		}
		
		// generating a decimal to add to the value.
		value = value + random.nextDouble();

		// Rounding to two decimal places.
		value = Math.floor(value * 100) / 100;
		return value;
	}

	/**
	 * Getter method for deviation.
	 * 
	 * @return deviation of generator.
	 */
	public double getDeviation() {
		return deviation;
	}

	/**
	 * Setter method for deviation.
	 * 
	 * @param upperBound
	 *            Integer value to become deviation.
	 */
	public void setDeviation(int dev) {
		this.deviation =  dev;
	}
	
	public void setMidpoint(double mid){
		this.midpoint = mid;
	}
	
	public double getMidpoint(){
		return this.midpoint;
	}

}
