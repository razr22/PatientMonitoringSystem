package application;
	
import javafx.application.Application;
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
	
	@Override
	public void start(Stage primaryStage) {
		try {
			window = primaryStage;
			
			Parent root = FXMLLoader.load(getClass().getResource("MainDisplay.fxml"));
			
			Scene scene = new Scene(root,1080,720);
			scene.getStylesheets().add(getClass().getResource("MainDisplayStyle.css").toExternalForm());
			
			window.setTitle("Patient Monitoring System v1.0");
			window.setScene(scene);
			window.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
