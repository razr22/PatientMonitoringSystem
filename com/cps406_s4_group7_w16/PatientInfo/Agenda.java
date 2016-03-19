package com.cps406_s4_group7_w16.PatientInfo;

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
}
