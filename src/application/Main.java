package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	@Override
	public void init() throws Exception {
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			// Create a new FXMLLoader instance
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("loginWindow.fxml"));
			// Load an object hierarchy from a FXML document
			Parent root = loader.load();

			// Return the controller associated with the root object
//			LoginController loginController = loader.getController();
//			loginController.setStage(primaryStage);
			
			Scene scene = new Scene(root, 500, 300);
			// Get an observable list of string URLs linking to the stylesheets
			// to use with this scene's contents
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setScene(scene);
			// Set the value of the property resizable
			primaryStage.setResizable(false);
			primaryStage.setTitle("Welcome");
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stop() throws Exception {
	}

	public static void main(String[] args) {
		launch(args);
	}
}
