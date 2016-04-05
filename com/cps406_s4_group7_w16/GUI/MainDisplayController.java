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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Polygon;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainDisplayController implements Initializable {
	@FXML
	MenuItem attachPatient = new MenuItem();
	@FXML
	MenuItem detachPatient = new MenuItem();

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
	private int HR_MID = 80;// 80
	private int HR_DEV = 20;
	private int RR_MID = 16;
	private int RR_DEV = 4;
	private int BP_SYSTOLICMID = 105;
	private int BP_SYSTOLICDEV = 15;
	private int BP_DIASTOLICMID = 80;
	private int BP_DIASTOLICDEV = 10;
	private int BT_MID = 37;
	private int BT_DEV = 1;

	// Generator Constants

	// Generator
	// objects-------------------------------------------------------------------
	private Generator HR_Gen = new Generator(HR_MID, HR_DEV); // beats per
																// minute
	private Generator RR_Gen = new Generator(RR_MID, RR_DEV); // breaths per
																// minute
	private BloodPressureGenerator BP_Gen = new BloodPressureGenerator(BP_SYSTOLICMID, BP_SYSTOLICDEV, BP_DIASTOLICMID,
			BP_DIASTOLICDEV);
	private BloodPressure bpObj;
	private TemperatureGenerator BT_Gen = new TemperatureGenerator(BT_MID, BT_DEV); // Degrees
																					// Celsius
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
	Label HR_QuickDataCurrent = new Label();
	@FXML
	Label BP_QuickDataCurrent = new Label();
	@FXML
	Label RR_QuickDataCurrent = new Label();
	@FXML
	Label BT_QuickDataCurrent = new Label();

	@FXML
	Label HR_QuickDataWarning = new Label();
	@FXML
	Label BP_QuickDataSystolicWarning = new Label();
	@FXML
	Label BP_QuickDataDiastolicWarning = new Label();
	@FXML
	Label RR_QuickDataWarning = new Label();
	@FXML
	Label BT_QuickDataWarning = new Label();
	// Quick Data Labels

	// Alarms
	Alarm HR_Alarm = new Alarm(100, 60);
	Alarm BP_SystolicAlarm = new Alarm(120, 90);
	Alarm BP_DiastolicAlarm = new Alarm(90, 70);
	Alarm RR_Alarm = new Alarm(20, 12);
	Alarm BT_Alarm = new Alarm(38, 36);
	// Alarms

	private static DecimalFormat df2 = new DecimalFormat(".##");

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
		attachPatient.setDisable(true);
		detachPatient.setDisable(false);
		displayAgenda();
		timelineInit();
		chartInit();
	}

	/**
	 * Opens a new window for simulation control, which influences values that
	 * ultimately changes the behavior of the patients heart rate, blood
	 * pressure, respiratory rate, and temperature.
	 * 
	 * @throws IOException
	 */
	public void displayPatientSimulator() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("SimulationDisplay.fxml"));
		loader.load();
		Parent root = loader.getRoot();

		Scene scene = new Scene(root, 720, 300);
		scene.getStylesheets().add(getClass().getResource("SimulationDisplayStyle.css").toExternalForm());

		Stage window = new Stage();
		window.setTitle("Patient Simulator v1.0");
		window.getIcons().add(new Image(getClass().getResourceAsStream("Program Icon.png")));
		window.setScene(scene);
		window.show();

		SimulationDisplayController sim = loader.getController();
		sim.setHR_Field(HR_MID);
		sim.setRR_Field(RR_MID);
		sim.setBP_SysField(BP_SYSTOLICMID);
		sim.setBP_DiaField(BP_DIASTOLICMID);
		sim.setBT_Field(BT_MID);
		Timeline patientSimulationTimeline = new Timeline(new KeyFrame(Duration.millis(1000), e -> {
			HR_Gen.setMidpoint(sim.getHR_Field());
			RR_Gen.setMidpoint(sim.getRR_Field());
			BP_Gen.setSystolicMidpoint(sim.getBP_SysField());
			BP_Gen.setDiastolicMidpoint(sim.getBP_DiaField());
			BT_Gen.setMidpoint(sim.getBT_Field());
		}));
		patientSimulationTimeline.setCycleCount(Timeline.INDEFINITE);
		patientSimulationTimeline.playFromStart();
		window.setOnCloseRequest(e -> {
			patientSimulationTimeline.stop();
		});
	}

	public void displayAlarmSettings() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("AlarmSettings.fxml"));
		loader.load();
		Parent root = loader.getRoot();

		Scene scene = new Scene(root, 750, 225);
		scene.getStylesheets().add(getClass().getResource("AlarmSettingsStyle.css").toExternalForm());

		Stage window = new Stage();
		window.setTitle("Alarm Settings v1.0");
		window.getIcons().add(new Image(getClass().getResourceAsStream("Program Icon.png")));
		window.setScene(scene);

		AlarmSettingsController alarmSettings = loader.getController();
		alarmSettings.setHR_UB(String.valueOf(HR_Alarm.getUpperBound()));
		alarmSettings.setHR_LB(String.valueOf(HR_Alarm.getLowerBound()));

		alarmSettings.setRR_UB(String.valueOf(RR_Alarm.getUpperBound()));
		alarmSettings.setRR_LB(String.valueOf(RR_Alarm.getLowerBound()));

		alarmSettings.setBP_SysUB(String.valueOf(BP_SystolicAlarm.getUpperBound()));
		alarmSettings.setBP_SysLB(String.valueOf(BP_SystolicAlarm.getLowerBound()));
		alarmSettings.setBP_DiaUB(String.valueOf(BP_DiastolicAlarm.getUpperBound()));
		alarmSettings.setBP_DiaLB(String.valueOf(BP_DiastolicAlarm.getLowerBound()));

		alarmSettings.setBT_UB(String.valueOf(BT_Alarm.getUpperBound()));
		alarmSettings.setBT_LB(String.valueOf(BT_Alarm.getLowerBound()));

		window.show();

		Timeline alarmSettingsTimeline = new Timeline(new KeyFrame(Duration.millis(1000), e -> {
			HR_Alarm.setUpperBound(alarmSettings.getHR_UB());
			HR_Alarm.setLowerBound(alarmSettings.getHR_LB());

			RR_Alarm.setUpperBound(alarmSettings.getRR_UB());
			RR_Alarm.setLowerBound(alarmSettings.getRR_LB());

			BP_SystolicAlarm.setUpperBound(alarmSettings.getBP_SysUB());
			BP_SystolicAlarm.setLowerBound(alarmSettings.getBP_SysLB());
			BP_DiastolicAlarm.setUpperBound(alarmSettings.getBP_DiaUB());
			BP_DiastolicAlarm.setLowerBound(alarmSettings.getBP_DiaLB());

			BT_Alarm.setUpperBound(alarmSettings.getBT_UB());
			BT_Alarm.setLowerBound(alarmSettings.getBT_LB());

			System.out.println("RR_UpperBound: " + RR_Alarm.getUpperBound());
		}));
		alarmSettingsTimeline.setCycleCount(Timeline.INDEFINITE);
		alarmSettingsTimeline.playFromStart();
		window.setOnCloseRequest(e -> {
			alarmSettingsTimeline.stop();
		});
	}

	public void displayAboutPage() throws IOException {
		System.out.println("About Page");

		Parent root = FXMLLoader.load(getClass().getResource("AboutPage.fxml"));

		Scene scene = new Scene(root, 600, 400);
		scene.getStylesheets().add(getClass().getResource("AboutPageStyle.css").toExternalForm());

		Stage window = new Stage();
		window.setTitle("About Page v1.0");
		window.getIcons().add(new Image(getClass().getResourceAsStream("Program Icon.png")));
		window.setScene(scene);
		window.show();
	}

	/**
	 * Method used by the "Add Event" button. Adds an entry to the Patient
	 * Agenda
	 */
	public void addAgendaEntryButton() {
		// System.out.println("Add");

		String tempStringTime = hour.getValue() + minute.getValue();
		int tempTime;

		if (am_pm.getValue().equals("AM")) {
			tempTime = Integer.parseInt(tempStringTime);
			// System.out.println(tempTime);

			tempStringTime = hour.getValue() + ":" + minute.getValue() + am_pm.getValue();

			AgendaEvent entry = new AgendaEvent(tempStringTime, eventName.getText());
			agenda.addEvent(entry);
			clearForm();
		} else if (am_pm.getValue().equals("PM")) {
			tempTime = Integer.parseInt(tempStringTime) + 1200;
			// System.out.println(tempTime);

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

	public void openButton() throws FileNotFoundException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File("."));
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));

		File selectedFile = fileChooser.showOpenDialog(null);

		if (selectedFile != null) {
			patient = new Patient(selectedFile);
			setPatientProfile();
			agenda = new Agenda(selectedFile);
			displayAgenda();
		} else {
			// System.out.println("File selection cancelled");
		}
	}

	public void saveButton() throws IOException {
		getPatientProfile();
		if(patient.getName().equals(null))
			patient.savePatientProfile("Person.txt");
		else{
		patient.savePatientProfile(patient.getName() + ".txt");
		}
		agenda.saveAgenda(patient.getName() + ".txt");
		log.saveLog(patient.getName() + ".txt");
	}

	public void exitButton() {
		System.exit(0);
	}

	public void detachPatient() {
		timeline.stop();
		attachPatient.setDisable(false);
		detachPatient.setDisable(true);
		schedule.getItems().clear();
		HR_QuickDataWarning.setText("ALERT: PATIENT DETACHED");
		BP_QuickDataSystolicWarning.setText("ALERT: PATIENT DETACHED");
		BP_QuickDataDiastolicWarning.setText("ALERT: PATIENT DETACHED");
		RR_QuickDataWarning.setText("ALERT: PATIENT DETACHED");
		BT_QuickDataWarning.setText("ALERT: PATIENT DETACHED");

		clearPatientProfile();
	}

	public void attachPatient() {
		HR_QuickDataWarning.setText("");
		BP_QuickDataSystolicWarning.setText("");
		BP_QuickDataDiastolicWarning.setText("");
		RR_QuickDataWarning.setText("");
		BT_QuickDataWarning.setText("");

		HR_Series = new XYChart.Series<>();
		RR_Series = new XYChart.Series<>();
		BP_SystolicSeries = new XYChart.Series<>();
		BP_DiastolicSeries = new XYChart.Series<>();
		BT_Series = new XYChart.Series<>();

		HR_Data = FXCollections.observableArrayList();
		RR_Data = FXCollections.observableArrayList();
		BP_Data = FXCollections.observableArrayList();
		BT_Data = FXCollections.observableArrayList();
		attachPatient.setDisable(true);
		detachPatient.setDisable(false);
		chartInit();
		timelineInit();
	}

	private void timelineInit() {
		timeline = new Timeline(new KeyFrame(Duration.millis(1000), e -> {
			secondsPassed.set(secondsPassed.add(1).get());
			// System.out.println("Seconds Passed: " + secondsPassed.get());
			vitalSign.setHeartRate(HR_Gen.generate());
			bpObj = BP_Gen.generate();
			vitalSign.setSystolicBloodPressure((int) bpObj.getSystolic());
			vitalSign.setDiastolicBloodPressure((int) bpObj.getDiastolic());
			vitalSign.setRespiratoryRate(RR_Gen.generate());
			vitalSign.setBodyTemperature(BT_Gen.generate());
			calendar = Calendar.getInstance();
			vitalSign.setTimeStamp(timeformat.format(calendar.getTime()));

			updateHeartRate(secondsPassed.get(), vitalSign.getHeartRate());
			updateBloodPressure(secondsPassed.get(), vitalSign.getSystolicBloodPressure(),
					vitalSign.getDiastolicBloodPressure());
			updateRespiratoryRate(secondsPassed.get(), vitalSign.getRespiratoryRate());
			updateTemperature(secondsPassed.get(), vitalSign.getBodyTemperature());

			// System.out.println(vitalSign.getTimeStamp());
			// System.out.println(vitalSign.toString());
			log.addLogEntry(vitalSign);
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.playFromStart();

	}

	private void getPatientProfile() {

		patient.setName(patientName.getText());
		patient.setAge(patientAge.getText());
		patient.setHeight(patientHeight.getText());
		patient.setWeight(patientWeight.getText());
		patient.setBloodType(patientBloodType.getText());
	}

	private void setPatientProfile() {

		clearPatientProfile();
		patientName.setText(patient.getName());
		patientAge.setText(patient.getAge());
		patientHeight.setText(patient.getHeight());
		patientWeight.setText(patient.getWeight());
		patientBloodType.setText(patient.getBloodType());
	}

	private void clearPatientProfile() {
		patientName.clear();
		patientAge.clear();
		patientHeight.clear();
		patientWeight.clear();
		patientBloodType.clear();
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
				// System.out.println("OK index is :" +
				// agenda.getAgenda().indexOf(newValue));
			}
		});
	}

	private void updateHeartRate(int second, double heartRate) {
		int pastUpper = 0;
		int pastLower = 0;

		// Checks Current Value if past bounds and Displays it to Quick
		// Data---------------------------------------
		if (heartRate >= HR_Alarm.getUpperBound())
			HR_QuickDataWarning.setText("WARNING: CURRENT HEART RATE PAST UPPER BOUNDS");
		else if (heartRate >= HR_Alarm.getUpperBound())
			HR_QuickDataWarning.setText("WARNING: CURRENT HEART RATE PAST LOWER BOUNDS");
		else
			HR_QuickDataWarning.setText("");

		// Checks Current Value if past bounds and Displays it to Quick
		// Data---------------------------------------

		// If more that 5 points are higher than Upper Bound then Heart
		// Attack-----------------------------------------------
		for (int i = 0; i <= HR_Series.getData().size() - 1; i++) {
			if (HR_Series.getData().get(i).getYValue().doubleValue() > HR_Alarm.getUpperBound())
				pastUpper++;
			if (HR_Series.getData().get(i).getYValue().doubleValue() < HR_Alarm.getLowerBound())
				pastLower++;
		}

		if (pastUpper > 5) {
			// System.out.println("HEART ATTACK");
			HR_QuickDataWarning.setText("ALERT: HEART ATTACK");
		}
		if (pastLower > 5) {
			// System.out.println("BRADYCARDIA");
			HR_QuickDataWarning.setText("ALERT: BRADYCARDIA");
		}
		// If more that 5 points are higher than Upper Bound then Heart
		// Attack-----------------------------------------------
		HR_QuickDataCurrent.setText("Quick Data: [Current Heart Rate = " + heartRate + " (BPM)]");
		HR_Series.getData().add(new XYChart.Data<Number, Number>(second, heartRate));
		if (HR_Series.getData().size() > 10)
			HR_Series.getData().remove(0);

	}

	private void updateRespiratoryRate(int second, double breathRate) {
		int pastUpper = 0;
		int pastLower = 0;
		if (breathRate >= RR_Alarm.getUpperBound())
			RR_QuickDataWarning.setText("WARNING: CURRENT RESPIRATORY RATE PAST UPPER BOUNDS");
		else if (breathRate >= RR_Alarm.getUpperBound())
			RR_QuickDataWarning.setText("WARNING: CURRENT RESPIRATORY RATE PAST LOWER BOUNDS");
		else
			RR_QuickDataWarning.setText("");

		for (int i = 0; i <= RR_Series.getData().size() - 1; i++) {
			if (RR_Series.getData().get(i).getYValue().doubleValue() > RR_Alarm.getUpperBound())
				pastUpper++;
			if (RR_Series.getData().get(i).getYValue().doubleValue() < RR_Alarm.getLowerBound())
				pastLower++;
		}

		if (pastUpper > 5) {
			// System.out.println("HYPERVENTALATION");
			RR_QuickDataWarning.setText("ALERT: HYPERVENTALATION");
		}
		if (pastLower > 5) {
			// System.out.println("BRADYPNEA");
			RR_QuickDataWarning.setText("ALERT: BRADYCARDIA");
		}

		RR_QuickDataCurrent.setText("Quick Data: [Current Respiration Rate = " + breathRate + " (RPM)]");
		RR_Series.getData().add(new XYChart.Data<Number, Number>(second, breathRate));
		if (RR_Series.getData().size() > 10)
			RR_Series.getData().remove(0);
	}

	private void updateBloodPressure(int second, int BP_Systolic, int BP_Diastolic) {
		int pastUpper_S = 0;
		int pastLower_S = 0;
		int pastUpper_D = 0;
		int pastLower_D = 0;

		if (BP_Systolic >= BP_SystolicAlarm.getUpperBound()) // sys too high
			BP_QuickDataSystolicWarning.setText("WARNING: SYSTOLIC PRESSURE PAST UPPER BOUND");
		else if (BP_Systolic <= BP_SystolicAlarm.getLowerBound()) // sys too low
			BP_QuickDataSystolicWarning.setText("WARNING: SYSTOLIC PRESSURE PAST LOWER BOUND");
		else{
			BP_QuickDataSystolicWarning.setText("");
		}
		if (BP_Diastolic >= BP_DiastolicAlarm.getUpperBound()) // dia too
																	// high
			BP_QuickDataDiastolicWarning.setText("WARNING: DIASTOLIC PRESSURE PAST UPPER BOUND");
		else if (BP_Diastolic <= BP_DiastolicAlarm.getLowerBound()) // dia too
																	// low
			BP_QuickDataDiastolicWarning.setText("WARNING: DIASTOLIC PRESSURE PAST LOWER BOUND");
		else{
			BP_QuickDataDiastolicWarning.setText("");	
		}

		for (int i = 0; i <= BP_SystolicSeries.getData().size() - 1; i++) {
			if (BP_SystolicSeries.getData().get(i).getYValue().doubleValue() > BP_SystolicAlarm.getUpperBound())
				pastUpper_S++;
			if (BP_SystolicSeries.getData().get(i).getYValue().doubleValue() < BP_SystolicAlarm.getLowerBound())
				pastLower_S++;
			if (BP_DiastolicSeries.getData().get(i).getYValue().doubleValue() > BP_DiastolicAlarm.getUpperBound())
				pastUpper_D++;
			if (BP_DiastolicSeries.getData().get(i).getYValue().doubleValue() < BP_DiastolicAlarm.getLowerBound())
				pastLower_D++;
		}
		System.out.println("pastUpper_S: " + pastUpper_S);
		System.out.println("pastLower_S: " + pastLower_S);
		System.out.println("pastUpper_D: " + pastUpper_D);
		System.out.println("pastLower_D: " + pastLower_D);
		if (pastUpper_S > 5) {
			// System.out.println("ISOLATED SYSTOLIC HYPERTENSION");
			BP_QuickDataSystolicWarning.setText("ALERT: ISOLATED SYSTOLIC HYPERTENSION");
		}
		if (pastLower_S > 5) {
			// System.out.println("ISOLATED SYSTOLIC HYPOTENSION");
			BP_QuickDataSystolicWarning.setText("ALERT:ISOLATED SYSTOLIC HYPOTENSION");
		}
		if (pastUpper_D > 5) {
			// System.out.println("ISOLATED DIASTOLIC HYPERTENSION");
			BP_QuickDataDiastolicWarning.setText("ALERT: ISOLATED DIASTOLIC HYPERTENSION");
		}
		if (pastLower_D > 5) {
			// System.out.println("ISOLATED DIASTOLIC HYPOTENSION");
			BP_QuickDataDiastolicWarning.setText("ALERT: ISOLATED DIASTOLIC HYPOTENSION");
		}
		

		BP_QuickDataCurrent
				.setText("Quick Data: [Current Blood Pressure = " + BP_Systolic + "/" + BP_Diastolic + " (mm Hg)]");

		BP_SystolicSeries.getData().add(new XYChart.Data<Number, Number>(second, BP_Systolic));
		if (BP_SystolicSeries.getData().size() > 10)
			BP_SystolicSeries.getData().remove(0);
		BP_DiastolicSeries.getData().add(new XYChart.Data<Number, Number>(second, BP_Diastolic));
		if (BP_DiastolicSeries.getData().size() > 10)
			BP_DiastolicSeries.getData().remove(0);
	}

	private void updateTemperature(int second, double temp) {
		int pastUpper = 0;
		int pastLower = 0;
		if (temp >= BT_Alarm.getUpperBound())
			BT_QuickDataWarning.setText("WARNING: CURRENT BODY TEMPERATURE PAST UPPER BOUNDS");
		else if (temp >= BT_Alarm.getUpperBound())
			BT_QuickDataWarning.setText("WARNING: CURRENT BODY TEMPERATURE PAST LOWER BOUNDS");
		else
			BT_QuickDataWarning.setText("");

		for (int i = 0; i <= BT_Series.getData().size() - 1; i++) {
			if (BT_Series.getData().get(i).getYValue().doubleValue() > BT_Alarm.getUpperBound())
				pastUpper++;
			if (BT_Series.getData().get(i).getYValue().doubleValue() < BT_Alarm.getLowerBound())
				pastLower++;
		}

		if (pastUpper > 5) {
			// System.out.println("HYPERTHERMIA");
			RR_QuickDataWarning.setText("ALERT: HYPERTHERMIA");
		}
		if (pastLower > 5) {
			// System.out.println("HYPOTHERMIA");
			RR_QuickDataWarning.setText("ALERT: HYPOTHERMIA");
		}

		BT_QuickDataCurrent.setText("Quick Data: [Current Body Temperature = " + temp + " (°C)]");
		BT_Series.getData().add(new XYChart.Data<Number, Number>(second, temp));
		if (BT_Series.getData().size() > 10)
			BT_Series.getData().remove(0);

	}

}
