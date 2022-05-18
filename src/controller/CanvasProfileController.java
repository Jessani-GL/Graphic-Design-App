package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;
import model.Model;
import model.User;

public class CanvasProfileController {
	@FXML
	private TextField username;
	@FXML
	private TextField password;
	@FXML
	private Button createUser;
	@FXML
	private Button close;
	@FXML
	private Label status;
	@FXML 
	private ImageView changeImage;
	
	private Stage stage;
	private Stage parentStage;
	private Model model;
	
	public CanvasProfileController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}

	@FXML
	public void initialize() {
//		createUser.setOnAction(event -> {
//			try {
//				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Canvas.fxml"));
//				
//				// Customize controller instance
//				Callback<Class<?>, Object> controllerFactory = param -> {
//					return new CanvasController(stage, model);
//				};
//
//				loader.setControllerFactory(controllerFactory);
//				GridPane root = loader.load();
//				
//				CanvasController canvasController = loader.getController();
//				canvasController.showStage(root);
//				
//				
//				
//				stage.close();
//			} catch (IOException e) {
//				
//			}
//			
//		});
		createUser.setOnAction(event -> {
			if (!username.getText().isEmpty() && !password.getText().isEmpty()) {
				User user;
				try {
					user = model.getUserDao().createUser(username.getText(), password.getText());
					if (user != null) {
						status.setText("Created " + user.getUsername());
						status.setTextFill(Color.GREEN);
					} else {
						status.setText("Cannot create user");
						status.setTextFill(Color.RED);
					}
				} catch (SQLException e) {
					status.setText(e.getMessage());
					status.setTextFill(Color.RED);
				}
				
			} else {
				status.setText("Empty username or password");
				status.setTextFill(Color.RED);
			}
		});

		close.setOnAction(event -> {
			stage.close();
			parentStage.show();
		});
		
		
	}
	
	@FXML
	private void changeProfileImage() {
		System.out.println("Choose Image");
		
		FileChooser fileChooser = new FileChooser();
		
		fileChooser.setSelectedExtensionFilter(new ExtensionFilter("images", "*.jpeg", "*.jpg", "*.png"));
		
		File selectedFile = fileChooser.showOpenDialog(stage);
		
		try {
			FileInputStream fileInputStream = new FileInputStream(selectedFile);
			changeImage.setImage(new Image(fileInputStream));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			status.setTextFill(Color.RED);
		}
	}
	
	public void showStage(GridPane root) {
		Scene scene = new Scene(root, 600, 400);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("reeeeeeeeee");
		stage.show();
	}
}
