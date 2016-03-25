package com.cps406_s4_group7_w16.BioInfo;

public class TemperatureGenerator extends Generator {

	//constants
	final double CONVERSION_MULTIPLIER = 1.8;
	final int CONVERSION_OFFSET = 32;
	
	/**
	 * Class constructor that uses a lower and an upper bound.
	 * @param lower	lower bound of number to be generated.
	 * @param upper	upper bound of number to be generated.
	 */
	public TemperatureGenerator(int lower, int upper) {
		super(lower, upper);
	}
	
	/**
	 * Class constructor that uses an upper and a lower bound as well as a boolean to determine which unit system to use.
	 * @param lower	lower bound of number to be generated.
	 * @param upper	upper bound of number to be generated.
	 * @param um
	 */
	public TemperatureGenerator(int lower, int upper, boolean um){
		super(lower, upper, um);
	}
	
	/**
	 * Method used to change the unit system being used by converting upper and lower bounds.
	 * @param um	boolean used for unit system. true for metric, false for imperial.
	 */
	public void convertUnits(boolean um){
		
		//conversion from metric to imperial.
		if(um == false && usesMetricSystem == true){
			usesMetricSystem = false;
			lowerBound = (int)((lowerBound * CONVERSION_MULTIPLIER) + CONVERSION_OFFSET);
			upperBound = (int)((upperBound * CONVERSION_MULTIPLIER) + CONVERSION_OFFSET);
			return;
		}
		
		//conversion from imperial to metric.
		if(um == true && usesMetricSystem == false){
			usesMetricSystem = false;
			lowerBound = (int)((lowerBound - CONVERSION_OFFSET) / CONVERSION_MULTIPLIER);
			upperBound = (int)((upperBound - CONVERSION_OFFSET) / CONVERSION_MULTIPLIER);
			return;
			
		}
		//if unit system being converted to is the same as current system.
		return;
	}
	
}
