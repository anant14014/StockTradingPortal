package stockportal.view;

import java.sql.SQLException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import stockportal.MainApp;
import stockportal.model.Stock;

public class UpdateStockController {
	
	@FXML
	private TextField nameField;
	@FXML
	private TextField valueField;
	
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
		int value = 0;
		ObservableList<Stock> stocks = null;
		if ( (nameField.getText().trim().isEmpty() || valueField.getText().trim().isEmpty()) ) {
			validationLabel.setText("Please enter both name and value");
		}
		
		else {
			value = Integer.parseInt(valueField.getText());
			numRowsChanged = Stock.UpdateStock(nameField.getText(), value);
		}
		if (numRowsChanged != 0) {	
			stocks = Stock.findByName(nameField.getText());
			mainApp.showStocksResults(stocks);
		}
	}
	
	public void handleBack() {
		mainApp.showStockHome();
	}
	
}
