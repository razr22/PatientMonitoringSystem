package com.cps406_s4_group7_w16.BioInfo;

public class BloodPressureGeneratorTester {
	
	
	public static void main(String[] args) {

		BloodPressureGenerator bpg = new BloodPressureGenerator(105 , 15, 80, 10);
		BloodPressure bp = bpg.generate();
		
		System.out.println("Systolic: " + bp.getSystolic());
		System.out.println("Diastolic: " + bp.getDiastolic());
		
	}

}
