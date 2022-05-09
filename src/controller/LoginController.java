package controller;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Model;
import model.User;

public class LoginController {
	@FXML
	private TextField name;
	@FXML
	private PasswordField password;
	@FXML
	private Label message;
	@FXML
	private Button login;
	@FXML
	private Button signup;

	private Model model;
	private Stage stage;
	
	
	public LoginController(Stage stage, Model model) {
		this.stage = stage;
		this.model = model;
	}
	
	@FXML
	public void initialize() {		
		login.setOnAction(event -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Canvas.fxml"));
				
				// Customize controller instance
				Callback<Class<?>, Object> controllerFactory = param -> {
					return new CanvasController(stage, model);
				};

				loader.setControllerFactory(controllerFactory);
				Pane root = loader.load();
				
				CanvasController canvasController = loader.getController();
				canvasController.showStage(root);
				
				message.setText("");
				name.clear();
				password.clear();
				
				stage.close();
			} catch (IOException e) {
				message.setText(e.getMessage());
			}});
		
//		login.setOnAction(event -> {
//			if (!name.getText().isEmpty() && !password.getText().isEmpty()) {
//				User user;
//				try {
//					user = model.getUserDao().getUser(name.getText(), password.getText());
//					if (user != null) {
//						model.setCurrentUser(user);
//						message.setText("Login success for " + user.getUsername());
//						message.setTextFill(Color.GREEN);
//
//
//						// Navigate to Smart Canvas
//						navigateToCanvas();
//
//						
//				        
//				        
//					} else {
//						message.setText("Wrong username or password");
//						message.setTextFill(Color.RED);
//					}
//				} catch (SQLException e) {
//					message.setText(e.getMessage());
//					message.setTextFill(Color.RED);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} 
//				
//			} else {
//				message.setText("Empty username or password");
//				message.setTextFill(Color.RED);
//			}
//			name.clear();
//			password.clear();
//		});
		
		signup.setOnAction(event -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SignupView.fxml"));
				
				// Customize controller instance
				Callback<Class<?>, Object> controllerFactory = param -> {
					return new SignupController(stage, model);
				};

				loader.setControllerFactory(controllerFactory);
				VBox root = loader.load();
				
				SignupController signupController = loader.getController();
				signupController.showStage(root);
				
				message.setText("");
				name.clear();
				password.clear();
				
				stage.close();
			} catch (IOException e) {
				message.setText(e.getMessage());
			}});
	}
	
//	public void navigateToCanvas() throws Exception {              
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SmartCanvas.fxml"));
//		
//		// Customize controller instance
//		Callback<Class<?>, Object> controllerFactory = param -> {
//			return new SmartCanvasController(stage, model);
//		};
//
//		loader.setControllerFactory(controllerFactory);
//		Parent root = loader.load();
//		
//		SmartCanvasController smartcanvasController = loader.getController();
//		smartcanvasController.showStage(root);
//		
//		message.setText("");
//		name.clear();
//		password.clear();
//		
//		stage.close();
//	}
	public void showStage(Pane root) {
		Scene scene = new Scene(root, 587, 470);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Welcome");
		stage.show();
	}
}

