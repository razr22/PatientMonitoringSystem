package com.cps406_s4_group7_w16.PatientInfo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class PatientTester {

	public static void main(String[] args) throws IOException {
		
		Patient pat = new Patient("Pat", 20, 20, 20);
		pat.writeToFile("Pat.txt");
	}

}
