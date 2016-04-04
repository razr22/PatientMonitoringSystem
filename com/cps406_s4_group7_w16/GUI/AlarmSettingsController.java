package com.cps406_s4_group7_w16.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AlarmSettingsController {
	@FXML
	TextField HR_UB = new TextField();
	@FXML
	TextField HR_LB = new TextField();
	@FXML
	TextField RR_UB = new TextField();
	@FXML
	TextField RR_LB = new TextField();
	@FXML
	TextField BP_SysUB = new TextField();
	@FXML
	TextField BP_SysLB = new TextField();
	@FXML
	TextField BP_DiaUB = new TextField();
	@FXML
	TextField BP_DiaLB = new TextField();
	@FXML
	TextField BT_UB = new TextField();
	@FXML
	TextField BT_LB = new TextField();

	public double getHR_UB() {
		return Double.parseDouble(HR_UB.getText());
	}

	public void setHR_UB(String hR_UB) {
		HR_UB.setText(hR_UB);
	}

	public double getHR_LB() {
		return Double.parseDouble(HR_LB.getText());
	}

	public void setHR_LB(String hR_LB) {
		HR_LB.setText(hR_LB);
	}

	public double getRR_UB() {
		return Double.parseDouble(RR_UB.getText());
	}

	public void setRR_UB(String rR_UB) {
		RR_UB.setText(rR_UB);
	}

	public double getRR_LB() {
		return Double.parseDouble(RR_LB.getText());
	}

	public void setRR_LB(String rR_LB) {
		RR_LB.setText(rR_LB);
	}

	public double getBP_SysUB() {
		return Double.parseDouble(BP_SysUB.getText());
	}

	public void setBP_SysUB(String bP_SysUB) {
		BP_SysUB.setText(bP_SysUB);
	}

	public double getBP_SysLB() {
		return Double.parseDouble(BP_SysLB.getText());
	}

	public void setBP_SysLB(String bP_SysLB) {
		BP_SysLB.setText(bP_SysLB);
	}

	public double getBP_DiaUB() {
		return Double.parseDouble(BP_DiaUB.getText());
	}

	public void setBP_DiaUB(String bP_DiaUB) {
		BP_DiaUB.setText(bP_DiaUB);
	}

	public double getBP_DiaLB() {
		return Double.parseDouble(BP_DiaLB.getText());
	}

	public void setBP_DiaLB(String bP_DiaLB) {
		BP_DiaLB.setText(bP_DiaLB);
	}

	public double getBT_UB() {
		return Double.parseDouble(BT_UB.getText());
	}

	public void setBT_UB(String bT_UB) {
		BT_UB.setText(bT_UB);
	}

	public double getBT_LB() {
		return Double.parseDouble(BT_LB.getText());
	}

	public void setBT_LB(String bT_LB) {
		BT_LB.setText(bT_LB);
	}

	public void HRresetButton() {
		setHR_UB("100.0");
		setHR_LB("60.0");
	}

	public void RRresetButton() {
		setRR_UB("20.0");
		setRR_LB("12.0");
	}

	public void BPresetButton() {
		setBP_SysUB("120.0");
		setBP_SysLB("90.0");
		setBP_DiaUB("90.0");
		setBP_DiaLB("70.0");

	}

	public void BTresetButton() {
		setBT_UB("38.0");
		setBT_LB("36.0");

	}
}
