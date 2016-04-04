package com.cps406_s4_group7_w16.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class SimulationDisplayController {
	@FXML
	TextField HR_Field = new TextField();
	@FXML
	TextField RR_Field = new TextField();
	@FXML
	TextField BP_SysField = new TextField();
	@FXML
	TextField BP_DiaField = new TextField();
	@FXML
	TextField BT_Field = new TextField();
	
	public int getHR_Field() {
		return Integer.parseInt(HR_Field.getText());
	}
	public void setHR_Field(int hR_mid) {
		HR_Field.setText(String.valueOf(hR_mid));;
	}
	
	public int getRR_Field() {
		return Integer.parseInt(RR_Field.getText());
	}
	public void setRR_Field(int rR_mid) {
		RR_Field.setText(String.valueOf(rR_mid));;
	}
	
	public int getBP_SysField() {
		return Integer.parseInt(BP_SysField.getText());
	}
	public void setBP_SysField(int bP_Sysmid) {
		BP_SysField.setText(String.valueOf(bP_Sysmid));;
	}
	public int getBP_DiaField() {
		return Integer.parseInt(BP_DiaField.getText());
	}
	public void setBP_DiaField(int bP_Diamid) {
		BP_DiaField.setText(String.valueOf(bP_Diamid));;
	}
	
	public int getBT_Field() {
		return Integer.parseInt(BT_Field.getText());
	}
	public void setBT_Field(int bT_mid) {
		BT_Field.setText(String.valueOf(bT_mid));;
	}

	public void heartAttack(){
		setHR_Field(110);
		setRR_Field(16);
		setBP_SysField(105);
		setBP_DiaField(80);
		setBT_Field(37);	}
	
	public void highBloodPressure(){
		setHR_Field(110);
		setRR_Field(16);
		setBP_SysField(180);
		setBP_DiaField(110);
		setBT_Field(37);	
	}
	
	public void seizure(){
		setHR_Field(110);
		setRR_Field(11);
		setBP_SysField(60);
		setBP_DiaField(40);
		setBT_Field(32);
	}
	
	public void reset(){
		setHR_Field(80);
		setRR_Field(16);
		setBP_SysField(105);
		setBP_DiaField(80);
		setBT_Field(37);
	}
}
