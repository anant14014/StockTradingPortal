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
		ObservableList<Stock> stocks = null;
		if ( (valueFromField.getText().trim().isEmpty() && !valueToField.getText().trim().isEmpty()) || (!valueFromField.getText().trim().isEmpty() && valueToField.getText().trim().isEmpty()) ) {
			validationLabel.setText("Please enter both from and to values");
		}
		else if (valueFromField.getText().trim().isEmpty() && valueToField.getText().trim().isEmpty() && nameField.getText().trim().isEmpty()) {
			stocks = Stock.findAll();
		}
		else if (valueFromField.getText().trim().isEmpty() && valueToField.getText().trim().isEmpty()) {
			stocks = Stock.findByName(nameField.getText());
		}
		else {
			int valueFrom = Integer.parseInt(valueFromField.getText());
			int valueTo = Integer.parseInt(valueToField.getText());
			if (nameField.getText().trim().isEmpty()) {
				stocks = Stock.findByValue(valueFrom, valueTo);
			}
			else {
				stocks = Stock.findByNameAndValue(nameField.getText(), valueFrom, valueTo);
			}
		}
		if (stocks != null) {		
			mainApp.showStocksResults(stocks);
		}
	}
	
	public void handleBack() {
		mainApp.showQueryHome();
	}
	
}
