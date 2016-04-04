package com.cps406_s4_group7_w16.PatientInfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class that defines an Agenda data type. An Agenda object holds a list of AgendaEvents.
 * @author Paul Martins
 *
 */
public class Agenda {

	final ObservableList<AgendaEvent> agenda = FXCollections.observableArrayList();

	/**
	 * Default Class Constructor.
	 * Does nothing.
	 */
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
	
	/**
	 * Method that adds an event to the Agenda.
	 * @param event AgendaEvent to be added.
	 */
	public void addEvent(AgendaEvent event){
		agenda.add(event);
	}
	
	/**
	 * Method that removes the last event in the Agenda.
	 */
	public void removeEvent(){
		agenda.remove(agenda.size()-1);
	}

	/**
	 * Getter for the Agenda
	 * @return Agenda to be returned.
	 */
	public ObservableList<AgendaEvent> getAgenda() {
		return agenda;
	}
	
	/**
	 * Method that saves an Agenda to a file.
	 * @param filename filename to be saved to.
	 * @throws IOException
	 */
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