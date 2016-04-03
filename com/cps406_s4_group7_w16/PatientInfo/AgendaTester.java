package com.cps406_s4_group7_w16.PatientInfo;

import java.io.File;
import java.io.IOException;

public class AgendaTester {

	public static void main(String[] args) throws IOException {
		File file = new File("Paul Martins.txt");
		Agenda agenda = new Agenda(file);

		agenda.saveAgenda("agendaTESTER.txt");
		
	}

}
