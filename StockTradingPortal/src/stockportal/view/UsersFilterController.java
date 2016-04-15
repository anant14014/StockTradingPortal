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
	private TextField valueToField;
	@FXML
	private TextField valueFromField;
	
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
		ObservableList<User> users = User.findAll();
		mainApp.showUsersResults(users);
	}
	
	public void handleBack() {
		mainApp.showQueryHome();
	}
	
	
	
}