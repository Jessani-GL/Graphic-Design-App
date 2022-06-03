package testing;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import controller.CanvasController;
import controller.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Model;
import model.User;

class CanvasControllerTest {

	private static Model model;
	private static Stage stage;
	@FXML
	private static TextField name;
	@FXML
	private static PasswordField password;
	@FXML
	private static Label message;
	@FXML
	private static Button login;
	
	
	@Before
	public void setup() {
		
		// any code you want to set up / create some objects before test
		// each test, c
		
		
	}

	@BeforeClass
	public static void runBeforeAllTests() {
		// any code you want to run before all tests
		// NOTE if you don't have any code to run, then you don't have to include these methods

		LoginController loginController = new LoginController(stage, model);
		loginController.initialize();
		
		String usernameTest = "jessani";
		String passwordTest = "password";
		name.setText(usernameTest);
		password.setText(passwordTest);
		login.isPressed();
//		FXMLLoader fxmlLoader = (FXMLLoader) (this.baseBorderPane.getScene().getUserData());
//		IController controller = fxmlLoader.getController();
		
		
//		controller.something();
		
	}
	
	@AfterClass
	public static void runAfterAllTests() {
		// any code you want to run before all tests have finished
		// such as closing ......
	}
	
	@After
	public void tearDown() {
		// some code to execute after a test has finished
		// you do not have to do this if u do not have Before or After class methods 
	}
	
	@Test
	public void checkIfUserIsledToAnotherFile() {
		FXMLLoader fxmlLoader = (FXMLLoader) (this.stage.getScene().getUserData());
//		IController controller = fxmlLoader.getController();
		FXMLLoader loader;
		CanvasController c;
		try {
			loader = new FXMLLoader(getClass().getResource("/view/SmartCanvas.fxml"));
			Parent root = loader.load();
			 c = loader.getController();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CanvasController canvasController = new CanvasController(stage, model);
		
//		assertTrue(canvasController, c);
	}
	

}
