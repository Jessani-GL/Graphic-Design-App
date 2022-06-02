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
import data.NewCanvasHolder;
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
	// Program
	@FXML
	private BorderPane smartCanvasPane;
	// MENU ITEMS
	@FXML
	private MenuItem dropNewCanvas;
	@FXML
	private MenuItem dropClearCanvas;
	@FXML
	private MenuItem dropSaveAs;
	@FXML
	private MenuItem aboutMenu;
	
	// Tool buttons
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
	
	// Changeable features
	@FXML
	private Label changeUsername;
	@FXML
	private ImageView profilePicture;
	@FXML
	private Slider slider;
	@FXML
	private Label zoomLabel;
	@FXML
	private Label coordinates;
	@FXML
	private Button profileBtn;
	@FXML
	private Button logout;

	// Property Tabs
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

	// Change Text Properties
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

	// Change Rectangle Properties
	@FXML
	private ColorPicker rectBorderColour;
	@FXML
	private TextField rectBorderWidth;
	@FXML
	private ColorPicker rectBgColour;

	// Change Circle Properties
	@FXML
	private ColorPicker circleBorderColour;
	@FXML
	private TextField circleBorderWidth;
	@FXML
	private ColorPicker circleBgColour;

	// Change Image Property
	@FXML
	private Button imgChangePath;

	@FXML
	private ColorPicker modifyCanvasChangeBg;

	@FXML
	private MenuItem deleteElement;

	// Add new Canvas
	private Pane canvas = new Pane();

	// Coordinates
	private double startX;
	private double startY;

	// Zoom in and zoom out
	private int zoomPercentage;
	FileChooser fileChooser = new FileChooser();

	Text text;
	Rectangle rectangle;
	Circle circle;
	ImageView addImage;

	// Text Property Variables
	private String[] fontFamilyList = { "Arial", "Monospace", "Times New Roman", "Gill Sans", "Verdana",
			"Serif", "San Serif" };

	// DATA
	private UserInfoHolder userInfoHolder = UserInfoHolder.getInstance();
	private NewCanvasHolder canvasHolder = NewCanvasHolder.getInstance();
	private SignupHolder signupHolder = SignupHolder.getInstance();
	private TextPropertiesHolder textHolder = TextPropertiesHolder.getInstance();
	private User user;

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
		dropClearCanvas.setDisable(true);
		dropSaveAs.setDisable(true);

		fontChoice.getItems().addAll(fontFamilyList);
		fontChoice.getSelectionModel().select(0);
		changeTextInput.setText("Text");
		changeFontSize.setText("11");

		User user;
		user = model.getCurrentUser();
//		changeUsername = new Label();
		System.out.println(user.getFirstName());
		changeUsername.setText(user.getFirstName());
		
		// ZOOM IN AND OUT FEATURE
//		zoomInAndOut();

//		changeUsername.setText(holder.getUsername());
//		Image profileImage = convertProfileImage(signupHolder.getProfileImg());
//		Image defaultProfileImage = convertProfileImage(signupHolder.getDefaultprofileImg());
//		profilePicture.setImage(defaultProfileImage);
//		/////////
//		user = model.getCurrentUser();
//		Image profileImage = convertProfileImage(user.getProfileImage());
//		profilePicture.setImage(profileImage);
//		
///////////s
//		
////		changeUsername.setText(userInfoHolder.getFirstName() + " " + userInfoHolder.getLastName());
///////////
//		Model model = new Model();
//		changeUsername = new Label();
///////////
//		
//		
//////		user = model.getUserDao().getUser(name.getText(), password.getText());
////////////////////
//		changeUsername.setText(model.getCurrentUser().getFirstName());
/////////

		// PROFILE BTN SETTINGS
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
			}
		});

		// ADD NEW CANVAS
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

				// Get width and height information from another file for the new canvas
				addANewCanvas(canvasHolder.getWidth(), canvasHolder.getHeight());
				
				dropClearCanvas.setDisable(false);
				dropSaveAs.setDisable(false);
				
				textBtn.setDisable(false);
				rectBtn.setDisable(false);
				circleBtn.setDisable(false);
				imageBtn.setDisable(false);
				canvasBtn.setDisable(false);

				System.out.println("yellow");
			} catch (IOException e) {
				System.out.println("yea nah");
				System.out.println(e.getMessage());
			}
		});

		// CLEAR CANVAS
		dropClearCanvas.setOnAction(event -> {
//			addANewCanvas(canvasHolder.getHeight(), canvasHolder.getWidth());
			canvas.getChildren().removeAll(text, rectangle, circle, addImage);
			canvas.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		});
		fileChooser.setSelectedExtensionFilter(new ExtensionFilter("images", "*.jpeg", "*.jpg", "*.png"));
//		dropClearCanvas.setOnAction(new EventHandler<ActionEvent>() {
//			
//	         public void handle(ActionEvent event) {
//	             //Opening a dialog box
//	             fileChooser.showSaveDialog(stage);
//	          }
//		});

		// SAVE AS
		dropSaveAs.setOnAction(e -> {
			try {
				save();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//			saveAs();
//			File file = fileChooser.showSaveDialog(new Stage());
//			if (file != null) {
//				saveAs();
//			}
		});

		// ABOUT PAGE
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
				System.out.println("yea nah");
				System.out.println(e.getMessage());
			}
		});

		// LOG OUT
		logout.setOnAction(event -> {
			stage.close();
			parentStage.show();
		});

		// ZOOM IN AND OUT
		slider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldNumber, Number newNumber) {

				zoomPercentage = (int) slider.getValue();
				zoomLabel.setText("Zoom: " + Integer.toString(zoomPercentage) + "%");

			}
		});

	}

	public void addANewCanvas(Double width, Double height) {
		canvas = new Pane();
		canvas.setStyle("-fx-background-color: white;");
		canvas.setMaxHeight(height);
		canvas.setMaxWidth(width);
		canvas.setEffect(new DropShadow());
		smartCanvasPane.setCenter(canvas);
	}

	public static Image convertProfileImage(byte[] img) {

		InputStream inputStream = new ByteArrayInputStream(img);
		Image profilePic = new Image(inputStream);
		return profilePic;
	}

	// CHANGE THIS CODE TO LOOK LIKE MINE
	private static void addPersistenceDelegatesTo(Encoder encoder) {
		encoder.setPersistenceDelegate(Font.class, new DefaultPersistenceDelegate(new String[] { "name", "size" }));
		encoder.setPersistenceDelegate(Color.class,
				new DefaultPersistenceDelegate(new String[] { "red", "green", "blue", "opacity" }));
		encoder.setPersistenceDelegate(LinearGradient.class, new DefaultPersistenceDelegate(
				new String[] { "startX", "startY", "endX", "endY", "proportional", "cycleMethod", "stops" }));
		encoder.setPersistenceDelegate(RadialGradient.class, new DefaultPersistenceDelegate(new String[] { "focusAngle",
				"focusDistance", "centerX", "centerY", "radius", "proportional", "cycleMethod", "stops" }));
	}

	private static final java.nio.file.Path SAVE_FILE_LOCATION = Paths.get(System.getProperty("user.home"),
			"shapes.xml");

	public void save() throws IOException {
		try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(Files.newOutputStream(SAVE_FILE_LOCATION)))) {

			encoder.setExceptionListener(e -> {
				throw new RuntimeException(e);
			});

			addPersistenceDelegatesTo(encoder);

			encoder.writeObject(canvas.getChildren().toArray(new Node[0]));
		}
	}

	public void zoomInAndOut() {
//		smartCanvasPane.getCenter().translateZProperty().bind(slider.valueProperty());
//		smartCanvasPane.translateZProperty().bind(slider.valueProperty());
		canvas.translateZProperty().set(canvas.getTranslateZ());
	}

	private FlowPane flow;

	@FXML
	public void addText(ActionEvent Event) {

		textVbox.setVisible(true);
		rectVbox.setVisible(false);
		circleVbox.setVisible(false);
		imageVbox.setVisible(false);
		modifyCanvasVbox.setVisible(false);

		
		text = new Text();
		text.setFont(Font.font("Arial", FontPosture.REGULAR, 11));
		flow = new FlowPane(text);
		text.setText("Text");
		text.setX(50);
		text.setY(50);
//		text.setOnMouseEntered(e -> {
////			text.setStyle("-fx-background-color: black;");
//			text.setStroke(Color.BLUE);
//			
//	        // other things you need to do when the mouse hovers....
//	    });

//		text.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent e) {
//            	text.setStroke(Color.BLUE);
//            }
//        });

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

		changeTextProperties();

		canvas.getChildren().add(flow);
//		canvas.getChildren().add(redBorder);
		
		canvas.getChildren().add(text);
		canvas.getChildren().forEach(this::makeDraggable);

	}

	
	public void changeTextProperties() {
		
		changeTextInput.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				String input = changeTextInput.getText();
				System.out.println(input);
				text.setText(input);

			}
		});

		fontChoice.setOnAction(event -> {

			String fontChoose = fontChoice.getValue();
			text.setStyle("-fx-font-family: " + fontChoose + ";");
			textHolder.setfFamily(fontChoose);

		});

		changeFontSize.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				
				int fontSize = Integer.parseInt(changeFontSize.getText());
				System.out.println(fontSize);
				text.setFont(Font.font(fontSize));
				textHolder.setfSize(fontSize);
			}
		});

		textColourChoice.setOnAction(event -> {
			Color colour = textColourChoice.getValue();
			text.setFill(colour);
		});

		textBold.setOnAction(event -> {
//			text.setStyle("-fx-font-weight:" + FontWeight.BOLD+ ";");
			
			text.setFont(Font.font(textHolder.getfFamily(), FontWeight.EXTRA_BOLD, textHolder.getfSize()));
		});

		textItalics.setOnAction(event -> {
			text.setFont(Font.font(textHolder.getfFamily(), FontPosture.ITALIC, textHolder.getfSize()));
//			text.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
		});

		textAlignLeft.setOnAction(event -> {
//			text.setTextAlignment(TextAlignment.LEFT);
			TextFlow text_flow = new TextFlow();
			text_flow.getChildren().add(text);
			canvas.getChildren().add(text_flow);
			text_flow.setTextAlignment(TextAlignment.LEFT);

		});

		textAlignMiddle.setOnAction(event -> {
			TextFlow text_flow = new TextFlow();
			text_flow.getChildren().add(text);
//			canvas.getChildren().add(text_flow);
			text_flow.setTextAlignment(TextAlignment.CENTER);
			VBox vbox = new VBox(text_flow);
			  
            // set alignment of vbox
            vbox.setAlignment(Pos.CENTER);
            canvas.getChildren().add(vbox);
//			text.setTextAlignment(TextAlignment.CENTER);
			 
		});

		textAlignRight.setOnAction(event -> {
//			text.setTextAlignment(TextAlignment.RIGHT);
			TextFlow text_flow = new TextFlow();
			text_flow.getChildren().add(text);
			canvas.getChildren().add(text_flow);
			text_flow.setTextAlignment(TextAlignment.RIGHT);
		});

//		Rectangle textBorder = new Rectangle(0, 0, Color.TRANSPARENT);
//		
//		textBorderColour.setOnAction(event -> {
//			Color colour = textBorderColour.getValue();
////			Rectangle textBorder = new Rectangle(0, 0, Color.TRANSPARENT);
//			textBorder.setStroke(colour);
//			textBorder.setManaged(false);
//			text.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
//
//				@Override
//				public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
//					textBorder.setLayoutX(text.getBoundsInParent().getMinX());
//					textBorder.setLayoutY(text.getBoundsInParent().getMinY());
//					textBorder.setWidth(text.getBoundsInParent().getWidth() + 5);
//					textBorder.setHeight(text.getBoundsInParent().getHeight() + 5);
//
//				}
//
//			});
//
//			canvas.getChildren().add(textBorder);
//			canvas.getChildren().forEach(this::makeDraggable);
//		});

		textBorderColour.setOnAction(event -> {
			Color colour = textBorderColour.getValue();
			text.setStroke(colour);
		});

		textBorderWidth.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				String width = textBorderWidth.getText();
				int borderWidth = Integer.parseInt(width);
				text.setStrokeWidth(borderWidth);
			}
		});

//		textBackground.setOnAction(event -> {
//			Color colour = textBackground.getValue();
//
//			int r = ((int) Math.round(colour.getRed() * 255)) << 24;
//			int g = ((int) Math.round(colour.getGreen() * 255)) << 16;
//			int b1 = ((int) Math.round(colour.getBlue() * 255)) << 8;
//			int a = ((int) Math.round(colour.getOpacity() * 255));
//			String hexColour = String.format("#%08X", (r + g + b1 + a));
////
////			System.out.println(hexColour);
////
//////			text.setStyle("-fx-background-color: "+hexColour.toString()+";");
//////			text.setStyle("-fx-background-color: "+hexColour+";"); //
////			text.setStyle("-fx-background-color: #fdfdfd;");
//
//			final Bounds out = flow.getBoundsInLocal();
//			final StringBuilder sbColors = new StringBuilder();
//			final StringBuilder sbInsets = new StringBuilder();
//			AtomicInteger cont = new AtomicInteger();
//			flow.getChildrenUnmodifiable().forEach(n -> {
//				sbColors.append("hsb(").append((((double) cont.get()) / ((double) flow.getChildren().size())) * 360d)
//						.append(", 60%, 90%)");
//				Bounds b = ((Text) n).getBoundsInParent();
//				sbInsets.append(b.getMinY()).append(" ");
//				sbInsets.append(Math.min(stage.getWidth(), out.getMaxX()) - b.getMaxX()).append(" ");
//				sbInsets.append(Math.min(stage.getHeight(), out.getMaxY()) - b.getMaxY()).append(" ");
//				sbInsets.append(b.getMinX());
//				if (cont.getAndIncrement() < flow.getChildren().size() - 1) {
//					sbColors.append(", ");
//					sbInsets.append(", ");
//				}
//			});
//			flow.setStyle("-fx-background-color: " + hexColour + ";");
//			
//			
//		});

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

		deleteElement.setOnAction(event -> {
			canvas.getChildren().remove(text);
		});

	}

	@FXML
	public void addRect(ActionEvent Event) {

		textVbox.setVisible(false);
		rectVbox.setVisible(true);
		circleVbox.setVisible(false);
		imageVbox.setVisible(false);
		modifyCanvasVbox.setVisible(false);

		rectangle = new Rectangle();
		rectangle.setX(100);
		rectangle.setY(100);
		rectangle.setWidth(100);
		rectangle.setHeight(100);
		rectangle.setOpacity(10);
		rectangle.setFill(Color.AQUAMARINE);
//		rectangle.setStrokeWidth(1);
		changeRectProperties();

		canvas.getChildren().add(rectangle);
		canvas.getChildren().forEach(this::makeDraggable);

	}

	public void changeRectProperties() {

		rectBorderColour.setOnAction(event -> {
			Color colour = rectBorderColour.getValue();
			rectangle.setStroke(colour);
		});

		rectBorderWidth.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				String width = rectBorderWidth.getText();
				int borderWidth = Integer.parseInt(width);
				rectangle.setStrokeWidth(borderWidth);
			}
		});

		rectBgColour.setOnAction(event -> {
			Color colour = rectBgColour.getValue();
			rectangle.setFill(colour);
		});

		deleteElement.setOnAction(event -> {
			canvas.getChildren().remove(rectangle);
		});
	}

	@FXML
	public void addCircle(ActionEvent Event) {
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
		canvas.getChildren().forEach(this::makeDraggable);

//		System.out.print(circle.translateYProperty());

	}

	public void changeCircleProperties() {
		circleBorderColour.setOnAction(event -> {
			Color colour = circleBorderColour.getValue();
			circle.setStroke(colour);
		});

		circleBorderWidth.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				String width = circleBorderWidth.getText();
				int borderWidth = Integer.parseInt(width);
				circle.setStrokeWidth(borderWidth);
			}
		});

		circleBgColour.setOnAction(event -> {
			Color colour = circleBgColour.getValue();
			circle.setFill(colour);
		});

		deleteElement.setOnAction(event -> {
			canvas.getChildren().remove(circle);
		});

	}

	@FXML
	public void addImage(ActionEvent Event) {
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

		deleteElement.setOnAction(event -> {
			canvas.getChildren().remove(addImage);
		});
	}

	@FXML
	public void modifyCanvas(ActionEvent Event) {

		textVbox.setVisible(false);
		rectVbox.setVisible(false);
		circleVbox.setVisible(false);
		imageVbox.setVisible(false);
		modifyCanvasVbox.setVisible(true);

//		canvas = new Pane();
//		Scene newScene1 = new Scene(canvas);
//		canvas.setStyle("-fx-background-color: gray;");
////		canvas2.setPrefSize(200, 200);
//		canvas.setMaxHeight(500);
//		canvas.setMaxWidth(500);
//		smartCanvasPane.setCenter(canvas);

		modifyCanvasChangeBg.setOnAction(event -> {
			Color colour = modifyCanvasChangeBg.getValue();
			canvas.setBackground(new Background(new BackgroundFill(colour, CornerRadii.EMPTY, Insets.EMPTY)));
			
		});

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

	public void makeDraggable(Node node) {

		node.setOnMousePressed(e -> {
			startX = e.getSceneX() - node.getTranslateX();
			startY = e.getSceneX() - node.getTranslateX();
			double dragPointX = e.getX();
			double dragPointY = e.getY();
			coordinates.setText("x: " + dragPointX + " y: " + dragPointY);
		});

		node.setOnMouseDragged(e -> {
			node.setTranslateX(e.getSceneX() - startX);
			node.setTranslateY(e.getSceneY() + 100 - startY);
			double dragPointX = e.getX();
			double dragPointY = e.getY();
			coordinates.setText("x: " + dragPointX + " y: " + dragPointY);
		});

	}

	public void showStage(BorderPane root) {
		Scene scene = new Scene(root, 1411, 856);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Smart Canvas");
		stage.show();
	}

}
