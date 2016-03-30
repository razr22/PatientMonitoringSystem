package com.cps406_s4_group7_w16.BioInfo;
import com.cps406_s4_group7_w16.PatientInfo.*;

import java.io.File;
import java.io.IOException;

//TESTER CLASS used to test printing Patient information and appending to that file with the VitalSign data.
public class LogTester {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//instantiating test objects.
		Patient pat = new Patient("Pat", 10,10,10);
		VitalSign vs = new VitalSign(10,10,10,10);
		Log log = new Log();
		log.addLogEntry(vs);
		
		//saving Patient info
		pat.writeToFile("test.txt");
		//saving Log info
		log.saveLog("test.txt");
		
		//opening file to read back the Patient information.
		File file = new File("test.txt");
		Patient pat2 = new Patient(file);
		
		System.out.println(pat2.getName());
		System.out.println(pat2.getAge());
		System.out.println(pat2.getHeight());
		System.out.println(pat2.getWeight());
	}

}
