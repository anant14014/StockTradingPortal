package stockportal.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import stockportal.MainApp;

public class QueryHomeController {
	
	@FXML
	private Button usersButton;
	@FXML
	private Button accountsButton;
	@FXML
	private Button stocksButton;
	
	private MainApp mainApp;
	
	@FXML
    void initialize() {
    }
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void handleStocks() {
		mainApp.showStockHome();
	}
	public void handleAccounts() {
		mainApp.showAccountsFilter();
	}
	public void handleUsers() {
		mainApp.showUsersFilter();
	}
	
	public void handleListings() {
		mainApp.showListingsFilter();
	}
	
}
