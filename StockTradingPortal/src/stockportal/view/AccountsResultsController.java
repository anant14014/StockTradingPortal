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
	private TableView<Account> accountTable;
	@FXML
	private TableColumn<Account, String> nameColumn;
	@FXML
	private TableColumn<Account, Integer> valueColumn;
	
	private MainApp mainApp;
	
	@FXML
    void initialize() {
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		valueColumn.setCellValueFactory(cellData -> cellData.getValue().valueProperty().asObject());
    }
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void showResults(ObservableList<Account> results){
		accountTable.setItems(results);
	}
	
	public void handleBack() {
		mainApp.showAccountsFilter();
	}
	
}