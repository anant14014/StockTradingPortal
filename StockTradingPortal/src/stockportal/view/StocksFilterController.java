package stockportal.view;

import java.sql.SQLException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import stockportal.MainApp;
import stockportal.model.Stock;

public class StocksFilterController {
	
	@FXML
	private TextField nameField;
	@FXML
	private TextField valueToField;
	@FXML
	private TextField valueFromField;
	
	@FXML
	private Button submitButton;
	
	private MainApp mainApp;
	
	@FXML
    void initialize() {
		
    }
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void handleSubmit() throws SQLException {
		ObservableList<Stock> stocks = Stock.findAll();
		mainApp.showStocksResults(stocks);
	}
	
}
