package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

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

public class AboutController {
	
	// Variable for button to return back to main scene of Smart canvas. 
	@FXML
	private Button close;
	// Label to display java version. 
	@FXML
	private Label javaVersion;
	@FXML
	private DialogPane About;

	private Stage stage;
	private Stage parentStage;
	private Model model;

	public AboutController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}

	@FXML
	public void initialize() {

		// Code to display the current version of the program. 
		javaVersion.setText("Current JVM version - " + System.getProperty("java.version"));
		
		// Button leading back to Smart Canvas. 
		close.setOnAction(event -> {
			stage.close();
			parentStage.show();
		});

	}

	// DialogPane was used to set this scene because it is visually appealing to have a window above
	// the main page of Smart Canvas. 
	public void showStage(DialogPane newCanvas) {
		Scene scene = new Scene(newCanvas, 479, 290);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Make new canvas");
		stage.showAndWait();
	}
}
