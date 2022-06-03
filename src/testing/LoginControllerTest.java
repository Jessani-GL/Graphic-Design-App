package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import javax.print.DocFlavor.URL;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import controller.LoginController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Model;
import model.User;

class LoginControllerTest {
	
	
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
	
	private static User user;
	
	@Before
	public void setup() {

		// any code you want to set up / create some objects before test
		// each test, c

	}

//	URL location = getClass().getResource("/view/SmartCanvas.fxml");
	@BeforeClass
	public static void runBeforeAllTests() {
		// any code you want to run before all tests
		// NOTE if you don't have any code to run, then you don't have to include these
		// methods

		LoginController loginController = new LoginController(stage, model);
		loginController.initialize();
		
	
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
	public void testLogin() {
		LoginController loginController = new LoginController(stage, model);
		loginController.initialize();
		
		String usernameTest = "jessani";
		String passwordTest = "password";
		name.setText(usernameTest);
		password.setText(passwordTest);
		login.isPressed();
		
//		assertEquals("Temp value retrieved is not the same as the value added to the list", 
//				validTempValue, list.getTemps().get(0).intValue());
		Label label = new Label();
//		label = "Login success for";
		message.setText("Login success for " + user.getUsername());
//		assertEquals(message.setText("Login success for " + user.getUsername()), message);
	}

}
