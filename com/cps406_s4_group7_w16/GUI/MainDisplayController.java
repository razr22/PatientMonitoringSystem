package com.cps406_s4_group7_w16.GUI;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainDisplayController {

	public void displayPatientSimulator() throws IOException{
		System.out.println("Patient Control");
		
		Parent root = FXMLLoader.load(getClass().getResource("SimulationDisplay.fxml"));
		
		Scene scene = new Scene(root,600,720);
		scene.getStylesheets().add(getClass().getResource("SimulationDisplayStyle.css").toExternalForm());
		
		Stage window = new Stage();
		window.setTitle("Patient Simulator v1.0");
		window.setScene(scene);
		window.show();
	}
	
}
