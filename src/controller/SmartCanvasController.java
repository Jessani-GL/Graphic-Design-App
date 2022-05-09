package controller;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Model;
import model.User;

public class SmartCanvasController {
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
	
	private Stage stage;
	private Stage parentStage;
	private Model model;
	
	public SmartCanvasController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}

	@FXML
	public void initialize() {
//		createUser.setOnAction(event -> {
//			if (!username.getText().isEmpty() && !password.getText().isEmpty()) {
//				User user;
//				try {
//					user = model.getUserDao().createUser(username.getText(), password.getText());
//					if (user != null) {
//						status.setText("Created " + user.getUsername());
//						status.setTextFill(Color.GREEN);
//					} else {
//						status.setText("Cannot create user");
//						status.setTextFill(Color.RED);
//					}
//				} catch (SQLException e) {
//					status.setText(e.getMessage());
//					status.setTextFill(Color.RED);
//				}
//				
//			} else {
//				status.setText("Empty username or password");
//				status.setTextFill(Color.RED);
//			}
//		});
//
//		close.setOnAction(event -> {
//			stage.close();
//			parentStage.show();
//		});
	}
	
	public void showStage(GridPane root) {
		Scene scene = new Scene(root, 925, 530);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Sign up");
		stage.show();
	}
}

//	public void showStage(Parent root) {
//		Scene scene = new Scene(root, 925, 530);
//		stage.setScene(scene);
//		stage.setResizable(false);
//		stage.setTitle("Smart Canvas");
//		stage.show();
//	}
//	
//	
//}
