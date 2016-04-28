package stockportal.view;

import java.sql.SQLException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import stockportal.MainApp;
import stockportal.model.Listing;



public class ListingsFilterController {
	
	@FXML
	private TextField nameField;
	
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
		ObservableList<Listing> listings = null;
		
		if (nameField.getText().trim().isEmpty()) {
			listings = Listing.findAll();
		}
		else {
			listings = Listing.findByName(nameField.getText());
		}
		if (listings != null) {		
			mainApp.showListingsResults(listings);
		}
	}
	
	public void handleBack() {
		mainApp.showQueryHome();
	}
	
}
