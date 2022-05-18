package controller;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import javafx.scene.control.TextArea;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
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
	
	@FXML 
	private Button profileBtn;

//	@FXML 
//	private ImageView profileImage;
//	@FXML
//	private Button profileOkBtn;
	
	private Stage stage;
	private Stage parentStage;
	private Model model;

	private LoginController loginController = new LoginController(stage, model);
	

	public CanvasController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}

	@FXML
	public void initialize() {		
		profileBtn.setOnAction(event -> {
			System.out.println("hello");
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Profile.fxml"));
				
				// Customize controller instance
				Callback<Class<?>, Object> controllerFactory = param -> {
					return new CanvasProfileController(stage, model);
				};

				loader.setControllerFactory(controllerFactory);
				GridPane canvasStage = loader.load();
				
				CanvasProfileController canvasController = loader.getController();
				canvasController.showStage(canvasStage);
				
				stage.close();
				System.out.println("yellow");
			} catch (IOException e) {
//				message.setText(e.getMessage());
				System.out.println("wellow");
			}});

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

		System.out.println("button was clicked");

		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		pane.setHgap(10);
		pane.setVgap(10);

		// Your code goes here
		pane.add(new Label("Width:"), 0, 0); // row & column indicies from 0
		TextField widthTextField = new TextField();
		pane.add(widthTextField, 1, 0);

		pane.add(new Label("Height:"), 0, 1); // row & column indicies from 0
		TextField heightTextField = new TextField();
		pane.add(heightTextField, 1, 1);

		// add the label to display the area result
		Label resultLabel = new Label();
		pane.add(resultLabel, 1, 2);

		Button createCanvas = new Button("Create");
		Button close = new Button("Close");
		pane.add(createCanvas, 1, 2);
		pane.add(close, 0, 2);

		Scene makeNewCanvasScene = new Scene(pane, 300, 200);

		stage.setScene(makeNewCanvasScene);
		stage.show();
		createCanvas.setOnAction(actionEvent -> {

			String widthText = widthTextField.getText();
			String heightText = heightTextField.getText();
			double width = Double.parseDouble(widthText);
			double height = Double.parseDouble(heightText);
			if (!widthText.isEmpty() && heightText.isEmpty()) {
				resultLabel.setText("Where is your numbers?");
			} else {

				try {
					
					if (width <= 0 && height <= 0) {
						resultLabel.setText("You must enter a positive number.");
						
					} else {
						resultLabel.setText("Value width result: " + width);
					}
				} catch (NumberFormatException e) {
					resultLabel.setText("You must enter a number");
				}
			}
			System.out.println("User clicked calculate");
			stage.close();
			GridPane canvasApp = loginController.getCanvasStage();
			
			Pane canvas = new Pane();
	        canvas.setStyle("-fx-background-color: black;");
	        canvas.setPrefSize(width,height);
	        
	        
	        Scene scene = new Scene(canvas, 587, 470);
	        
	        
	        canvasApp.add(canvas, 1, 4);
	        
	        Scene scene1 = new Scene(canvasApp, 1140, 738);
	        stage.setScene(scene1);
	        stage.show();
	      
	      
		});

		close.setOnAction(event -> {
			stage.close();
			loginController.getCanvasStage();
		});
		// get the value from the widthTextField and the heightTextField
		// do the calculation of the area and display the area

		// Need to use gridpane of smart canvas stage. but how
//			GridPane canvasStage = new GridPane();
//			
//			Pane canvas = new Pane();
//	        canvas.setStyle("-fx-background-color: black;");
//	        canvas.setPrefSize(200,200);
//	        Scene scene = new Scene(canvas, 587, 470);
//	        
//	        canvasStage.add(canvas, 1, 4);
//	        Scene scene1 = new Scene(canvasStage, 587, 470);
//	        
//	        stage.setScene(scene1);
//	        stage.show();
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
	
//	@FXML
//	public void profileBtn(ActionEvent Event) {
//
//		try {
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Profile.fxml"));
//			
//			// Customize controller instance
//			Callback<Class<?>, Object> controllerFactory = param -> {
//				return new CanvasProfileController(stage, model);
//			};
//
//			loader.setControllerFactory(controllerFactory);
//			GridPane root = loader.load();
//			
//			CanvasProfileController canvasProfileController = loader.getController();
//			canvasProfileController.showStage(root);
//			
//			stage.close();
//		} catch (IOException e) {
////			message.setText(e.getMessage());
//		}
////		Parent pane;
////		try {
////			pane = FXMLLoader.load(
////			           getClass().getResource("/view/Profile.fxml"));
////			System.out.println("bruh");
////			
////			Scene scene = new Scene(pane);
////			stage.setScene(scene);
////			stage.show();
////			
//////			profileOkBtn.setOnAction(event -> {
//////				System.out.print("bruh");
//////			});
////			
//////			profileImage.setOnMouseClicked(event-> {
//////				System.out.println("Choose Image");
//////				
//////				FileChooser fileChooser = new FileChooser();
//////				
//////				fileChooser.setSelectedExtensionFilter(new ExtensionFilter("images", "*.jpeg", "*.jpg", "*.png"));
//////				
//////				File selectedFile = fileChooser.showOpenDialog(stage);
//////				
//////				try {
//////					FileInputStream fileInputStream = new FileInputStream(selectedFile);
//////					profileImage.setImage(new Image(fileInputStream));
//////				} catch (FileNotFoundException e) {
//////					e.printStackTrace();
////////					status.setTextFill(Color.RED);
//////				}
//////		});
////			
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//
//	}
	
//	@FXML
//	private void changeProfileImage() {
//		
//		
//		System.out.println("Choose Image");
//		
//		FileChooser fileChooser = new FileChooser();
//		
//		fileChooser.setSelectedExtensionFilter(new ExtensionFilter("images", "*.jpeg", "*.jpg", "*.png"));
//		
//		File selectedFile = fileChooser.showOpenDialog(stage);
//		
//		try {
//			FileInputStream fileInputStream = new FileInputStream(selectedFile);
//			profileImage.setImage(new Image(fileInputStream));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
////			status.setTextFill(Color.RED);
//		}
//	}

	public void showStage(GridPane root) {
		Scene scene = new Scene(root, 1140, 738);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Sign up");
		stage.show();
	}
	
	


}
