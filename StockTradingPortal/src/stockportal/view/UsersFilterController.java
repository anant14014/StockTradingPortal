package stockportal.view;

import java.sql.SQLException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import stockportal.MainApp;
import stockportal.model.User;

public class UsersFilterController {
	
	@FXML
	private TextField nameField;
	@FXML
	private TextField idField;
	@FXML
	private TextField emailField;
	@FXML
	private TextField balanceToField;
	@FXML
	private TextField balanceFromField;
	
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
		ObservableList<User> users = null;
		
		String name = nameField.getText();
		String idString = idField.getText();
		String email = emailField.getText();
		String balanceFromString = balanceFromField.getText();
		String balanceToString = balanceToField.getText();
		
		int sum = 0;
		if (!name.isEmpty()) {
			users = User.findByName(name);
			sum++;
		}
		if (!idString.isEmpty()) {
			users = User.findById(Integer.parseInt(idString));
			sum++;
		}
		if (!email.isEmpty()) {
			users = User.findByEmail(email);
			sum++;
		}
		if (!balanceFromString.isEmpty() && !balanceToString.isEmpty()) {
			users = User.findByBalance(Integer.parseInt(balanceFromString), Integer.parseInt(balanceToString));
			sum++;
		}
		if (sum > 1) {
			validationLabel.setText("Please enter only 1 filter!");
		}
		if (sum == 0) {
			users = User.findAll();
		}
		if (users != null) {
			mainApp.showUsersResults(users);
		}
	}
	
	public void handleBack() {
		mainApp.showQueryHome();
	}
	
	
	
}