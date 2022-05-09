package controller;

import java.awt.Rectangle;
import java.io.IOException;
import java.sql.SQLException;
import javafx.scene.control.TextArea;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Model;
import model.User;

public class CanvasController {
	@FXML
	private Canvas newCanvas;
	@FXML
	private MenuItem dropNewCanvas;
	@FXML
	private Label changeUsername;
	@FXML
	private Button inputText;
	@FXML
	private Canvas canvas;
	
	@FXML
	private Rectangle rect;
	
	private Stage stage;
	private Stage parentStage;
	private Model model;
	
	public CanvasController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}

	@FXML
	public void initialize() {
		
		
		
//		GridPane pane = new GridPane();
//		pane.setAlignment(Pos.CENTER);
//		pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
//		pane.setHgap(10);
//		pane.setVgap(10);
//
//		// Your code goes here
//		pane.add(new Label("Width:"), 0, 0); // row & column indicies from 0
//		TextField widthTextField = new TextField();
//		pane.add(widthTextField, 1, 0);
//
//		pane.add(new Label("Height:"), 0, 1); // row & column indicies from 0
//		TextField heightTextField = new TextField();
//		pane.add(heightTextField, 1, 1);
//
//		//add the label to display the area result
//		Label resultLabel = new Label();
//		pane.add(resultLabel, 1, 2);
//
//		Button button = new Button("Calculate");
//		pane.add(button, 1, 3);
//		button.setOnAction(actionEvent -> {
//
//			String widthText = widthTextField.getText();
//			String heightText = heightTextField.getText();
//			if (!widthText.isEmpty() && heightText.isEmpty()) {
//				resultLabel.setText("Where is your numbers?");
//			} 
//			else {
//				
//				try {
//					double width = Double.parseDouble(widthText);
//					double height = Double.parseDouble(heightText);
//					if(width <= 0 && height <= 0) {
//						resultLabel.setText("You must enter a positive number.");
//					} else {
//						resultLabel.setText("Value width result: " + width);
//					}
//				} catch (NumberFormatException e) {
//					resultLabel.setText("You must enter a number");	
//				}
//			}
//			System.out.println("User clicked calculate");
//		});
		
	}
	
	@FXML
	public void newCanvas(ActionEvent Event) {
		
			changeUsername.setText("buh");
			System.out.println("button was clicked");
			
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/createNewCanvas.fxml"));
//			
//			// Customize controller instance
//			Callback<Class<?>, Object> controllerFactory = param -> {
//				return new CanvasController(stage, model);
//			};
//
//			loader.setControllerFactory(controllerFactory);
//			GridPane root = null;
//			try {
//				root = loader.load();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			CanvasController canvasController = loader.getController();
//			canvasController.showStage(root);
//			canvas.setVisible(true);
//			
//			canvas.setStyle("-fx-background-color: red");
//			System.out.println("did it show?");
	}
	
	
	@FXML
	public void addText(ActionEvent Event) {
		
		TextArea text = new TextArea("Drag me!");
		
		
		System.out.println("bruh");
	
}
	
	@FXML
	public void addRect(ActionEvent Event) {
		
		rect.setSize(100, 200);
		System.out.println("bruh");
	
}
	
	@FXML
	public void addCircle(ActionEvent Event) {
		
		
		System.out.println("bruh");
	
}

	@FXML
	public void addImage(ActionEvent Event) {
		
		
		System.out.println("bruh");
	
}

	public void showStage(Pane root) {
		Scene scene = new Scene(root, 1140, 738);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Sign up");
		stage.show();
	}

}
