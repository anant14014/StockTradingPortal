package stockportal.view;

import java.sql.SQLException;
import java.util.Random;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import stockportal.MainApp;
import stockportal.model.User;

public class AddUserController {
	
	@FXML
	private TextField nameField;
	@FXML
	private TextField emailField;
	@FXML
	private TextField mobileField;
	@FXML
	private TextField date;
	
	@FXML
	private Label validationLabel;
	
	@FXML
	private Button submitButton;
	@FXML
	private Button backButton;
	
	private MainApp mainApp;
	
	@FXML
    void initialize() {
		
    }
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void handleSubmit() throws SQLException {
		int numRowsChanged = 0;
		int mobile = 0;
		int id = 0;
		ObservableList<User> users = null;
		if ( (nameField.getText().trim().isEmpty() || emailField.getText().trim().isEmpty() || mobileField.getText().trim().isEmpty()) || date == null ) {
			validationLabel.setText("Please enter all the values");
		}
		
		else {
			//mobile = Integer.parseInt(mobileField.getText().trim());
			Random rn = new Random();
			id = rn.nextInt(1000000) + 1;
			numRowsChanged = User.AddUser(nameField.getText(), mobileField.getText(), emailField.getText(), date.getText(), id, "pass");
		}
		if (numRowsChanged != 0) {	
			users = User.findByNameAndID(nameField.getText(), id);
			mainApp.showUsersResults(users);
		}
	}
	
	public void handleBack() {
		mainApp.showUserHome();
	}
	
}
