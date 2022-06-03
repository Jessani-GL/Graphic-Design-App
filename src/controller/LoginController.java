package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import data.UserInfoHolder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Model;
import model.User;

public class LoginController {
	// Variables to log in
	@FXML
	private TextField name;
	@FXML
	private PasswordField password;
	// Variable for validation
	@FXML
	private Label message;
	// Variable buttons to login if user has an existing account or to make an acconunt if a user does not
	// have an account
	@FXML
	private Button login;
	@FXML
	private Label signup;
	// Button to close program.
	@FXML
	private Button close;

	private Model model;
	private Stage stage;
	

	public LoginController(Stage stage, Model model) {
		this.stage = stage;
		this.model = model;
	}

	@FXML
	public void initialize() {
		
		login.setOnAction(event -> {
			if (!name.getText().isEmpty() && !password.getText().isEmpty()) {
				User user;
				try {
					user = model.getUserDao().getUser(name.getText(), password.getText());
					if (user != null) {
						model.setCurrentUser(user);
						message.setText("Login success for " + user.getUsername());
						message.setTextFill(Color.GREEN);
						UserInfoHolder holder = UserInfoHolder.getInstance();
						holder.setUsername(user.getUsername());

						// Navigate to Smart Canvas
						try {
							FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SmartCanvas.fxml"));

							// Customize controller instance
							Callback<Class<?>, Object> controllerFactory = param -> {
								return new CanvasController(stage, model);
							};

							loader.setControllerFactory(controllerFactory);
							BorderPane root = loader.load();

							CanvasController canvasController = loader.getController();
							canvasController.showStage(root);

							message.setText("");
							name.clear();
							password.clear();

							stage.close();
						} catch (IOException e) {
							message.setText(e.getMessage());
							System.out.println(e);
						}

					} else {
						message.setText("Wrong username or password");
						message.setTextFill(Color.RED);
						
					}
				} catch (SQLException e) {
					message.setText(e.getMessage());
					message.setTextFill(Color.RED);
					System.out.println(e);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e);
				}

			} else {
				message.setText("Empty username or password");
				message.setTextFill(Color.RED);
			}
			name.clear();
			password.clear();
		});

		// Sign up button that leads to the sign up page/ sign up fxml file. 
		signup.setOnMouseClicked(event -> {
			System.out.println("hello");
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
				System.out.println("hello");
				stage.close();
			} catch (IOException e) {
				message.setText(e.getMessage());
				System.out.println(e);
			}
		});

		close.setOnAction(e -> {
			stage.close();
		});
	}

	public SecretKey decryptUserPassword(int password) {
		KeyGenerator keyGenerator;
		SecretKey key = null;
		try {
			keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(password);
			key = keyGenerator.generateKey();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return key;
	}

	public void showStage(Pane root) {
		Scene scene = new Scene(root, 587, 470);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Welcome");
		stage.show();
	}
}
