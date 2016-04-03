/**
 * This class, MainDisplayController.java, is where the core functionality of the main 
 * display exists. This includes the agenda, graphs, and patient profile. 
 * @author Andy S. Llactahuamani
 * @version 1.0
 *
 */

package com.cps406_s4_group7_w16.GUI;

import com.cps406_s4_group7_w16.BioInfo.*;
import com.cps406_s4_group7_w16.PatientInfo.*;
import com.cps406_s4_group7_w16.Security.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.ResourceBundle;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainDisplayController implements Initializable {
	// this is a push test
	// Agenda
	// Variables----------------------------------------------------------------

	@FXML
	TableView<AgendaEvent> schedule;
	@FXML
	TableColumn<AgendaEvent, Integer> time;
	@FXML
	TableColumn<AgendaEvent, String> event;

	private IntegerProperty index = new SimpleIntegerProperty();
	// Agenda
	// Variables----------------------------------------------------------------

	// Form
	// Variables-----------------------------------------------------------------
	@FXML
	TextField eventName;
	@FXML
	ComboBox<String> hour;
	@FXML
	ComboBox<String> minute;
	@FXML
	ComboBox<String> am_pm;
	// Form
	// Variables-----------------------------------------------------------------

	// Timeline
	// Variables----------------------------------------------------------------------
	private Timeline timeline;
	private SimpleIntegerProperty secondsPassed = new SimpleIntegerProperty(0);
	// Timeline
	// Variables----------------------------------------------------------------------

	// Patient Profile
	// Variables-------------------------------------------------------------
	@FXML
	private TextField patientName;
	@FXML
	private TextField patientAge;
	@FXML
	private TextField patientHeight;
	@FXML
	private TextField patientWeight;
	@FXML
	private TextField patientBloodType;

	// Patient Profile
	// Variables-------------------------------------------------------------

	// Chart
	// Series--------------------------------------------------------------------------
	private XYChart.Series<Number, Number> HR_Series = new XYChart.Series<>();
	private XYChart.Series<Number, Number> RR_Series = new XYChart.Series<>();
	private XYChart.Series<Number, Number> BP_SystolicSeries = new XYChart.Series<>();
	private XYChart.Series<Number, Number> BP_DiastolicSeries = new XYChart.Series<>();
	private XYChart.Series<Number, Number> BT_Series = new XYChart.Series<>();
	// Chart
	// Series--------------------------------------------------------------------------

	// Generator Constants
	private final int HR_MID = 80;
	private final int HR_DEV = 20;
	private final int RR_MID = 16;
	private final int RR_DEV = 4;
	private final int BB_SYSTOLICMID = 105;
	private final int BB_SYSTOLICDEV = 15;
	private final int BB_DIASTOLICMID = 70;
	private final int BB_DIASTOLICDEV = 10;
	private final int BT_MID = 37;
	private final int BT_DEV = 1;

	//Generator Constants
	
	// Generator
	// objects-------------------------------------------------------------------
	private Generator HR_Gen = new Generator(HR_MID, HR_DEV); // beats per minute
	private Generator RR_Gen = new Generator(RR_MID, RR_DEV); // breaths per minute
	private Generator BP_SystolicGen = new Generator(BB_SYSTOLICMID, BB_SYSTOLICDEV); // Systolic Blood Pressure
	private Generator BP_DiastolicGen = new Generator(BB_DIASTOLICMID, BB_DIASTOLICDEV); // Diastolic Blood Pressure
	private TemperatureGenerator BT_Gen = new TemperatureGenerator(BT_MID, BT_DEV); // Degrees Celsius
	// Generator
	// objects-------------------------------------------------------------------

	// Data
	// Lists--------------------------------------------------------------------------------------------------------
	private ObservableList<XYChart.Series<Number, Number>> HR_Data = FXCollections.observableArrayList();
	private ObservableList<XYChart.Series<Number, Number>> RR_Data = FXCollections.observableArrayList();
	private ObservableList<XYChart.Series<Number, Number>> BP_Data = FXCollections.observableArrayList();
	private ObservableList<XYChart.Series<Number, Number>> BT_Data = FXCollections.observableArrayList();
	// Data
	// Lists--------------------------------------------------------------------------------------------------------

	// Chart
	// Axes---------------------------------------------------------------------------
	@FXML
	private NumberAxis HRxAxis = new NumberAxis(); // HEART RATE xAxis
	@FXML
	private NumberAxis HRyAxis = new NumberAxis(); // HEART RATE yAxis
	@FXML
	private NumberAxis RRxAxis = new NumberAxis(); // RESPIRATORY RATE xAxis
	@FXML
	private NumberAxis RRyAxis = new NumberAxis(); // RESPIRATORY RATE yAxis
	@FXML
	private NumberAxis BPxAxis = new NumberAxis(); // BLOOD PRESSURE xAxis
	@FXML
	private NumberAxis BPyAxis = new NumberAxis(); // BLOOD PRESSURE yAxis
	@FXML
	private NumberAxis BTxAxis = new NumberAxis(); // BODY TEMPERATURE xAxis
	@FXML
	private NumberAxis BTyAxis = new NumberAxis(); // BODY TEMPERATURE yAxis
	// Chart
	// Axes---------------------------------------------------------------------------

	// Line
	// Charts----------------------------------------------------------------------------------
	@FXML
	LineChart<Number, Number> HR_Chart = new LineChart<>(HRxAxis, HRyAxis);
	@FXML
	LineChart<Number, Number> BP_Chart = new LineChart<>(BPxAxis, BPyAxis);
	@FXML
	LineChart<Number, Number> RR_Chart = new LineChart<>(RRxAxis, RRyAxis);
	@FXML
	LineChart<Number, Number> BT_Chart = new LineChart<>(BTxAxis, BTyAxis);
	// Line
	// Charts----------------------------------------------------------------------------------

	// Saved Data Variables
	Log log = new Log();
	VitalSign vitalSign = new VitalSign();
	Patient patient = new Patient();
	Agenda agenda = new Agenda();

	DateFormat timeformat = new SimpleDateFormat("HH:mm:ss");
	Calendar calendar;
	// Saved Data Variables

	// Quick Data Labels
	@FXML
	Label HR_QuickData = new Label();
	@FXML
	Label BP_QuickData = new Label();
	@FXML
	Label RR_QuickData = new Label();
	@FXML
	Label BT_QuickData = new Label();

	// Quick Data Labels

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

			secondsPassed.set(secondsPassed.add(1).get());
			// System.out.println("Seconds Passed: " + secondsPassed.get());
			vitalSign.setHeartRate(HR_Gen.generate());
			vitalSign.setSystolicBloodPressure((int) BP_SystolicGen.generate());
			vitalSign.setDiastolicBloodPressure((int) BP_DiastolicGen.generate());
			vitalSign.setRespiratoryRate(RR_Gen.generate());
			vitalSign.setBodyTemperature(BT_Gen.generate());
			calendar = Calendar.getInstance();
			vitalSign.setTimeStamp(timeformat.format(calendar.getTime()));

			updateHeartRate(secondsPassed.get(), vitalSign.getHeartRate());
			updateBloodPressure(secondsPassed.get(), vitalSign.getSystolicBloodPressure(),
					vitalSign.getDiastolicBloodPressure());
			updateRespiratoryRate(secondsPassed.get(), vitalSign.getRespiratoryRate());
			updateTemperature(secondsPassed.get(), vitalSign.getBodyTemperature());

			System.out.println(vitalSign.getTimeStamp());
			System.out.println(vitalSign.toString());
			log.addLogEntry(vitalSign);
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		// --------------------------------------------------------------------------------------
		chartInit();
		timeline.playFromStart();
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

		Scene scene = new Scene(root, 720, 225);
		scene.getStylesheets().add(getClass().getResource("SimulationDisplayStyle.css").toExternalForm());

		Stage window = new Stage();
		window.setTitle("Patient Simulator v1.0");
		window.setScene(scene);
		window.show();
	}

	public void displayAlarmSettings() throws IOException{
		System.out.println("Alarm Settings");

		Parent root = FXMLLoader.load(getClass().getResource("AlarmSettings.fxml"));

		Scene scene = new Scene(root, 750, 225);
		scene.getStylesheets().add(getClass().getResource("AlarmSettingsStyle.css").toExternalForm());

		Stage window = new Stage();
		window.setTitle("Alarm Settings v1.0");
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

	public void saveButton() throws IOException {
		getPatientProfile();
		patient.savePatientProfile(patient.getName() + ".txt");
		agenda.saveAgenda(patient.getName() + ".txt");
		log.saveLog(patient.getName() + ".txt");
	}

	/**
	 * Method used by the "Exit" file item. Exits program. TODO: Figure out how
	 * to access outer class from inner class to exit
	 */
	public void exitButton() {
		System.exit(0);
	}
	
	private void getPatientProfile() {

		patient.setName(patientName.getText());
		patient.setAge(patientAge.getText());
		patient.setHeight(patientHeight.getText());
		patient.setWeight(patientWeight.getText());
		patient.setBloodType(patientBloodType.getText());
	}

	/**
	 * This method is used to initialize all four charts to allow x and y Axis
	 * to adjust to appropriate ranges. Also allows charts to have visible data
	 * points on graph. Adds each respective series to respective data lists,
	 * and adds each respective data lists to respective charts.
	 */
	private void chartInit() {
		HRxAxis.setForceZeroInRange(false);
		HRyAxis.setForceZeroInRange(false);
		HR_Chart.setCreateSymbols(true);
		HR_Data.add(HR_Series);
		HR_Chart.setData(HR_Data);

		BPxAxis.setForceZeroInRange(false);
		BPyAxis.setForceZeroInRange(false);
		BP_Chart.setCreateSymbols(true);
		BP_SystolicSeries.setName("Systolic Data");
		BP_DiastolicSeries.setName("Diastolic Data");
		BP_Data.addAll(BP_SystolicSeries, BP_DiastolicSeries);
		BP_Chart.setData(BP_Data);

		RRxAxis.setForceZeroInRange(false);
		RRyAxis.setForceZeroInRange(false);
		RR_Chart.setCreateSymbols(true);
		RR_Data.add(RR_Series);
		RR_Chart.setData(RR_Data);

		BTxAxis.setForceZeroInRange(false);
		BTyAxis.setForceZeroInRange(false);
		BT_Chart.setCreateSymbols(true);
		BT_Data.add(BT_Series);
		BT_Chart.setData(BT_Data);
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

	private void updateHeartRate(int second, double heartRate) {
		HR_Series.getData().add(new XYChart.Data<Number, Number>(second, heartRate));
		HR_QuickData.setText("Quick Data: [Current Heart Rate = " + heartRate + " (BPM)]");
		if (HR_Series.getData().size() > 10)
			HR_Series.getData().remove(0);

	}

	private void updateRespiratoryRate(int second, double breathRate) {
		RR_Series.getData().add(new XYChart.Data<Number, Number>(second, breathRate));
		RR_QuickData.setText("Quick Data: [Current Respiration Rate = " + breathRate + " (RPM)]");
		if (RR_Series.getData().size() > 10)
			RR_Series.getData().remove(0);
	}

	private void updateBloodPressure(int second, int BP_Systolic, int BP_Diastolic) {
		BP_QuickData.setText("Quick Data: [Current Blood Pressure = " + BP_Systolic + "/" + BP_Diastolic + " (mm Hg)]");
		BP_SystolicSeries.getData().add(new XYChart.Data<Number, Number>(second, BP_Systolic));
		if (BP_SystolicSeries.getData().size() > 10)
			BP_SystolicSeries.getData().remove(0);
		BP_DiastolicSeries.getData().add(new XYChart.Data<Number, Number>(second, BP_Diastolic));
		if (BP_DiastolicSeries.getData().size() > 10)
			BP_DiastolicSeries.getData().remove(0);
	}

	private void updateTemperature(int second, double temp) {
		BT_QuickData.setText("Quick Data: [Current Body Temperature = " + temp + " (°C)]");
		BT_Series.getData().add(new XYChart.Data<Number, Number>(second, temp));
		if (BT_Series.getData().size() > 10)
			BT_Series.getData().remove(0);

	}

}
