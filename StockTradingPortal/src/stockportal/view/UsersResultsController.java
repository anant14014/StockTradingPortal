package stockportal.view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import stockportal.MainApp;
import stockportal.model.User;

public class UsersResultsController {
	
	@FXML
	private Button backButton;
	
	@FXML
	private TableView<User> userTable;
	@FXML
	private TableColumn<User, String> nameColumn;
	@FXML
	private TableColumn<User, Integer> valueColumn;
	
	private MainApp mainApp;
	
	@FXML
    void initialize() {
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		valueColumn.setCellValueFactory(cellData -> cellData.getValue().valueProperty().asObject());
    }
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void showResults(ObservableList<User> results){
		userTable.setItems(results);
	}
	
	public void handleBack() {
		mainApp.showUsersFilter();
	}
	
}