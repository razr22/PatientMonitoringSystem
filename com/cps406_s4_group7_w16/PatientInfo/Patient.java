package com.cps406_s4_group7_w16.PatientInfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Patient{

private String name;
private String age;
private String height;
private String weight;
private String bloodType;


	public Patient(){
		this.setName("");
		this.setAge("");
		this.setHeight("");
		this.setWeight("");
		this.setBloodType("");
	}

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
		
		this.name = in.nextLine();
		this.age = in.nextLine();
		this.height = in.nextLine();
		this.weight = in.nextLine();
		this.bloodType = in.nextLine();
		in.close();
	}
	
	
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
}