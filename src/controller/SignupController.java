package controller;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import dao.Database;
import dao.UserDaoImpl;
import data.SignupHolder;
import data.UserInfoHolder;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Model;
import model.User;

public class SignupController {
	// Variables contains user details, which are required for a user account.
	@FXML
	private TextField username;
	@FXML
	private TextField firstName;
	@FXML
	private TextField lastName;
	@FXML
	private TextField password;
	// Variables for buttons on the fxml file/ sign up page. 
	@FXML
	private Button createUser;
	@FXML
	private Button close;
	// Variable used for validation.
	@FXML
	private Label status;
	// Variable for the profile picture option. 
	@FXML
	private ImageView changeImage;
	
	// Class and variable that saves and holds variables for the user details to be displayed in smart canvas. 
	private static SignupHolder signupHolder = SignupHolder.getInstance();
	private static byte[] profileData = null;


	private Stage stage;
	private Stage parentStage;
	private Model model;

	
	public SignupController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}

	@FXML
	public void initialize() throws Exception {

		// Attempt to add image to the database. 
//		// Creating the image view
//		ImageView imageView = new ImageView();
//		// Setting image to the image view
//		changeImage.setImage(image);
//
//		UserDaoImpl  userIm = null;
//		userIm.chooseAndSaveFile(changeImg());
//		convertImgToBlob(changeProfileImage());
//		automaticProfile();
//		System.out.println(profileData.toString());

		// Method and validation to create a new user. 
		createUser.setOnAction(event -> {

			if (!username.getText().isEmpty() && !password.getText().isEmpty()) {
				User user;
				try {
//					encryptString(username.getText()
//					profileData = convertImgToBlob(changeProfileImage());
//					System.out.println(profileData.toString());
					user = model.getUserDao().createUser(username.getText(), password.getText(), firstName.getText(),
							lastName.getText(), profileData);
					if (user != null) {
						status.setText("Created " + user.getUsername());
						status.setTextFill(Color.GREEN);
//						user.setFirstName(firstName.getText());
//						user.setLastName(lastName.getText());
//						model.setCurrentUser(user);

						
						
//						Saves user first and last name to display on Smart Canvas
						UserInfoHolder holder = UserInfoHolder.getInstance();
						holder.setFirstName(user.getFirstName());
						holder.setLastName(user.getLastName());

						stage.close();
						parentStage.show();
					} else {
						status.setText("Cannot create user");
						status.setTextFill(Color.RED);
					}
				} catch (SQLException e) {
					status.setText(e.getMessage());
					status.setTextFill(Color.RED);
				}

			} else {
				status.setText("Username or password is empty");
				status.setTextFill(Color.RED);
			}
		});

		// Closes program
		close.setOnAction(event -> {
			stage.close();
			parentStage.show();
		});

	}

	// hashes password
	public String encryptString(String input) {
		MessageDigest md;
		BigInteger bigInt = null;
		User user = null;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());

			bigInt = new BigInteger(1, messageDigest);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print(e);
		}

		String hashedPassword = bigInt.toString(16);
		user.setPassword(hashedPassword);
		return hashedPassword;

	}

	// Default profile
	public void automaticProfile() {
		BufferedImage defaultPFP = null;
		ByteArrayOutputStream result = null;
		try {
			defaultPFP = ImageIO.read(getClass().getResource("/img/dog.jpeg"));
			result = new ByteArrayOutputStream();
			ImageIO.write(defaultPFP, "jpg", result);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.print(e1);
		}

		byte[] imgInfo;
		imgInfo = result.toByteArray();
		profileData = imgInfo;
//		signupHolder.setDefaultprofileImg(profileData);
	}

	// Convert the file into a blob to put into the data
	public static byte[] convertImgToBlob(File img) {
		BufferedImage bImg;
		ByteArrayOutputStream result = null;
		try {
			bImg = ImageIO.read(img);
			result = new ByteArrayOutputStream();
			ImageIO.write(bImg, "jpg", result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.print(e);
			e.printStackTrace();
		}

		byte[] imgInfo = result.toByteArray();
//		signupHolder.setProfileImg(imgInfo);
		profileData = imgInfo;
		return imgInfo;
	}

	// Adds profile image when adding a new user. 
	@FXML
	private File changeProfileImage() {

		System.out.println("Choose Image");

		FileChooser fileChooser = new FileChooser();

		fileChooser.setSelectedExtensionFilter(new ExtensionFilter("images", "*.jpeg", "*.jpg", "*.png"));

		// Opens a window to access users direct files in order to choose a image for profile.
		File selectedFile = fileChooser.showOpenDialog(stage);

		try {
			FileInputStream fileInputStream = new FileInputStream(selectedFile);
			changeImage.setImage(new Image(fileInputStream));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.print(e);
			status.setTextFill(Color.RED);
		}

		return selectedFile;

	}

	public void showStage(Pane root) {

		Scene scene = new Scene(root, 587, 470);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Sign up");
		stage.show();
	}
}
