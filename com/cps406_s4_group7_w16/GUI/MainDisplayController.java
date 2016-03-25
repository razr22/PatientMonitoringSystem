/**
 * This class, MainDisplayController.java, is where the core functionality of the main 
 * display exists. This includes the agenda, graphs, and patient profile. 
 * @author Andy S. Llactahuamani
 * @version 1.0
 *
 */

package com.cps406_s4_group7_w16.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import com.cps406_s4_group7_w16.PatientInfo.Agenda;
import com.cps406_s4_group7_w16.PatientInfo.AgendaEvent;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainDisplayController implements Initializable {

	Agenda agenda = new Agenda();

	@FXML
	TableView<AgendaEvent> schedule;
	@FXML
	TableColumn<AgendaEvent, Integer> time;
	@FXML
	TableColumn<AgendaEvent, String> event;

	private IntegerProperty index = new SimpleIntegerProperty();

	@FXML
	TextField eventName;
	@FXML
	ComboBox<String> hour;
	@FXML
	ComboBox<String> minute;
	@FXML
	ComboBox<String> am_pm;

	private Timeline timeline;
	private SimpleIntegerProperty lastX = new SimpleIntegerProperty(0);
	private XYChart.Series<Number, Number> series;
	private Random rand = new Random();
	private ObservableList<XYChart.Series<Number, Number>> data = FXCollections.observableArrayList();
	@FXML
	private NumberAxis xAxis = new NumberAxis(); // xAxis
	@FXML
	private NumberAxis yAxis = new NumberAxis(); // yAxis

	@FXML
	LineChart<Number, Number> heartRateChart;
	@FXML
	LineChart<Number, Number> bloodPreasureChart;
	@FXML
	LineChart<Number, Number> respiratoryRateChart;
	@FXML
	LineChart<Number, Number> bodyTemperatureChart = new LineChart<>(xAxis, yAxis);

	/**
	 * A method from "Initializable" that must be implemented. This is
	 * implemented so that the program can go through an initialize phase, when
	 * it is able to setup initial code
	 * 
	 * @param arg0
	 *            irrelevant
	 * @param arg1
	 *            irrelevant
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		displayAgenda();
		// Timeline
		// Initizalization-----------------------------------------------------------
		timeline = new Timeline(new KeyFrame(Duration.millis(1000), e -> {
			lastX.set(lastX.add(1).get());
			System.out.println(lastX.get() + " seconds have passed");
			series.getData().add(new XYChart.Data<Number, Number>(lastX.get(), rand.nextInt(100)));
			if (series.getData().size() > 10)
				series.getData().remove(0);
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		updateTemperature();

		// --------------------------------------------------------------------------------------

	}

	/**
	 * Opens a new window for simulation control, which influences values that
	 * ultimately changes the behavior of the patients heart rate, blood
	 * pressure, respiratory rate, and temperature.
	 * 
	 * @throws IOException
	 */
	public void displayPatientSimulator() throws IOException {
		System.out.println("Patient Control");

		Parent root = FXMLLoader.load(getClass().getResource("SimulationDisplay.fxml"));

		Scene scene = new Scene(root, 600, 720);
		scene.getStylesheets().add(getClass().getResource("SimulationDisplayStyle.css").toExternalForm());

		Stage window = new Stage();
		window.setTitle("Patient Simulator v1.0");
		window.setScene(scene);
		window.show();
	}

	/**
	 * Method used by the "Add Event" button. Adds an entry to the Patient
	 * Agenda
	 */
	public void addAgendaEntryButton() {
		System.out.println("Add");

		String tempStringTime = hour.getValue() + minute.getValue();
		int tempTime;

		if (am_pm.getValue().equals("AM")) {
			tempTime = Integer.parseInt(tempStringTime);
			System.out.println(tempTime);

			tempStringTime = hour.getValue() + ":" + minute.getValue() + am_pm.getValue();

			AgendaEvent entry = new AgendaEvent(tempStringTime, eventName.getText());
			agenda.addEvent(entry);
			clearForm();
		} else if (am_pm.getValue().equals("PM")) {
			tempTime = Integer.parseInt(tempStringTime) + 1200;
			System.out.println(tempTime);

			tempStringTime = String.valueOf(tempTime - Integer.parseInt(minute.getValue())).substring(0, 2) + ":"
					+ minute.getValue() + am_pm.getValue();

			AgendaEvent entry = new AgendaEvent(tempStringTime, eventName.getText());
			agenda.addEvent(entry);
			clearForm();
		}

	}

	/**
	 * Method used by the "Remove Event" button. Removes an entry from the
	 * Patient Agenda
	 */
	public void removeAgendaEntryButton() {
		int i = index.get();
		if (i > -1) {
			agenda.getAgenda().remove(i);
			schedule.getSelectionModel().clearSelection();
		}
	}

	/**
	 * Method used by the "Start Simulation" file item. Activates all line
	 * charts to start generating values for chart plotting. This method maybe
	 * removed, depending if the group decides to activate the charts on start
	 * up
	 */
	public void startSimulationButton() {

	}

	/**
	 * Method used by the "Exit" file item. Exits program. TODO: Figure out how
	 * to access outer class from inner class to exit
	 */
	public void exitButton() {
		System.exit(0);
	}

	/**
	 * This method is used to reset the textbox and dropdowns used for Agenda
	 * entry to its initial values. This method is used after every time an
	 * agenda entry is made.
	 */
	private void clearForm() {
		eventName.clear();
		hour.getSelectionModel().clearSelection();
		minute.getSelectionModel().clearSelection();
		am_pm.getSelectionModel().clearSelection();
		hour.setValue("12");
		minute.setValue("00");
		am_pm.setValue("AM");
	}

	/**
	 * This method is used for the initialization of the Agenda Form and the
	 * Agenda Table
	 */
	private void displayAgenda() {
		hour.setItems(FXCollections.observableArrayList("00", "01", "02", "03", "04", "05", "06", "07", "08", "09",
				"10", "11", "12"));
		minute.setItems(FXCollections.observableArrayList("00", "05", "10", "15", "20", "25", "30", "35", "40", "45",
				"50", "55"));
		am_pm.setItems(FXCollections.observableArrayList("AM", "PM"));
		hour.setValue("12");
		minute.setValue("00");
		am_pm.setValue("AM");
		time.setCellValueFactory(new PropertyValueFactory<AgendaEvent, Integer>("Time"));
		event.setCellValueFactory(new PropertyValueFactory<AgendaEvent, String>("Event"));

		schedule.setItems(agenda.getAgenda());

		schedule.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {

			@Override
			public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
				index.set(agenda.getAgenda().indexOf(newValue));
				System.out.println("OK index is :" + agenda.getAgenda().indexOf(newValue));
			}
		});
	}

	private void updateHeartRate() {

	}

	private void updateRespiratoryRate() {

	}

	private void updateBloodPreasure() {

	}

	private void updateTemperature() {
		xAxis.setForceZeroInRange(false);
		bodyTemperatureChart.setCreateSymbols(true);
		bodyTemperatureChart.setData(data);

		series = new XYChart.Series<>();
		data.add(series);
		lastX.set(0);
		timeline.playFromStart();
	}

}
