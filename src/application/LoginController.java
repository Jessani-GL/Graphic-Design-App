package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	@FXML
	private TextField name;
	@FXML
	private PasswordField password;
	@FXML
	private Label message;
	@FXML
	private Button login;

	private Stage stage;
	
	@FXML
	public void initialize() {
		login.setOnAction(event -> {
			if (!name.getText().isEmpty() && !password.getText().isEmpty()) {
				message.setText("Login success");
			} else {
				message.setText("Empty username or password");
			}
			name.clear();
			password.clear();
		});
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
