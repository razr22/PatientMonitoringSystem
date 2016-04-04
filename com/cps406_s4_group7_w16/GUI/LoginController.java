package com.cps406_s4_group7_w16.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.cps406_s4_group7_w16.Security.Account;
import com.cps406_s4_group7_w16.Security.AccountList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LoginController implements Initializable{

	@FXML
	Button loginButton = new Button();
	@FXML
	TextField loginField = new TextField();
	@FXML
	PasswordField passwordField = new PasswordField();
	
	AccountList accountList = new AccountList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
	@FXML
	public void loginButton(ActionEvent event) throws IOException{
		System.out.println(loginField.getText());
		System.out.println(passwordField.getText());
		String username = loginField.getText();
		String password = passwordField.getText();
		
		if(accountList.isValidUsernameAndPassword(username, password)){
			System.out.println("Valid User!");
			try{
			((Node) (event.getSource())).getScene().getWindow().hide();;;
			
			Parent root = FXMLLoader.load(getClass().getResource("MainDisplay.fxml"));

			Scene scene = new Scene(root, 1080, 720);
			scene.getStylesheets().add(getClass().getResource("MainDisplayStyle.css").toExternalForm());

			Stage window = new Stage();
			window.setTitle("Patient Monitoring System v1.0");
			window.getIcons().add(new Image(getClass().getResourceAsStream("Program Icon.png")));
			window.setScene(scene);
			window.show();
			}catch(Exception e){
				e.printStackTrace();

			}
		}
		else{
			System.out.println("Invalid User");
			loginField.clear();
			passwordField.clear();
			loginField.setPromptText("Enter Correct Username");
			passwordField.setPromptText("Enter Correct Password");
			System.out.println("didn't worked");
		}
		
		
		
	}

}
