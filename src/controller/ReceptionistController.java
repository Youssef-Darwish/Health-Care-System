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
import model.records.PatientRecord;
import model.records.Record;
import model.users.Receptionist;

public class ReceptionistController implements Initializable {

	@FXML // fx:id="patientsTable"
	private TableView<Record> patientsTable;

	@FXML // fx:id="patientIdColumn"
	private TableColumn<Record, Integer> patientIdColumn;

	@FXML // fx:id="patientNameColumn"
	private TableColumn<Record, String> patientNameColumn;

	@FXML // fx:id="patientGenderColumn"
	private TableColumn<Record, Integer> patientGenderColumn;

	@FXML // fx:id="patientTelephoneColumn"
	private TableColumn<Record, String> patientTelephoneColumn;

	@FXML // fx:id="patientRegisterationColumn" 
	private TableColumn<Record, String> patientRegisterationColumn;

	@FXML // fx:id="editPatientButton
	private Button editPatientButton;

	@FXML // fx:id="addPatientButton
	private Button addPatientButton;

	@FXML // fx:id="deletePatientButton
	private Button deletePatientButton;

	@FXML // fx:id="searchPatientButton
	private Button searchPatientButton;

	@FXML // fx:id="cancelSearchButton
	private Button cancelSearchButton;

	@FXML // fx:id="patientSearchField
	private TextField patientSearchField;

	@FXML // fx:id="patientSearchOptions
	private ComboBox<String> patientSearchOptions;

	public static PatientRecord selectedRecord;

	private Receptionist receptionist = new Receptionist();

	public void buildStaffTable(ResultSet rs) throws SQLException {

		ObservableList<Record> data = FXCollections.observableArrayList();

		// check fields of patient
		while (rs.next()) {

			data.add(new PatientRecord(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5)));
		}
		System.out.println(((PatientRecord)(data.get(0))).getRegistrationDate());
		patientIdColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
		patientNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
		patientGenderColumn.setCellValueFactory(new PropertyValueFactory<>("Gender"));
		patientTelephoneColumn.setCellValueFactory(new PropertyValueFactory<>("Telephone"));
		patientRegisterationColumn.setCellValueFactory(new PropertyValueFactory<>("RegisterationDate"));

		patientsTable.setItems(data);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		editPatientButton.disableProperty()
				.bind(Bindings.isEmpty(patientsTable.getSelectionModel().getSelectedItems()));
		deletePatientButton.disableProperty()
				.bind(Bindings.isEmpty(patientsTable.getSelectionModel().getSelectedItems()));

		ObservableList<String> searchAttributes = FXCollections.observableArrayList("ID", "Name", "Gender", "Telephone",
				"Registeration Date");
		patientSearchOptions.setItems(searchAttributes);
		patientSearchOptions.setValue("ID");

		ResultSet rs = (ResultSet) receptionist.getAllPatients();

		try {
			this.buildStaffTable(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void show(String uml, ActionEvent event) throws IOException {
		Parent pageParent = FXMLLoader.load(getClass().getResource(uml));
		Scene patientPageScene = new Scene(pageParent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		stage.setScene(patientPageScene);

		stage.show();
	}

	@FXML
	public void editPatient(ActionEvent event) throws IOException {
		selectedRecord = (PatientRecord) patientsTable.getSelectionModel().getSelectedItem();
		// EditPatient
		show("/view/EditPatient.fxml", event);
	}

	@FXML
	public void deletePatient(ActionEvent event) throws SQLException {

		selectedRecord = (PatientRecord) patientsTable.getSelectionModel().getSelectedItem();
		((Receptionist) LoginController.loggedIn).deletePatient("id", String.valueOf(selectedRecord.getId()));
		this.buildStaffTable(receptionist.getAllPatients());
	}

	@FXML
	public void addPatient(ActionEvent event) throws IOException {
		// views not yet
		show("/view/AddPatient.fxml", event);
	}

	@FXML
	public void logout(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
		show("/view/Login.fxml", event);
	}

	@FXML
	public void searchPatient(ActionEvent event) throws SQLException {

		String key = patientSearchOptions.getValue();
		String value = patientSearchField.getText();
		ResultSet rs = receptionist.searchPatient(key, value);
		buildStaffTable((rs));

	}

	@FXML
	public void cancelSearch(ActionEvent e) throws SQLException {
		patientSearchField.setText("");
		buildStaffTable(receptionist.getAllPatients());
	}

}
