package stockportal.view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import stockportal.MainApp;
import stockportal.model.Listing;

public class ListingsResultsController {
	
	@FXML
	private Button backButton;
	
	@FXML
	private TableView<Listing> listingTable;
	@FXML
	private TableColumn<Listing, String> nameColumn;
	
	private MainApp mainApp;
	
	@FXML
    void initialize() {
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    }
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void showResults(ObservableList<Listing> results){
		listingTable.setItems(results);
	}
	
	public void handleBack() {
		mainApp.showListingsFilter();
	}
	
}