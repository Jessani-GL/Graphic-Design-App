package main;

import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Model;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;

import controller.LoginController;

public class Main extends Application {
	private Model model;

	@Override
	public void init() {
		model = new Model();
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			model.setup();
			// Loads login page
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));
			
			// Customize controller instance to login page
			Callback<Class<?>, Object> controllerFactory = param -> {
				return new LoginController(primaryStage, model);
			};

			loader.setControllerFactory(controllerFactory);

			GridPane root = loader.load();

			LoginController loginController = loader.getController();
			loginController.showStage(root);
		} catch (IOException | SQLException | RuntimeException e) {
			Scene scene = new Scene(new Label(e.getMessage()), 587, 470);
			primaryStage.setTitle("There is an error");
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
