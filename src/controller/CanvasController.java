package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import data.UserInfoHolder;
import javafx.scene.control.TextArea;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;
import model.Model;
import model.User;

public class CanvasController {
	@FXML
	private BorderPane smartCanvasPane;
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

	@FXML
	private Button logout;
	
//	@FXML 
//	private ImageView profileImage;
//	@FXML
//	private Button profileOkBtn;

	private GridPane gridPane2;

	private Stage stage;
	private Stage parentStage;
	private Model model;

	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));
//	private LoginController loginController1 = new LoginController(stage, model);
	LoginController loginController = loader.getController();
	Pane canvas2 = new Pane();
	public CanvasController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}

	@FXML
	public void initialize() {
		UserInfoHolder holder = UserInfoHolder.getInstance();
		
//		changeUsername.setText(holder.getUsername());
		changeUsername.setText(holder.getFirstName() + " " + holder.getLastName());
		
		profileBtn.setOnAction(event -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Profile.fxml"));

				// Customize controller instance
				Callback<Class<?>, Object> controllerFactory = param -> {
					return new CanvasProfileController(stage, model);
				};

				loader.setControllerFactory(controllerFactory);
				GridPane profileStage = loader.load(); 

				CanvasProfileController canvasController = loader.getController();
				canvasController.showStage(profileStage);

				stage.close();

				System.out.println("bruh");

//				Scene scene = new Scene(profileStage);
//				stage.setScene(scene);
//				stage.show();
				System.out.println("yellow");
			} catch (IOException e) {
//				message.setText(e.getMessage());
				System.out.println("yea nah");
				System.out.println(e.getMessage());
			}});
		
		// add canvas
		dropNewCanvas.setOnAction(event -> {
			System.out.println("hello");
			try {

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/createNewCanvas.fxml"));

				// Customize controller instance
				Callback<Class<?>, Object> controllerFactory = param -> {
					return new CreateNewCanvasController(stage, model);
				};

				loader.setControllerFactory(controllerFactory);
				DialogPane newCanvas = loader.load();

				CreateNewCanvasController createNewCanvasController = loader.getController();
				createNewCanvasController.showStage(newCanvas);

//				stage.close();
				NewCanvasHolder canvasHolder = NewCanvasHolder.getInstance();
			
				// Get width and height information from another file for the new canvas
				addANewCanvas(canvasHolder.getHeight(), canvasHolder.getWidth());
				
				System.out.println("yellow");
			} catch (IOException e) {
				System.out.println("yea nah");
				System.out.println(e.getMessage());
			}
		});
		
		logout.setOnAction(event -> {
			stage.close();
			parentStage.show();
		});
		
	}
	
	public void addANewCanvas(Double width, Double height) {
		canvas2 = new Pane();
		Scene newScene1 = new Scene(canvas2);
		canvas2.setStyle("-fx-background-color: white;");
//		canvas2.setPrefSize(200, 200);
		canvas2.setMaxHeight(height);
		canvas2.setMaxWidth(width);
		canvas2.setEffect(new DropShadow());
		smartCanvasPane.setCenter(canvas2);
	}

//	@FXML
	public void newCanvas(ActionEvent Event) {
	
	}
//	@FXML
	public void newCanvas1(ActionEvent Event) {

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
			System.out.println("User clicked create");
			

			
//			addNewCanvas();

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

	public void addNewCanvas() {
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SmartCanvas.fxml"));
//		try {
//			gridPane2 = loader.load();
//
//			Scene newScene1 = new Scene(gridPane2, 1140, 738);
//
//			Pane canvas2 = new Pane();
//			canvas2.setStyle("-fx-background-color: white;");
//			canvas2.setPrefSize(200, 200);
//			gridPane2.add(canvas2, 1, 1, 1, 1);
//			stage.setScene(newScene1);
//			stage.show();
//
//		} catch (IOException e) {
//
//			System.out.println(e);
//			e.printStackTrace();
//
//		}
	}

	@FXML
	public void addText(ActionEvent Event) {
		
		TextArea textArea = new TextArea("Drag me!");
		
		Text text = new Text();
		text.setText("Hello");
		text.setX(50);
		text.setY(50);
		canvas2.getChildren().add(text);
		canvas2.getChildren().forEach(this::makeDraggable);
		
		System.out.println("bruh");

	}

	@FXML
	public void addRect(ActionEvent Event) {

		Rectangle rectangle = new Rectangle();
		rectangle.setX(100);
		rectangle.setY(100);
		rectangle.setWidth(100);
		rectangle.setHeight(100);
//		rectangle.setStroke(Color.RED);
		rectangle.setOpacity(10);
		rectangle.setFill(Color.AQUAMARINE);
		rectangle.setStrokeWidth(1);
		
		canvas2.getChildren().add(rectangle);
		canvas2.getChildren().forEach(this::makeDraggable);

	}

	@FXML
	public void addCircle(ActionEvent Event) {

		Circle circle = new Circle();
		circle.setCenterX(350);
		circle.setCenterY(350);
		circle.setRadius(50);
		circle.setFill(Color.AQUA);
		
		canvas2.getChildren().add(circle);
		canvas2.getChildren().forEach(this::makeDraggable);
		System.out.println("bruh");
		
	

	}

	private double startX;
	private double startY;
	@FXML
	public void addImage(ActionEvent Event) {

		System.out.println("bruh");

	}
	
	public void makeDraggable(Node node) {
		
		node.setOnMousePressed(e -> {
			startX = e.getSceneX() - node.getTranslateX();
			startY = e.getSceneX() - node.getTranslateX();
		});
		
		node.setOnMouseDragged(e -> {
			node.setTranslateX(e.getSceneX() - startX);
			node.setTranslateY(e.getSceneY() - startY);
		});
		
	}


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
	
	@FXML
	public void modifyCanvas(ActionEvent Event) {


//		Scene newScene1 = new Scene(smartCanvasPane, 1140, 738);
//		//
//		Pane canvas2 = new Pane();
//		canvas2.setStyle("-fx-background-color: black;");
//		canvas2.setPrefSize(200, 200);
//		smartCanvasPane.setCenter(canvas2);
//		stage.setScene(newScene1);
//		

		
		canvas2 = new Pane();
		Scene newScene1 = new Scene(canvas2);
		canvas2.setStyle("-fx-background-color: gray;");
//		canvas2.setPrefSize(200, 200);
		canvas2.setMaxHeight(500);
		canvas2.setMaxWidth(500);
		smartCanvasPane.setCenter(canvas2);
//		Rectangle rectangle = new Rectangle();
//		rectangle.setX(100);
//		rectangle.setY(100);
//		rectangle.setWidth(100);
//		rectangle.setHeight(100);
//		rectangle.setStroke(Color.RED);
//		rectangle.setOpacity(10);
//		rectangle.setFill(Color.AQUAMARINE);
//		rectangle.setStrokeWidth(1);
//		
//		canvas2.getChildren().add(rectangle);
		
//		smartCanvasPane.setCenter(canvas2);
		
		System.out.println("bruh");

	}

	public void showStage(BorderPane root) {
		Scene scene = new Scene(root, 1140, 738);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Smart Canvas");
		stage.show();
	}

}
