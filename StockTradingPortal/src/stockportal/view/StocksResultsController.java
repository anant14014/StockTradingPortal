package stockportal.view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import stockportal.MainApp;
import stockportal.model.Stock;

public class StocksResultsController {
	
	@FXML
	private Button backButton;
	
	@FXML
	private TableView<Stock> stockTable;
	@FXML
	private TableColumn<Stock, String> nameColumn;
	@FXML
	private TableColumn<Stock, Integer> valueColumn;
	
	private MainApp mainApp;
	
	@FXML
    void initialize() {
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		valueColumn.setCellValueFactory(cellData -> cellData.getValue().valueProperty().asObject());
    }
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void showResults(ObservableList<Stock> results){
		stockTable.setItems(results);
	}
	
	public void handleBack() {
		mainApp.showStocksFilter();
	}
	
}