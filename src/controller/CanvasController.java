package controller;

import java.beans.DefaultPersistenceDelegate;
import java.beans.Encoder;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.scene.transform.Rotate;
import data.NewCanvasHolder;
import data.ShapeDimensions;
import data.SignupHolder;
import data.TextPropertiesHolder;
import data.UserInfoHolder;
import javafx.scene.control.TextField;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;
import model.Model;
import model.User;

public class CanvasController {
	// Main Program
	@FXML
	private BorderPane smartCanvasPane;
	// MENU ITEMS : Contains options for the user to access. Contains 'drop' in the name because it is part of
	// the dropdown menu in the menu bar
	@FXML
	private MenuItem dropNewCanvas;
	@FXML
	private MenuItem dropClearCanvas;
	@FXML
	private MenuItem dropSaveAs;
	@FXML
	private MenuItem aboutMenu;

	// Tool buttons : these variables were named by what they are, such as text and combined with 'Btn', 
	// which is a shortened version of button. It is the buttons assigned on the left part of the Smart canvas, 
	// which clearly illustrate what they are, a button for their designated label. 
	@FXML
	private Button textBtn;
	@FXML
	private Button rectBtn;
	@FXML
	private Button circleBtn;
	@FXML
	private Button imageBtn;
	@FXML
	private Button canvasBtn;

	// Changeable features : These variables are for the profile on the top right of the smart canvas.
	// To display the user and their profile picture, as well as allowing them to edit their profile 
	// and to logout
	@FXML
	private Label changeUsername;
	@FXML
	private ImageView profilePicture;
	@FXML
	private Button profileBtn;
	@FXML
	private Button logout;

	// Variables the zoom in and zoom out feature. It uses a slider and label that is interactable 
	// and displays the percentage value of the zoom in and zoom out.
	@FXML
	private Slider slider; 
	@FXML
	private Label zoomLabel;
	
	// Displays the coordinates of the elements. As well as the size of the element
	@FXML
	private Label coordinates;
	
	// Property Tabs : Displays the property details of each tool that can be used on a canvas (text, shapes etc)
	// It shows editable features for each tool
	@FXML
	private VBox textVbox;
	@FXML
	private VBox rectVbox;
	@FXML
	private VBox circleVbox;
	@FXML
	private VBox imageVbox;
	@FXML
	private VBox modifyCanvasVbox;

	// Change Text Properties : These variables are the editable features that can be applied onto text element/s
	@FXML
	private TextField changeTextInput;
	@FXML
	private ChoiceBox<String> fontChoice;
	@FXML
	private TextField changeFontSize;
	@FXML
	private Button textBold;
	@FXML
	private Button textItalics;
	@FXML
	private ColorPicker textColourChoice;
	@FXML
	private Button textAlignLeft;
	@FXML
	private Button textAlignMiddle;
	@FXML
	private Button textAlignRight;
	@FXML
	private ColorPicker textBorderColour;
	@FXML
	private TextField textBorderWidth;
	@FXML
	private ColorPicker textBackground;
	@FXML
	private Button rotateText;

	// Change Rectangle Properties : These variables are the editable features that can be applied onto rectangle element/s
	@FXML
	private ColorPicker rectBorderColour;
	@FXML
	private TextField rectBorderWidth;
	@FXML
	private ColorPicker rectBgColour;
	// Rectangle Resize
	@FXML
	private TextField rectangleWidth;
	@FXML
	private TextField rectangleHeight;
	@FXML
	private Button rectangleRotate;

	// Change Circle Properties : These variables are the editable features that can be applied onto circle element/s
	@FXML
	private ColorPicker circleBorderColour;
	@FXML
	private TextField circleBorderWidth;
	@FXML
	private ColorPicker circleBgColour;
	@FXML
	private TextField circleRadius;

	// Change Image Properties : These variables are the editable features that can be applied onto image element/s
	@FXML
	private Button imgChangePath;
	@FXML
	private Button imgRotate;
	@FXML
	private TextField imgWidth;
	@FXML
	private TextField imgHeight;

//	Change Canvas Property : This variable is a editable feature that can change the background colour of a canvas.
	@FXML
	private ColorPicker modifyCanvasChangeBg;

	// Element is called deleteElement because it deletes elements. 
	@FXML
	private MenuItem deleteElement;

	// Add new Canvas
	private Pane canvas = new Pane();

	// Coordinates
	private double startX;
	private double startY;
	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");

	// Zoom in and zoom out
	private int zoomPercentage;
	private FileChooser fileChooser = new FileChooser();

	// Contains the elements that are added to the canvas. Was made into a variable to enable 
	// myself to use it globally within this file
	private Text text;
	private Rectangle rectangle;
	private Circle circle;
	private ImageView addImage;

	// Text Property Variables : Contains font family options for changing the font style for text.
	private String[] fontFamilyList = { "Arial", "Monospace", "Times New Roman", "Gill Sans", "Verdana", "Serif",
			"San Serif" };

	// DATA : Contains classes that hold data in order for them to be used in other classes. 
	private UserInfoHolder userInfoHolder = UserInfoHolder.getInstance();
	private NewCanvasHolder canvasHolder = NewCanvasHolder.getInstance();
	private SignupHolder signupHolder = SignupHolder.getInstance();
	private TextPropertiesHolder textHolder = TextPropertiesHolder.getInstance();
	private ShapeDimensions shapeHolder = ShapeDimensions.getInstance();
	private User user;

	// Controller variables
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
		// Disabling users ability to use 'Clear Canvas' and 'Save as' unless they make a canvas
		dropClearCanvas.setDisable(true);
		dropSaveAs.setDisable(true);

		fontChoice.getItems().addAll(fontFamilyList);
		fontChoice.getSelectionModel().select(0);
		changeTextInput.setText("Text");
		changeFontSize.setText("11");

		// ZOOM IN AND OUT FEATURE
//		zoomInAndOut();

		// Attempt to set image from database
//		user = model.getCurrentUser();
//		Image profileImage = convertProfileImage(user.getProfileImage());
//		profilePicture.setImage(profileImage);
		
		// Attempt to set name from database
//		Model model = new Model();
//		changeUsername = new Label();
//		user = model.getUserDao().getUser(name.getText(), password.getText());
//		changeUsername.setText(model.getCurrentUser().getFirstName());
		
//		User user;
//		user = model.getCurrentUser();
//		System.out.println(user.getFirstName());
//		changeUsername.setText(user.getFirstName());

		// Sets the first and last name after signing up and logging in
		changeUsername.setText(userInfoHolder.getFirstName() + " " + userInfoHolder.getLastName());

		// Profile Button : Navigates to the edit profile interface
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
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		});

		// Button to add a new canvas
		dropNewCanvas.setOnAction(event -> {

			try {
				// Navigates to the width and height inputs to create a new canvas with users chosen dimensions
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/createNewCanvas.fxml"));

				// Customize controller instance
				Callback<Class<?>, Object> controllerFactory = param -> {
					return new CreateNewCanvasController(stage, model);
				};

				loader.setControllerFactory(controllerFactory);
				// Used a DialogPane because it is designed to hover over the previous scene and return variables, which is the width and height
				// of the canvas
				DialogPane newCanvas = loader.load();

				// Switch controller to the create new canvas fxml
				CreateNewCanvasController createNewCanvasController = loader.getController();
				createNewCanvasController.showStage(newCanvas);

				// Get width and height information from the create canvas controller in order to create a new canvas
				addANewCanvas(canvasHolder.getWidth(), canvasHolder.getHeight());

				// Enables the dropdown options of'Clear canvas' and 'Save as' drop down options available to use after creating a new canvas.
				dropClearCanvas.setDisable(false);
				dropSaveAs.setDisable(false);

				// Enables the user to use the left tool options, such as adding text, rectangle etc onto the canvas.
				// Is originally disabled because the user needs to create a new canvas before being able to use it.
				textBtn.setDisable(false);
				rectBtn.setDisable(false);
				circleBtn.setDisable(false);
				imageBtn.setDisable(false);
				canvasBtn.setDisable(false);
				
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		});

		// Clear Canvas : Deletes all edits made onto the canvas, and leaves user an empty canvas
		dropClearCanvas.setOnAction(event -> {
			canvas.getChildren().removeAll(text, rectangle, circle, addImage);
			canvas.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		});



		// SAVE AS : Attempt to save canvas as a image.
		dropSaveAs.setOnAction(e -> {
		
//			saveAs();
//			File file = fileChooser.showSaveDialog(new Stage());
//			fileChooser.setSelectedExtensionFilter(new ExtensionFilter("images", "*.jpeg", "*.jpg", "*.png"));
//			if (file != null) {
//				saveAs();
//			}
		});

		// ABOUT PAGE : Navigates to a fxml file and changes controller to that designated fxml file. 
		// The page displays the program version.
		aboutMenu.setOnAction(event -> {
			try {

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/About.fxml"));

				// Customize controller instance
				Callback<Class<?>, Object> controllerFactory = param -> {
					return new AboutController(stage, model);
				};

				loader.setControllerFactory(controllerFactory);
				DialogPane About = loader.load();

				AboutController aboutController = loader.getController();
				aboutController.showStage(About);

			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		});

		// LOG OUT : Logs user out and is taken back to the login/signin page. 
		logout.setOnAction(event -> {
			stage.close();
			parentStage.show();
		});

		// ZOOM IN AND OUT : Attempt to zoom in and out feature. 
		slider.valueProperty().addListener(new ChangeListener<Number>() {
			
			// This method changes the percentage of the zoom in and out when using the slider.
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldNumber, Number newNumber) {

				zoomPercentage = (int) slider.getValue();
				zoomLabel.setText("Zoom: " + Integer.toString(zoomPercentage) + "%");

			}
		});

	}

	// This method creates a new canvas. It creates a pane and adds it to the centre of the borderpane. 
	// It also takes in the users width and height, which is why there is a width and height parameter.
	public void addANewCanvas(Double width, Double height) {
		canvas = new Pane();
		canvas.setStyle("-fx-background-color: white;");
		canvas.setMaxHeight(height);
		canvas.setMaxWidth(width);
		canvas.setEffect(new DropShadow());
		smartCanvasPane.setCenter(canvas);
	}

	// This method converts the blob data from the database into a image variable. Which can be used to display the profile picture.
	public static Image convertProfileImage(byte[] img) {

		InputStream inputStream = new ByteArrayInputStream(img);
		Image profilePic = new Image(inputStream);
		return profilePic;
	}


	// This method is a attempt to create the zoom in and zoom out feature.
	public void zoomInAndOut() {
//		smartCanvasPane.getCenter().translateZProperty().bind(slider.valueProperty());
//		smartCanvasPane.translateZProperty().bind(slider.valueProperty());
		canvas.translateZProperty().set(canvas.getTranslateZ());
	}

	
	// This is a FXML method which is connected to the Text Button. It adds text elements onto the canvas.
	@FXML
	public void addText(ActionEvent Event) {

		// Makes only the text properties visible : The editable options for text. And makes all other editable properties for other elements invisible to avoid clashed visuals
		textVbox.setVisible(true);
		rectVbox.setVisible(false);
		circleVbox.setVisible(false);
		imageVbox.setVisible(false);
		modifyCanvasVbox.setVisible(false);

		text = new Text();
		text.setFont(Font.font("Arial", FontPosture.REGULAR, 11));
		FlowPane flow = new FlowPane(text);
		text.setText("Text");
		text.setX(50);
		text.setY(50);


		// Below is an attempt to create a selection margin and/or background for the text
		final Rectangle redBorder = new Rectangle(0, 0, Color.TRANSPARENT);
		redBorder.setStroke(Color.RED);
		redBorder.setManaged(false);
		text.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {

			@Override
			public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
				redBorder.setLayoutX(text.getBoundsInParent().getMinX());
				redBorder.setLayoutY(text.getBoundsInParent().getMinY());
				redBorder.setWidth(text.getBoundsInParent().getWidth());
				redBorder.setHeight(text.getBoundsInParent().getHeight());

			}

		});

		// Method that enables text properties to be changed and edited
		changeTextProperties();

		// Below is an attempt to create a selection margin and/or background for the text part 2
//		canvas.getChildren().add(flow);
//		canvas.getChildren().add(redBorder);

		// Adds text element/s onto the canvas
		canvas.getChildren().add(text);
		// Makes all text elements draggable
		canvas.getChildren().forEach(this::makeDraggable);

	}

	// Method changes the text properties. It contains logic that allows editable changes to the text.
	public void changeTextProperties() {

		// Changes the text of the text element on key pressed 'Enter'.
		changeTextInput.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				String input = changeTextInput.getText();
				System.out.println(input);
				text.setText(input);

			}
		});

		// Changes font of the text
		fontChoice.setOnAction(event -> {
			String fontChoose = fontChoice.getValue();
			text.setStyle("-fx-font-family: " + fontChoose + ";");
			textHolder.setfFamily(fontChoose);

		});

		// Changes font size of the text on key pressed 'Enter'.
		changeFontSize.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {

				int fontSize = Integer.parseInt(changeFontSize.getText());
				System.out.println(fontSize);
				text.setFont(Font.font(fontSize));
				textHolder.setfSize(fontSize);
			}
		});

		// Changes colour of the text
		textColourChoice.setOnAction(event -> {
			Color colour = textColourChoice.getValue();
			text.setFill(colour);
		});

		// Makes the text bold
		textBold.setOnAction(event -> {
			text.setFont(Font.font(textHolder.getfFamily(), FontWeight.EXTRA_BOLD, textHolder.getfSize()));
		});

		// Makes the text italic
		textItalics.setOnAction(event -> {
			text.setFont(Font.font(textHolder.getfFamily(), FontPosture.ITALIC, textHolder.getfSize()));
		});

		// Button to assign the text to the left
		textAlignLeft.setOnAction(event -> {
			text.setTextAlignment(TextAlignment.LEFT);
//			TextFlow text_flow = new TextFlow();
//			text_flow.getChildren().add(text);
//			canvas.getChildren().add(text_flow);
//			text_flow.setTextAlignment(TextAlignment.LEFT);

		});

//		Button to assign the text to the middle
		textAlignMiddle.setOnAction(event -> {
//			TextFlow text_flow = new TextFlow();
//			text_flow.getChildren().add(text);
//			canvas.getChildren().add(text_flow);
//			text_flow.setTextAlignment(TextAlignment.CENTER);
//			VBox vbox = new VBox(text_flow);
//
//			// set alignment of vbox
//			vbox.setAlignment(Pos.CENTER);
//			canvas.getChildren().add(vbox);
			text.setTextAlignment(TextAlignment.CENTER);

		});

		// Button to assign the text to the right
		textAlignRight.setOnAction(event -> {
//			text.setTextAlignment(TextAlignment.RIGHT);
			TextFlow text_flow = new TextFlow();
			text_flow.getChildren().add(text);
			canvas.getChildren().add(text_flow);
			text_flow.setTextAlignment(TextAlignment.RIGHT);
		});

		// Changes stroke colour of text
		textBorderColour.setOnAction(event -> {
			Color colour = textBorderColour.getValue();
			text.setStroke(colour);
		});

		// Changes width stroke of text on key pressed 'Enter'.
		textBorderWidth.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				String width = textBorderWidth.getText();
				int borderWidth = Integer.parseInt(width);
				text.setStrokeWidth(borderWidth);
			}
		});


		// Attempt to change the text background. It creates a border around the text
		textBackground.setOnAction(event -> {

			Color colour = textBackground.getValue();

			Rectangle textBorder = new Rectangle(0, 0, colour);
			textBorder.setStroke(colour);
			textBorder.setManaged(false);
			text.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {

				@Override
				public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
					textBorder.setLayoutX(text.getBoundsInParent().getMinX());
					textBorder.setLayoutY(text.getBoundsInParent().getMinY());
					textBorder.setWidth(text.getBoundsInParent().getWidth() + 5);
					textBorder.setHeight(text.getBoundsInParent().getHeight() + 5);

				}

			});

			int r = ((int) Math.round(colour.getRed() * 255)) << 24;
			int g = ((int) Math.round(colour.getGreen() * 255)) << 16;
			int b1 = ((int) Math.round(colour.getBlue() * 255)) << 8;
			int a = ((int) Math.round(colour.getOpacity() * 255));
			String hexColour = String.format("#%08X", (r + g + b1 + a));

//			flow.setStyle("-fx-background-color: " + hexColour + ";");
			canvas.getChildren().add(textBorder);
			canvas.getChildren().forEach(this::makeDraggable);

		});

		// Rotates text using a button
		rotateText.setOnAction(event -> {
			Rotate rotate = new Rotate();
			rotate.setAngle(20);
			rotate.setPivotX(50);
			rotate.setPivotY(50);
			text.getTransforms().addAll(rotate);
		});

		// Deletes text element 
		deleteElement.setOnAction(event -> {
			canvas.getChildren().remove(text);
		});

	}

//	This is a FXML method which is connected to the 'Rect' Button, aka Rectangle button. It adds rectangle elements onto the canvas.
	@FXML
	public void addRect(ActionEvent Event) {
//		Makes only the Rectangle properties visible : The editable options for rectangle/s. And makes all other editable properties for other elements invisible to avoid clashed visuals
		textVbox.setVisible(false);
		rectVbox.setVisible(true);
		circleVbox.setVisible(false);
		imageVbox.setVisible(false);
		modifyCanvasVbox.setVisible(false);

		// Creates rectangle element
		rectangle = new Rectangle();
		rectangle.setX(100);
		rectangle.setY(100);
		rectangle.setWidth(100);
		rectangle.setHeight(100);
		rectangle.setOpacity(10);
		rectangle.setFill(Color.AQUAMARINE);
//		rectangle.setStrokeWidth(1);
		changeRectProperties();

		// Adds rectangle element/s to canvas
		canvas.getChildren().add(rectangle);
		canvas.getChildren().forEach(this::makeDraggable);

	}

	// Method changes the rectangle properties, aka 'rect' for short. It contains logic that allows editable changes to the rectangle/s.
	public void changeRectProperties() {

		// Changes rectangle border colour.
		rectBorderColour.setOnAction(event -> {
			Color colour = rectBorderColour.getValue();
			rectangle.setStroke(colour);
		});

		// Changes rectangle border width on key pressed 'Enter'.
		rectBorderWidth.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				String width = rectBorderWidth.getText();
				int borderWidth = Integer.parseInt(width);
				rectangle.setStrokeWidth(borderWidth);
			}
		});

		// Changes background colour (aka bg colour) of rectangle
		rectBgColour.setOnAction(event -> {
			Color colour = rectBgColour.getValue();
			rectangle.setFill(colour);
		});

		// Changes rectangle width on key pressed 'Enter'.
		rectangleWidth.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				String width = rectangleWidth.getText();
				int rectWidth = Integer.parseInt(width);
				rectangle.setWidth(rectWidth);
				shapeHolder.setRectWidth(rectWidth);
			}
		});

		// Changes rectangle height on key pressed 'Enter'.
		rectangleHeight.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				String height = rectangleHeight.getText();
				int rectHeight = Integer.parseInt(height);
				rectangle.setHeight(rectHeight);
				shapeHolder.setRectHeight(rectHeight);
			}
		});

		// Rotates rectangle
		rectangleRotate.setOnAction(event -> {
			Rotate rotate = new Rotate();
			rotate.setAngle(20);

			rotate.setPivotX(150);
			rotate.setPivotY(150);
			rectangle.getTransforms().addAll(rotate);

			System.out.println(rotate.getAngle());
//			rectangle.getTransforms()
//					.add(new Rotate(20,
//							rectangle.getBoundsInParent().getMinX() + (rectangle.getBoundsInLocal().getWidth() / 2),
//							rectangle.getBoundsInParent().getMinX() + (rectangle.getBoundsInLocal().getHeight() / 2)));

		});

		// Deletes rectangle/s element/s.
		deleteElement.setOnAction(event -> {
			canvas.getChildren().remove(rectangle);
		});
	}

//	This is a FXML method which is connected to the Circle Button. It adds circle elements onto the canvas.
	@FXML
	public void addCircle(ActionEvent Event) {
//		Makes only the Circle properties visible : The editable options for circle/s. And makes all other editable properties for other elements invisible to avoid clashed visuals
		textVbox.setVisible(false);
		rectVbox.setVisible(false);
		circleVbox.setVisible(true);
		imageVbox.setVisible(false);
		modifyCanvasVbox.setVisible(false);

		circle = new Circle();
		circle.setCenterX(350);
		circle.setCenterY(350);
		circle.setRadius(50);
		circle.setFill(Color.AQUA);

		changeCircleProperties();

		canvas.getChildren().add(circle);
		canvas.getChildren().forEach(this::makeDraggableC);
		


	}

	// Method changes the circle properties. It contains logic that allows editable changes to the circle/s.
	public void changeCircleProperties() {
		// Changes colour of the border of circle element/s.
		circleBorderColour.setOnAction(event -> {
			Color colour = circleBorderColour.getValue();
			circle.setStroke(colour);
		});

		// Changes border width of circle element/s on key pressed 'Enter'.
		circleBorderWidth.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				String width = circleBorderWidth.getText();
				int borderWidth = Integer.parseInt(width);
				circle.setStrokeWidth(borderWidth);
				
			}
		});

		// Changes background colour (aka bg colour) of circle.
		circleBgColour.setOnAction(event -> {
			Color colour = circleBgColour.getValue();
			circle.setFill(colour);
		});

		// Changes circle radius (aka size of circle) of circle/s on key pressed 'Enter'.
		circleRadius.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				String radius = circleRadius.getText();
				int circleRadius = Integer.parseInt(radius);
				circle.setRadius(circleRadius);
				double w = circle.getLayoutBounds().getWidth();
				double h = circle.getLayoutBounds().getHeight();
				System.out.println(w);
				System.out.println(h);
				shapeHolder.setCircleWidth(w);
				shapeHolder.setCircleHeight(h);
			}
		});

		// Deletes circle element/s.
		deleteElement.setOnAction(event -> {
			canvas.getChildren().remove(circle);
		});

	}
	
//	This is a FXML method which is connected to the Image Button. It adds images onto the canvas.
	@FXML
	public void addImage(ActionEvent Event) {
//		Makes only the Image properties visible : The editable options for images. And makes all other editable properties for other elements invisible to avoid clashed visuals
		textVbox.setVisible(false);
		rectVbox.setVisible(false);
		circleVbox.setVisible(false);
		imageVbox.setVisible(true);
		modifyCanvasVbox.setVisible(false);

		addImage = new ImageView();
		addImage.setX(100);
		addImage.setY(100);
		FileChooser fileChooser = new FileChooser();

		fileChooser.setSelectedExtensionFilter(new ExtensionFilter("images", "*.jpeg", "*.jpg", "*.png"));

		File selectedFile = fileChooser.showOpenDialog(stage);

		try {
			FileInputStream fileInputStream = new FileInputStream(selectedFile);
			addImage.setImage(new Image(fileInputStream));

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}

		changeImageProperties();
		canvas.getChildren().add(addImage);
		canvas.getChildren().forEach(this::makeDraggable);

	}
	
	// Method changes the image properties. It contains logic that allows editable changes to the image/s.
	public void changeImageProperties() {

		imgChangePath.setOnAction(event -> {

			fileChooser.setSelectedExtensionFilter(new ExtensionFilter("images", "*.jpeg", "*.jpg", "*.png"));

			File selectedFile = fileChooser.showOpenDialog(stage);

			try {
				FileInputStream fileInputStream = new FileInputStream(selectedFile);
				addImage.setImage(new Image(fileInputStream));

			} catch (FileNotFoundException e) {
				e.printStackTrace();

			}
		});
		
		// Changes image/s width on key pressed 'Enter'.
		imgWidth.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				String width = imgWidth.getText();
				int imgWidth = Integer.parseInt(width);
				addImage.setFitWidth(imgWidth);
				shapeHolder.setRectWidth(imgWidth);
			}
		});

		// Changes image/s height on key pressed 'Enter'.
		imgHeight.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				String height = imgHeight.getText();
				int imgHeight = Integer.parseInt(height);
				addImage.setFitHeight(imgHeight);
				shapeHolder.setRectHeight(imgHeight);
			}
		});

		imgRotate.setOnAction(event -> {
			Rotate rotate = new Rotate();
			rotate.setAngle(20);
			rotate.setPivotX(200);
			rotate.setPivotY(200);
			addImage.getTransforms().addAll(rotate);
		});

		deleteElement.setOnAction(event -> {
			canvas.getChildren().remove(addImage);
		});
	}

//	This is a FXML method which is connected to the Canvas Button. It modifies/changes the canvas background
	@FXML
	public void modifyCanvas(ActionEvent Event) {
		
//		Makes only the Canvas properties visible : The editable options for the canvas. And makes all other editable properties for other elements invisible to avoid clashed visuals

		textVbox.setVisible(false);
		rectVbox.setVisible(false);
		circleVbox.setVisible(false);
		imageVbox.setVisible(false);
		modifyCanvasVbox.setVisible(true);

		modifyCanvasChangeBg.setOnAction(event -> {
			Color colour = modifyCanvasChangeBg.getValue();
			canvas.setBackground(new Background(new BackgroundFill(colour, CornerRadii.EMPTY, Insets.EMPTY)));

		});


	}

	// This method enables elements to become draggable. It also shows the coordinates of the width and height of an element : rectangle.
	public void makeDraggable(Node node) {

		// When the users mouse clicks on a element, it retrieves the X and Y values. it also prints it as coordinates in the Smart Canvas application
		node.setOnMousePressed(e -> {
			startX = e.getSceneX() - node.getTranslateX();
			startY = e.getSceneX() - node.getTranslateX();
			double dragPointX = e.getX();
			double dragPointY = e.getY();
			coordinates.setText("x: " + DECIMAL_FORMAT.format(dragPointX) + " y: " + DECIMAL_FORMAT.format(dragPointY)
			+ " w: " + shapeHolder.getRectWidth() + " h: " + shapeHolder.getRectHeight());
			
		});

		// Enables user to drag elements and prints X and Y coordinates while being dragged.
		node.setOnMouseDragged(e -> {
			node.setTranslateX(e.getSceneX() - startX);
			node.setTranslateY(e.getSceneY() + 100 - startY);
			double dragPointX = e.getX();
			double dragPointY = e.getY();
			coordinates.setText("x: " + DECIMAL_FORMAT.format(dragPointX) + " y: " + DECIMAL_FORMAT.format(dragPointY)
			+ " w: " + shapeHolder.getRectWidth() + " h: " + shapeHolder.getRectHeight());
		});

	}
	
	// This method enables elements to become draggable, but is creates to also show the width and height of the circle. 
	public void makeDraggableC(Node node) {

		// When the users mouse clicks on a element, it retrieves the X and Y values. it also prints it as coordinates in the Smart Canvas application
		node.setOnMousePressed(e -> {
			startX = e.getSceneX() - node.getTranslateX();
			startY = e.getSceneX() - node.getTranslateX();
			double dragPointX = e.getX();
			double dragPointY = e.getY();
			coordinates.setText("x: " + DECIMAL_FORMAT.format(dragPointX) + " y: " + DECIMAL_FORMAT.format(dragPointY)
			+ " w: " + shapeHolder.getCircleWidth() + " h: " + shapeHolder.getCircleHeight());
			
		});

		// Enables user to drag elements and prints X and Y coordinates while being dragged.
		node.setOnMouseDragged(e -> {
			node.setTranslateX(e.getSceneX() - startX);
			node.setTranslateY(e.getSceneY() + 100 - startY);
			double dragPointX = e.getX();
			double dragPointY = e.getY();
			coordinates.setText("x: " + DECIMAL_FORMAT.format(dragPointX) + " y: " + DECIMAL_FORMAT.format(dragPointY)
			+ " w: " + DECIMAL_FORMAT.format(shapeHolder.getCircleWidth()) + " h: " + DECIMAL_FORMAT.format(shapeHolder.getCircleHeight()));
		});

	}
	
	// Method to show the Smart Canvas fxml and set it as the current scene. 
	public void showStage(BorderPane root) {
		Scene scene = new Scene(root, 1411, 856);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Smart Canvas");
		stage.show();
	}

}
