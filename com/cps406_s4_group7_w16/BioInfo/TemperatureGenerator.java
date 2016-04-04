package com.cps406_s4_group7_w16.BioInfo;

public class TemperatureGenerator extends Generator {

	//constants
	final double CONVERSION_MULTIPLIER = 1.8;
	final int CONVERSION_OFFSET = 32;
	
	/**
	 * Class constructor that uses a lower and an upper bound.
	 * @param mid	midpoint of number to be generated.
	 * @param dev	deviation from midpoint.
	 */
	public TemperatureGenerator(int mid, int dev) {
		super(mid, dev);
	}
	
	/**
	 * Class constructor that uses an upper and a lower bound as well as a boolean to determine which unit system to use.
	 * @param mid	midpoint of number to be generated.
	 * @param upper	deviation from midpoint.
	 * @param um
	 */
	public TemperatureGenerator(int mid, int dev, boolean um){
		super(mid, dev, um);
	}
	
	/**
	 * Method used to change the unit system being used by converting upper and lower bounds.
	 * @param um	boolean used for unit system. true for metric, false for imperial.
	 */
	public void convertUnits(boolean um){
		
		//conversion from metric to imperial.
		if(um == false && usesMetricSystem == true){
			usesMetricSystem = false;
			midpoint = (int)((midpoint * CONVERSION_MULTIPLIER) + CONVERSION_OFFSET);
			deviation = (int)((deviation * CONVERSION_MULTIPLIER) + CONVERSION_OFFSET);
			return;
		}
		
		//conversion from imperial to metric.
		if(um == true && usesMetricSystem == false){
			usesMetricSystem = false;
			midpoint = (int)((midpoint - CONVERSION_OFFSET) / CONVERSION_MULTIPLIER);
			deviation = (int)((deviation - CONVERSION_OFFSET) / CONVERSION_MULTIPLIER);
			return;
			
		}
		//if unit system being converted to is the same as current system.
		return;
	}
	
}
