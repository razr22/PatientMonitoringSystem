package com.cps406_s4_group7_w16.BioInfo;

import java.util.Random;
import java.lang.Math;

public class Generator {
	
	protected boolean usesMetricSystem = true;
	protected double upperBound;
	protected double lowerBound;

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
	public Generator(double lower, double upper, boolean um) {
		this.setUpperBound(upper);
		this.setLowerBound(lower);
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
	public Generator(double lower, double upper) {
		this.setUpperBound(upper);
		this.setLowerBound(lower);
	}

	/**
	 * Generates a Double value from upper to lower bound as specified by the
	 * generator object.
	 * 
	 * @return Double value in between lower and upper bounds.
	 */
	public double generate() {
		double value;
		Random random = new Random();

		// number from lowerBound to UpperBound.
		value = (random.nextInt((int) upperBound - (int) lowerBound)) + (int) lowerBound;

		// generating a decimal to add to the value.
		value = value + random.nextDouble();

		// Rounding to two decimal places.
		value = Math.floor(value * 100) / 100;
		return value;
	}

	/**
	 * Getter method for upper bound.
	 * 
	 * @return upper bound of generator.
	 */
	public double getUpperBound() {
		return upperBound;
	}

	/**
	 * Setter method for upper bound.
	 * 
	 * @param upperBound
	 *            Double value to become upper bound.
	 */
	public void setUpperBound(double upperBound) {
		this.upperBound = upperBound;
	}

	/**
	 * Getter method for lower bound.
	 * 
	 * @return lower bound of generator.
	 */
	public double getLowerBound() {
		return lowerBound;
	}

	/**
	 * Setter method for lower bound.
	 * 
	 * @param lowerBound
	 *            Double value to become lower bound.
	 */
	public void setLowerBound(double lowerBound) {
		this.lowerBound = lowerBound;
	}

}
