package stockportal.view;

import java.sql.SQLException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import stockportal.MainApp;
import stockportal.model.Account;

public class AccountsFilterController {
	
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
		ObservableList<Account> accounts = Account.findAll();
		mainApp.showAccountsResults(accounts);
	}
	
	public void handleBack() {
		mainApp.showQueryHome();
	}
	
}