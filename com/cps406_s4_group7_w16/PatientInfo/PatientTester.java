package com.cps406_s4_group7_w16.PatientInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class PatientTester {

	public static void main(String[] args) throws IOException {
		
		File file = new File("Paul Martins.txt");
		Patient pat = new Patient(file);
		
		//printing values back.
		System.out.println("Name: " + pat.getName());
		System.out.println("Age: " + pat.getAge());
		System.out.println("Height: " + pat.getHeight());
		System.out.println("Weight: " + pat.getWeight());
		System.out.println("Blood Type: " + pat.getBloodType());

	}

}
