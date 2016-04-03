package com.cps406_s4_group7_w16.PatientInfo;

import java.io.IOException;

public class AgendaTester {

	public static void main(String[] args) throws IOException {
		Agenda agenda = new Agenda();
		AgendaEvent event = new AgendaEvent("2:00", "MRI");
		agenda.addEvent(event);
		agenda.writeToFile("agendaTESTER.txt");
		
	}

}
