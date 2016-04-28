package stockportal.view;

import java.sql.SQLException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import stockportal.MainApp;
import stockportal.model.Account;
import stockportal.model.Stock;
import stockportal.model.User;

public class SingleAccountController {
	
	@FXML
	private Button backButton;
	
	@FXML
	private Label accountNumberLabel;
	@FXML
	private Label accountTypeLabel;
	@FXML
	private Label accountHolderLabel;
	@FXML
	private Label accountBalanceLabel;
	
	@FXML
	private TableView<Stock> stockTable;
	@FXML
	private TableColumn<Stock, String> nameColumn;
	@FXML
	private TableColumn<Stock, Integer> valueColumn;
	@FXML
	private TableColumn<Stock, Integer> quantityColumn;
	
	private MainApp mainApp;
	
	@FXML
    void initialize() {
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		valueColumn.setCellValueFactory(cellData -> cellData.getValue().valueProperty().asObject());
		quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
    }
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setAccount(Account account) throws SQLException{
		accountNumberLabel.setText(Integer.toString(account.getAccountNumber()));
		accountTypeLabel.setText(account.getType());
		accountBalanceLabel.setText(Integer.toString(account.getBalance()));
		String accountHolder = User.findById(account.getCustomerId()).get(0).getName();
		accountHolderLabel.setText(accountHolder);
		
		ObservableList<Stock> stocks = Stock.findByAccount(account.getAccountNumber());
		stockTable.setItems(stocks);
	}
	
	public void handleBack() {
		// gotta change this
		mainApp.showAccountsFilter();
	}
	
}
