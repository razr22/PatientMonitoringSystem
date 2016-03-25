/**
 * This program allows the user (typically a doctor or nurse) to monitor and
 * save a patients heart rate, blood pressure, respiratory rate, and body
 * Temperature. This program also allows the user keep a record of a patients
 * agenda
 * 
 * This class, Main.java, acts as the entry point for the program, a JavaFX
 * application, to start. Main extends "Application" which is required to
 * implement the JavaFX library
 * 
 * @author Andy S. Llactahuamani
 * @version 1.0
 *
 */

package com.cps406_s4_group7_w16.GUI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {

	Stage window;

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * A method in "Application" that must be implemented to initialize a JavaFX
	 * application.
	 * 
	 * @param primaryStage
	 *            the window of the program
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			window = primaryStage;

			Parent root = FXMLLoader.load(getClass().getResource("MainDisplay.fxml"));

			Scene scene = new Scene(root, 1080, 720);
			scene.getStylesheets().add(getClass().getResource("MainDisplayStyle.css").toExternalForm());

			window.setTitle("Patient Monitoring System v1.0");
			window.setScene(scene);
			window.show();
			window.setOnCloseRequest(e -> {
				Platform.exit();
				System.exit(0);
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
