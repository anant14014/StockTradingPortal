package stockportal.view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import stockportal.MainApp;
import stockportal.model.Account;

public class AccountsResultsController {
	
	@FXML
	private Button backButton;
	
	@FXML
	private TableView<Account> accountsTable;
	@FXML
	private TableColumn<Account, Integer> accountNumberColumn;
	@FXML
	private TableColumn<Account, String> typeColumn;
	@FXML
	private TableColumn<Account, Integer> customerIdColumn;
	@FXML
	private TableColumn<Account, Integer> balanceColumn;
	@FXML
	private TableColumn<Account, Hyperlink> linkColumn;
	
	private MainApp mainApp;
	
	@FXML
    void initialize() {
		customerIdColumn.setCellValueFactory(cellData -> cellData.getValue().customerIdProperty().asObject());
		typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
		accountNumberColumn.setCellValueFactory(cellData -> cellData.getValue().accountNumberProperty().asObject());
		balanceColumn.setCellValueFactory(cellData -> cellData.getValue().balanceProperty().asObject());
		linkColumn.setCellValueFactory(cellData -> cellData.getValue().buttonProperty());
    }
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void showResults(ObservableList<Account> results){
		for (Account account : results) {
			account.mainApp = this.mainApp;
		}
		accountsTable.setItems(results);
	}
	
	public void handleBack() {
		mainApp.showAccountsFilter();
	}
	
}