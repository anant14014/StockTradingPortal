package stockportal.view;

import java.sql.SQLException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import stockportal.MainApp;
import stockportal.model.Account;

public class AccountsFilterController {
	
	@FXML
	private TextField accountNumberField;
	@FXML
	private TextField typeField;
	@FXML
	private TextField customerIdField;
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
		ObservableList<Account> accounts = null;
		
		String accountNumber = accountNumberField.getText();
		String type = typeField.getText();
		String customerId = customerIdField.getText();
		String balanceFromString = balanceFromField.getText();
		String balanceToString = balanceToField.getText();
		
		int sum = 0;
		if (!accountNumber.isEmpty()) {
			accounts = Account.findByAccountNumber(Integer.parseInt(accountNumber));
			sum++;
		}
		if (!type.isEmpty()) {
			accounts = Account.findByType(type);
			sum++;
		}
		if (!customerId.isEmpty()) {
			accounts = Account.findByCustomerId(Integer.parseInt(customerId));
			sum++;
		}
		if (!balanceFromString.isEmpty() && !balanceToString.isEmpty()) {
			accounts = Account.findByBalance(Integer.parseInt(balanceFromString), Integer.parseInt(balanceToString));
			sum++;
		}
		if (sum > 1) {
			validationLabel.setText("Please enter only 1 filter!");
		}
		if (sum == 0) {
			accounts = Account.findAll();
		}
		if (accounts != null) {
			mainApp.showAccountsResults(accounts);
		}
	}
	
	public void handleBack() {
		mainApp.showQueryHome();
	}
	
	
	
}