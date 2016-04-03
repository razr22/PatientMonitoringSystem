package com.cps406_s4_group7_w16.PatientInfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Agenda {

	final ObservableList<AgendaEvent> agenda = FXCollections.observableArrayList();

	public Agenda(){
		
	}
	
	/**
	 * Agenda constructor that takes a file as input.
	 * @param file input file containing agenda. agenda events are written as a time and event name.
	 * @throws FileNotFoundException
	 */
	public Agenda(File file) throws FileNotFoundException{
		Scanner in = new Scanner(file);

		//skipping over PatientInfo in file
		for(int i = 0; i < 13; i++){
			in.nextLine();
		}
		
		String nextLine = in.nextLine();
		
		//if next line is not "VITAL SIGN LOG" or an empty line we copy it.
		while(nextLine.equals("VITAL SIGN LOG") != true && nextLine.equals("") != true){
			
			//extracting time and event name from line.
			String timeFromLine = nextLine.substring(0, 7);
			String eventFromLine = nextLine.substring(10);

			//adding event to agenda.
			AgendaEvent event = new AgendaEvent(timeFromLine, eventFromLine);
			this.addEvent(event);
			
			//getting next line
			nextLine = in.nextLine();
		}

		//closing file.
		in.close();
	}
	
	public void addEvent(AgendaEvent event){
		agenda.add(event);
	}
	
	public void removeEvent(){
		agenda.remove(agenda.size()-1);
	}

	public ObservableList<AgendaEvent> getAgenda() {
		return agenda;
	}
	
	public void saveAgenda(String filename) throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
		writer.write("AGENDA\n");
		writer.write("Time\t\tEvent\n");
		writer.write("-----------------------------------------------------------------------------------------------------------\n");
		//for each agendaEvent print the time of event and event name.
		for(AgendaEvent a : agenda){
			
			String eventString = a.getEvent();
			String timeString = a.getTime();
			writer.write(timeString + "\t-\t");
			writer.write(eventString + "\n");
		}
		writer.close();
		
	}
}