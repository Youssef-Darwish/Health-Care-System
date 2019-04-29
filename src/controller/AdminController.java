package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.ResultSet;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.records.Record;
import model.records.StaffRecord;
import model.users.Admin;

public class AdminController implements Initializable {

	@FXML // fx:id="staffTable"
	private TableView<Record> staffTable;

	@FXML // fx:id="staffid"
	private TableColumn<Record, Integer> staffid;

	@FXML // fx:id="staffname"
	private TableColumn<Record, String> staffname;

	@FXML // fx:id="staffrole"
	private TableColumn<Record, Integer> staffrole;

	@FXML // fx:id="stafftelephone"
	private TableColumn<Record, String> stafftelephone;

	@FXML // fx:id="staffsalary"
	private TableColumn<Record, Double> staffsalary;

	@FXML // fx:id="editStaffButton
	private Button editStaffButton;

	@FXML // fx:id="deleteStaffButton
	private Button deleteStaffButton;

	@FXML // fx:id="adminSearchButton
	private Button adminSearchButton;

	@FXML // fx:id="adminCancelButton
	private Button adminCancelButton;

	@FXML // fx:id="adminSearchTextField
	private TextField adminSearchTextField;

	@FXML // fx:id="adminSearchOptions
	private ComboBox<String> adminSearchOptions;

	public static StaffRecord selectedRecord;

	private Admin admin = new Admin();

	public void buildStaffTable(ResultSet rs) throws SQLException {

		ObservableList<Record> data = FXCollections.observableArrayList();

		while (rs.next()) {
			
			data.add(new StaffRecord(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4), rs.getDouble(5)));
		}

		staffid.setCellValueFactory(new PropertyValueFactory<>("id"));
		staffname.setCellValueFactory(new PropertyValueFactory<>("name"));
		staffrole.setCellValueFactory(new PropertyValueFactory<>("role"));
		stafftelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
		staffsalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

		// staffTable.setItems(null);
		staffTable.setItems(data);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		editStaffButton.disableProperty().bind(Bindings.isEmpty(staffTable.getSelectionModel().getSelectedItems()));
		deleteStaffButton.disableProperty().bind(Bindings.isEmpty(staffTable.getSelectionModel().getSelectedItems()));
		
		ObservableList<String> searchAttributes = FXCollections.observableArrayList("ID", "NAME", "ROLE", "SALARY",
				"TELEPHONE");
		adminSearchOptions.setItems(searchAttributes);
		adminSearchOptions.setValue("ID");

		ResultSet rs = (ResultSet) admin.getAll();

		try {
			this.buildStaffTable(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void show(String uml, ActionEvent event) throws IOException {
		Parent pageParent = FXMLLoader.load(getClass().getResource(uml));
		Scene adminPageScene = new Scene(pageParent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		stage.setScene(adminPageScene);

		stage.show();
	}

	@FXML
	public void editStaffMember(ActionEvent event) throws IOException {
		selectedRecord = (StaffRecord) staffTable.getSelectionModel().getSelectedItem();
		show("/view/EditStaffMember.fxml", event);
	}

	@FXML
	public void deleteStaffMember(ActionEvent event) throws SQLException {
		selectedRecord = (StaffRecord) staffTable.getSelectionModel().getSelectedItem();
		((Admin) LoginController.loggedIn).deleteStaffMember("id", String.valueOf(selectedRecord.getId()));
		this.buildStaffTable(admin.getAll());
	}

	@FXML
	public void addStaffMember(ActionEvent event) throws IOException {
		show("/view/AddStaffMember.fxml", event);
	}

	@FXML
	public void logout(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
		show("/view/Login.fxml", event);
	}

	@FXML
	public void search(ActionEvent event) throws SQLException {

		String key = adminSearchOptions.getValue();
		String value = adminSearchTextField.getText();
		ResultSet rs = admin.searchStaffMember(key, value);
		buildStaffTable((rs));

	}

	@FXML
	public void cancelSearch(ActionEvent e) throws SQLException {
		adminSearchTextField.setText("");
		buildStaffTable(admin.getAll());
	}
	

}
