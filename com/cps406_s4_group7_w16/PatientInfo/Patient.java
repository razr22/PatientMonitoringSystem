package com.cps406_s4_group7_w16.PatientInfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Patient{

private String name;
private int age;
private double height;
private double weight;


	public Patient(){
		this.setName("");
		this.setAge(0);
		this.setHeight(0);
		this.setWeight(0);
	}

	public Patient(String name, int age, double height, double weight){

		this.name = name;
		this.age = age;
		this.height = height;
		this.weight = weight;
	}

	/**
	 * Patient constructor that takes a file as input.
	 * @param file input file containing information about a patient. Line by line it contains: name, age , height and weight.
	 * @throws FileNotFoundException
	 */
	public Patient(File file) throws FileNotFoundException{
		
		Scanner in = new Scanner(file);
		
		this.name = in.nextLine();
		this.age = in.nextInt();
		this.height = in.nextDouble();
		this.weight = in.nextDouble();
		in.close();
	}
	
	
	public void writeToFile(String filename) throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		
		//converting to strings
		String ageString =  age + "\n";
		String heightString = height + "\n";
		String weightString = weight + "\n";
		
		writer.write(this.getName() + "\n");
		writer.write(ageString, 0, ageString.length());
		writer.write(heightString, 0, heightString.length());
		writer.write(weightString, 0, weightString.length());
		writer.close();
		
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
}