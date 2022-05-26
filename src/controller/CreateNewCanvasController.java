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

public class CreateNewCanvasController {

	@FXML
	private Button okBtn;
	@FXML
	private Button cancel;
	@FXML
	private TextField textHeight;
	@FXML
	private TextField textWidth;
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

		okBtn.setOnAction(event -> {

//			try {
//				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SmartCanvas2.fxml"));
//				
//				// Customize controller instance
//				Callback<Class<?>, Object> controllerFactory = param -> {
//					return new CanvasController2(stage, model);
//				};
//				
//				loader.setControllerFactory(controllerFactory);
//				GridPane canvasStage = loader.load();
//				
//				CanvasController2 canvasController = loader.getController();
//				canvasController.showStage(canvasStage);
//				
//				stage.close();
//				System.out.print("print");
//			} catch (IOException e) {
//				System.out.print(e);
//			}
			Node node = (Node) event.getSource();
			this.stage = (Stage) node.getScene().getWindow();
			String widthTextInfo = textWidth.getText();
			String heightTextInfo = textHeight.getText();
			
			double canvasWidth = Double.parseDouble(widthTextInfo);
			double cavnasHeight = Double.parseDouble(heightTextInfo);
//			
			System.out.println(widthTextInfo);
			
			NewCanvasHolder holder = NewCanvasHolder.getInstance();
			
			holder.setHeight(cavnasHeight);
			holder.setWidth(canvasWidth);
//		
//			newCanvasPane.getChildren().removeAll();
//			newCanvasPane.getChildren().setAll(smartCanvasPane);
			
			stage.close();
			parentStage.show();
			
		});
		cancel.setOnAction(event -> {
			stage.close();
			parentStage.show();
		});

	}
	
//	public double processWidth() {
////		String widthTextInfo = widthText.getText();
////		String heightTextInfo = heightText.getText();
//		double canvasWidth = 0;
//		double heightWidth = 0;
//		
//		if (!widthTextInfo.isEmpty() && heightTextInfo.isEmpty()) {
//			resultLabel.setText("Where is your numbers?");
//		} 
//		else {
//			
//			try {
//				canvasWidth = Double.parseDouble(widthTextInfo);
//				heightWidth = Double.parseDouble(heightTextInfo);
//				if(canvasWidth <= 0 && heightWidth <= 0) {
//					resultLabel.setText("You must enter a positive number.");
//				} else {
//					resultLabel.setText("Value width result: " + canvasWidth + "and " + heightWidth);
//				}
//			} catch (NumberFormatException e) {
//				resultLabel.setText("You must enter a valid number");	
//			}
//		}
//		System.out.println("User clicked Ok");
//		return canvasWidth;
//	}
//	
//
//	public double processHeight() {
////		String widthTextInfo = widthText.getText();
////		String heightTextInfo = heightText.getText();
//		double canvasWidth = 0;
//		double heightWidth = 0;
//		
//		if (!widthTextInfo.isEmpty() && heightTextInfo.isEmpty()) {
//			resultLabel.setText("Where are your numbers?");
//		} 
//		else {
//			
//			try {
//				canvasWidth = Double.parseDouble(widthTextInfo);
//				heightWidth = Double.parseDouble(heightTextInfo);
//				if(canvasWidth <= 0 && heightWidth <= 0) {
//					resultLabel.setText("You must enter a positive number.");
//				} else {
//					resultLabel.setText("Value width result: " + canvasWidth + "and " + heightWidth);
//				}
//			} catch (NumberFormatException e) {
//				resultLabel.setText("You must enter a valid number");	
//			}
//		}
//		System.out.println("User clicked Ok");
//		return heightWidth;
//	}

	public void showStage(DialogPane newCanvas) {
		Scene scene = new Scene(newCanvas, 479, 290);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Make new canvas");
		stage.showAndWait();
//		stage.show();
	}
}
