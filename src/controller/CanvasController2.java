package controller;

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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;
import model.Model;
import model.User;

public class CanvasController2 {
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

	private Pane addNewPane = new Pane(); // Adds a canvas onto the program
	
	

	private LoginController loginController = new LoginController(stage, model);

	public CanvasController2(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}
//	public void stage (Stage primaryStage) {
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SmartCanvas2.fxml"));
//		try {
//			stage.close();
//			gridPane2 = loader.load();
//
//			Scene newScene1 = new Scene(gridPane2, 1140, 738);
//
//			Pane canvas2 = new Pane();
//			canvas2.setStyle("-fx-background-color: black;");
//			canvas2.setPrefSize(200, 200);
//			gridPane2.add(canvas2, 1, 1, 1, 1);
//			primaryStage.setScene(newScene1);
//			primaryStage.show();
//
//		} catch (IOException e) {
//
//			System.out.println(e);
//			e.printStackTrace();
//
//		}
//	}
//	public void stage (Stage primaryStage) {
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SmartCanvas2.fxml"));
//		try {
//			stage.close();
//			gridPane2 = loader.load();
//
//			Scene newScene1 = new Scene(gridPane2, 1140, 738);
//
//			Pane canvas2 = new Pane();
//			canvas2.setStyle("-fx-background-color: black;");
//			canvas2.setPrefSize(200, 200);
//			gridPane2.add(canvas2, 1, 1, 1, 1);
//			primaryStage.setScene(newScene1);
//			primaryStage.show();
//
//		} catch (IOException e) {
//
//			System.out.println(e);
//			e.printStackTrace();
//
//		}
//	}

	@FXML
	public void initialize() {
		Circle circle = new Circle();
		circle.setCenterX(350);
		circle.setCenterY(350);
		circle.setFill(Color.YELLOW);
		

//		System.out.println("test");
//
//		Scene newScene1 = new Scene(gridPane2, 1140, 738);
//		//
//		Pane canvas2 = new Pane();
//		canvas2.setStyle("-fx-background-color: black;");
//		canvas2.setPrefSize(200, 200);
//		gridPane2.add(canvas2, 1, 1, 1, 1);
//		stage.setScene(newScene1);
//		stage.show();
//		
//		smartCanvasPane.setCenter(canvas2);
//		System.out.print("Hello");

		profileBtn.setOnAction(event -> {
			try {
				FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/view/Profile.fxml"));

				// Customize controller instance
				Callback<Class<?>, Object> controllerFactory = param -> {
					return new CanvasProfileController(stage, model);
				};

				loader1.setControllerFactory(controllerFactory);
				GridPane profileStage = loader1.load();

				CanvasProfileController canvasController = loader1.getController();
				canvasController.showStage(profileStage);

				stage.close();

				System.out.println("bruh");

//				Scene scene = new Scene(profileStage);
//				stage.setScene(scene);
//				stage.show();
				System.out.println("yellowwwww");
			} catch (IOException e) {
//				message.setText(e.getMessage());
				System.out.println("yea nah");
				System.out.println(e.getMessage());
			}
		});

		dropNewCanvas.setOnAction(event -> {
			System.out.println("hello");
			
//			double canvasWidth = Double.parseDouble(Data.width);
//			double heightWidth = Double.parseDouble(Data.height);
		
			
//			Pane canvas2 = new Pane();
//			canvas2.setStyle("-fx-background-color: black;");
//			canvas2.setPrefSize(canvasWidth, heightWidth);
//			smartCanvasPane.setCenter(canvas2);
//			System.out.println("test");
		});

		logout.setOnAction(event -> {
			stage.close();
			parentStage.show();
		});

	}

//	@FXML
	public void newCanvas(ActionEvent Event) {

		System.out.println("hello");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/createNewCanvas.fxml"));

			// Customize controller instance
			Callback<Class<?>, Object> controllerFactory = param -> {
				return new CreateNewCanvasController(stage, model);
			};

			loader.setControllerFactory(controllerFactory);
			GridPane newCanvas = loader.load();

			CreateNewCanvasController createNewCanvasController = loader.getController();
//			createNewCanvasController.showStage(newCanvas);

			stage.close();

			System.out.println("test");

//			Scene scene = new Scene(profileStage);
//			stage.setScene(scene);
//			stage.show();
			System.out.println("test");
		} catch (IOException e) {
//			message.setText(e.getMessage());
			System.out.println("test no");
			System.out.println(e.getMessage());
		}

	}
	private Parent root;
	private Scene scene;
	public void printCanvas(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/createNewCanvas.fxml"));	
		root = loader.load();	
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		CreateNewCanvasController scene2Controller = loader.getController();
	}
	
	@FXML
	public void addText(ActionEvent Event) {

		TextArea text = new TextArea("Drag me!");

		System.out.println("test");
		

	}

	@FXML
	public void addRect(ActionEvent Event) {

		Rectangle rectangle = new Rectangle();
		rectangle.setX(100);
		rectangle.setY(100);
		rectangle.setWidth(100);
		rectangle.setHeight(100);
		rectangle.setStroke(Color.RED);
		rectangle.setOpacity(10);
		rectangle.setFill(Color.AQUAMARINE);
		rectangle.setStrokeWidth(1);
		System.out.println("test");

	}

	@FXML
	public void addCircle(ActionEvent Event) {

		Circle circle = new Circle();
		circle.setCenterX(350);
		circle.setCenterY(350);
		circle.setFill(Color.YELLOW);
		System.out.println("test");

	}

	@FXML
	public void addImage(ActionEvent Event) {

		System.out.println("test");

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



	public void showStage(GridPane root) {
		Scene scene = new Scene(root, 1140, 738);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Canvas with new Canvas");
		stage.show();
	}

}
