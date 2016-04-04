package com.cps406_s4_group7_w16.PatientInfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Class that defines a Patient data type. Patient object holds a name, age, height, weight and blood type of a patient being monitored.
 * @author Paul Martins
 *
 */
public class Patient{

private String name;
private String age;
private String height;
private String weight;
private String bloodType;


	/**
	 * Default constructor for Patient class. 
	 * Initializes Patient object to default values.
	 */
	public Patient(){
		this.setName("");
		this.setAge("");
		this.setHeight("");
		this.setWeight("");
		this.setBloodType("");
	}

	/**
	 * Constructor for Patient class.
	 * @param name Name of patient.
	 * @param age	Age of Patient
	 * @param height	Height of Patient
	 * @param weight	Weight of Patient
	 * @param bloodType	Blood type of Patient.
	 */
	public Patient(String name, String age, String height, String weight, String bloodType){

		this.name = name;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.bloodType = bloodType;
	}

	/**
	 * Patient constructor that takes a file as input.
	 * @param file input file containing information about a patient. Line by line it contains: name, age , height and weight.
	 * @throws FileNotFoundException
	 */
	public Patient(File file) throws FileNotFoundException{
		
		Scanner in = new Scanner(file);
		
		//skipping over beginning of file.
		for(int i = 0; i < 4; i++){
			in.nextLine();
		}
		
		//reading in whole lines.
		String nameLine = in.nextLine();
		String ageLine = in.nextLine();
		String heightLine = in.nextLine();
		String weightLine = in.nextLine();
		String bloodTypeLine = in.nextLine();
		
		//extracting values from line.
		this.name  = nameLine.substring(6);
		this.age = ageLine.substring(5);
		this.height = heightLine.substring(8);
		this.weight = weightLine.substring(8);
		this.bloodType = bloodTypeLine.substring(12);
		in.close();
	}
	
	/**
	 * method that saves a Patient's profile to a file.
	 * @param filename file to be written to.
	 * @throws IOException
	 */
	public void savePatientProfile(String filename) throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		
		//converting to strings
		String ageString =  age + "\n";
		String heightString = height + "\n";
		String weightString = weight + "\n";
		
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Calendar calendar = Calendar.getInstance();

		writer.write("Save Time: " + dateformat.format(calendar.getTime())  + "\n\n");
		writer.write("PATIENT PROFILE\n");
		writer.write("---------------------------------------------------\n");
		writer.write("Name: " + this.getName() + "\n");
		writer.write("Age: ");
		writer.write(ageString, 0, ageString.length());
		writer.write("Height: ");
		writer.write(heightString, 0, heightString.length());
		writer.write("Weight: ");
		writer.write(weightString, 0, weightString.length());
		writer.write("Blood Type: " + this.getBloodType() + "\n\n");
		writer.close();
		
	}
	
	/**
	 * Getter for a Patient's name
	 * @return name to be returned.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for a Patient's name.
	 * @param name Name to be added.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for a Patient's age
	 * @return age to be returned.
	 */
	public String getAge() {
		return age;
	}

	/**
	 * Setter for a Patient's age
	 * @param age Age to be added.
	 */
	public void setAge(String age) {
		this.age = age;
	}

	/**
	 * Getter for a Patient's height.
	 * @return height to be returned.
	 */
	public String getHeight() {
		return height;
	}

	/**
	 * Setter for a Patient's height
	 * @param height Height to be added.
	 */
	public void setHeight(String height) {
		this.height = height;
	}

	/**
	 * Getter for a Patient's weight
	 * @return weight to be returned.
	 */
	public String getWeight() {
		return weight;
	}

	/**
	 * Setter for a Patient's weight
	 * @param weight Weight to be returned.
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}

	/**
	 * Getter for a Patient's blood type
	 * @return blood type to be returned.
	 */
	public String getBloodType() {
		return bloodType;
	}

	/**
	 * Setter for a Patient's blood type
	 * @param bloodType blood type to be returned.
	 */
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
}