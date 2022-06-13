package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import data.NewCanvasHolder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;
import model.Model;
import model.User;

public class CreateNewCanvasController {

	// Buttons to continue to escape window. 
	@FXML
	private Button okBtn;
	@FXML
	private Button cancel;
	// Users text inputs for canvas width and height
	@FXML
	private TextField textHeight;
	@FXML
	private TextField textWidth;
	// Validation message variable.
	@FXML
	private Label resultLabel;
	@FXML
	private DialogPane newCanvasPane;

	private Stage stage;
	private Stage parentStage;
	private Model model;

	public CreateNewCanvasController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}

	@FXML
	public void initialize() {

		// Takes in user input of width and height. The width and height information is used to create
		// a new canvas.
		okBtn.setOnAction(event -> {

			Node node = (Node) event.getSource();
			this.stage = (Stage) node.getScene().getWindow();

			// Instance holder used to save width and height. 
			NewCanvasHolder holder = NewCanvasHolder.getInstance();
			
			String widthTextInfo = textWidth.getText();
			String heightTextInfo = textHeight.getText();
			
			double canvasWidth = 0;
			double canvasHeight = 0;
			
			// If statement for validation of inputs for width and height.
			if (!widthTextInfo.isEmpty() && heightTextInfo.isEmpty()) {
				resultLabel.setText("Where are your numbers?");
			} 
			else {
				
				try {
					canvasWidth = Double.parseDouble(widthTextInfo);
					canvasHeight = Double.parseDouble(heightTextInfo);
					if(canvasWidth <= 0 && canvasHeight <= 0) {
						resultLabel.setText("You must enter a positive number.");
					} else {
						resultLabel.setText("Valid");
						// Saves width and height into a class
						holder.setHeight(canvasHeight);
						holder.setWidth(canvasWidth);
						// User is then taken back to the main page, aka the Smart Canvas. 
						stage.close();
						parentStage.show();
						
					}
				} catch (NumberFormatException e) {
					resultLabel.setText("You must enter a number");
					System.out.print(e.getMessage());
				}
			}
			
			
			
		});
		
		// User is taken back to Smart Canvas
		cancel.setOnAction(event -> {
			stage.close();
			parentStage.show();
		});

	}

	// Method to show the Smart Canvas fxml and set it as the current scene.
	// It is using a dialog pane because this fxml is meant to show a window in front of the main page, 
	// which is the Smart Canvas, and when it is closed, it returns variables onto the Smart Canvas. 
	public void showStage(DialogPane newCanvas) {
		Scene scene = new Scene(newCanvas, 479, 290);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Make new canvas");
		stage.showAndWait();
	}
}
