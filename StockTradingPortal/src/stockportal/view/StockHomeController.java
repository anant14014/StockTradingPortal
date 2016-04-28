package stockportal.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import stockportal.MainApp;

public class StockHomeController {
	
	@FXML
	private Button filterButton;
	@FXML
	private Button addButton;
	@FXML
	private Button updateButton;
	@FXML
	private Button backButton;
	
	private MainApp mainApp;
	
	@FXML
    void initialize() {
    }
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void handleFilter() {
		mainApp.showStocksFilter();
	}
	public void handleAdd() {
		mainApp.showStocksAdd();
	}
	public void handleUpdate() {
		mainApp.showStocksUpdate();
	}
	
	public void handleBack() {
		mainApp.showQueryHome();
	}
	
}
