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
	
	// Variables for buttons
	@FXML
	private Button okBtn;
	@FXML
	private Button cancel;
	// Variable to change profile image.
	@FXML 
	private ImageView profileImage;
	// Variables to change name.
	@FXML 
	private TextField firstName;
	@FXML 
	private TextField lastName;
	
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

//		String fName = firstName.getText();
//		String lName = lastName.getText();
		
		// Changes profile picture
		profileImage.setOnMouseClicked(event -> {
			System.out.println("Choose Image");
			
			FileChooser fileChooser = new FileChooser();
			
			fileChooser.setSelectedExtensionFilter(new ExtensionFilter("images", "*.jpeg", "*.jpg", "*.png"));
			
			File selectedFile = fileChooser.showOpenDialog(stage);
			
			try {
				FileInputStream fileInputStream = new FileInputStream(selectedFile);
				profileImage.setImage(new Image(fileInputStream));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.print(e.getMessage());
			}
			
			
		});

		// User is taken back to main page (Smart Canvas)
		okBtn.setOnAction(event -> {
			// image change is transfered to canvas
			profileImage.setImage(null); 
			stage.close();
			parentStage.show();
		});
		
		// User is taken back to main page (Smart Canvas)
		cancel.setOnAction(event -> {
			stage.close();
			parentStage.show();
		});
		
		
	}
	
	// Method to retrieve image from the users direct files to select a new profile image. 
	@FXML
	private void changeProfileImage() {
		System.out.println("Choose Image");
		
		FileChooser fileChooser = new FileChooser();
		
		fileChooser.setSelectedExtensionFilter(new ExtensionFilter("images", "*.jpeg", "*.jpg", "*.png"));
		
		// Opens new window to choose a image from their direct computer. 
		File selectedFile = fileChooser.showOpenDialog(stage);
		
		try {
			FileInputStream fileInputStream = new FileInputStream(selectedFile);
			profileImage.setImage(new Image(fileInputStream));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
	}
	
	// Sets stage for current controller.
	public void showStage(GridPane root) {
		Scene scene = new Scene(root, 600, 400);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("profile");
		stage.show();
	}
}
