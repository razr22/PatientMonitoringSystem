package com.cps406_s4_group7_w16.PatientInfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Agenda {

	final ObservableList<AgendaEvent> agenda = FXCollections.observableArrayList();
	
	public void addEvent(AgendaEvent event){
		agenda.add(event);
	}
	
	public void removeEvent(){
		agenda.remove(agenda.size()-1);
	}

	public ObservableList<AgendaEvent> getAgenda() {
		return agenda;
	}
	
	public void writeToFile(String filename) throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		
		//for each agendaEvent print the time of event and event name.
		for(AgendaEvent a : agenda){
			
			String eventString = a.getEvent();
			String timeString = a.getTime();
			writer.write("Agenda\n");
			writer.write("-----------------------------------------------------------------------------------------------------------\n");
			writer.write(timeString + "\t-\t");
			writer.write(eventString + "\n");
		}
		writer.close();
		
	}
}
