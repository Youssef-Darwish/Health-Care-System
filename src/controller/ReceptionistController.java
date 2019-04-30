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
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.records.AppointmentRecord;
import model.records.PatientRecord;
import model.records.Record;
import model.users.Doctor;
import model.users.Receptionist;

public class ReceptionistController implements Initializable {

	@FXML // fx:id="patientsTable"
	private TableView<Record> patientsTable;

	@FXML // fx:id="appTable"
	private TableView<Record> appTable;

	@FXML // fx:id="patientIdColumn"
	private TableColumn<Record, Integer> patientIdColumn;

	@FXML // fx:id="patientNameColumn"
	private TableColumn<Record, String> patientNameColumn;

	@FXML // fx:id="patientGenderColumn"
	private TableColumn<Record, Integer> patientGenderColumn;

	@FXML // fx:id="patientTelephoneColumn"
	private TableColumn<Record, String> patientTelephoneColumn;

	@FXML // fx:id="patientRegisterationColumn"
	private TabPane tabPane;

	@FXML // fx:id="medicinesTab"
	private Tab medicinesTab;

	@FXML // fx:id="patientsTab"
	private Tab patientsTab;

	@FXML // fx:id="appTab"
	private Tab appTab;

	@FXML // fx:id="tabPane"
	private TableColumn<Record, String> patientRegisterationColumn;

	@FXML // fx:id="appPatientIdColumn"
	private TableColumn<Record, Integer> appPatientIdColumn;

	@FXML // fx:id="appIdColumn"
	private TableColumn<Record, Integer> appIdColumn;

	@FXML // fx:id="appDoctorIdColumn"
	private TableColumn<Record, String> appDoctorIdColumn;

	@FXML // fx:id="appDateColumn"
	private TableColumn<Record, Integer> appDateColumn;

	@FXML // fx:id="appHourColumn"
	private TableColumn<Record, String> appHourColumn;

	@FXML // fx:id="medNameColumn"
	private TableColumn<Record, Integer> medNameColumn;

	@FXML // fx:id="priceColumn"
	private TableColumn<Record, String> priceColumn;

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

	public static AppointmentRecord selectedAppRecord;

	@FXML // fx:id="addApp"
	private Button addApp;

	@FXML // fx:id="editApp"
	private Button editApp;

	@FXML // fx:id="deleteApp"
	private Button deleteApp;

	@FXML
	private Button logoutButton;

	private Receptionist receptionist = new Receptionist();
	private Doctor doctor = new Doctor();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		editPatientButton.disableProperty()
				.bind(Bindings.isEmpty(patientsTable.getSelectionModel().getSelectedItems()));
		deletePatientButton.disableProperty()
				.bind(Bindings.isEmpty(patientsTable.getSelectionModel().getSelectedItems()));

		editApp.disableProperty().bind(Bindings.isEmpty(appTable.getSelectionModel().getSelectedItems()));
		deleteApp.disableProperty().bind(Bindings.isEmpty(appTable.getSelectionModel().getSelectedItems()));

		ObservableList<String> searchAttributes = FXCollections.observableArrayList("ID", "Name", "Gender", "Telephone",
				"REGISTERATIONDATE");
		patientSearchOptions.setItems(searchAttributes);
		patientSearchOptions.setValue("ID");

		ResultSet rs = (ResultSet) receptionist.getAllPatients();

		try {
			this.buildPatientTable(rs);
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
		show("/view/EditPatient.fxml", event);
	}

	@FXML
	public void editAppointment(ActionEvent event) throws IOException {
		selectedAppRecord = (AppointmentRecord) appTable.getSelectionModel().getSelectedItem();
		System.out.println(selectedAppRecord==null);
		show("/view/EditAppointment.fxml", event);
	}

	@FXML
	public void deletePatient(ActionEvent event) throws SQLException {
		selectedRecord = (PatientRecord) patientsTable.getSelectionModel().getSelectedItem();
		((Receptionist) LoginController.loggedIn).deletePatient("id", String.valueOf(selectedRecord.getId()));
		this.buildPatientTable(receptionist.getAllPatients());
	}

	@FXML
	public void deleteAppointment(ActionEvent event) throws SQLException {
		selectedAppRecord = (AppointmentRecord) appTable.getSelectionModel().getSelectedItem();
		((Receptionist) LoginController.loggedIn).deleteAppointment("id", String.valueOf(selectedAppRecord.getAppointmentId()));
		this.buildAppTable(receptionist.getAllAppointments());
	}

	@FXML
	public void addPatient(ActionEvent event) throws IOException {
		show("/view/AddPatient.fxml", event);
	}

	@FXML
	public void addAppointment(ActionEvent event) throws IOException {
		show("/view/AddAppointment.fxml", event);
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
		buildPatientTable((rs));

	}

	@FXML
	public void cancelSearch(ActionEvent e) throws SQLException {
		patientSearchField.setText("");
		buildPatientTable(receptionist.getAllPatients());
	}

	@FXML
	public void selectAppTab(Event e) throws SQLException {
		System.out.println("app tab selected");
		ResultSet rs = (ResultSet) receptionist.getAllAppointments();
		buildAppTable(rs);
	}

	@FXML
	public void selectPatientTab(Event e) throws SQLException {
		System.out.println("patients tab selected");
		ResultSet rs = (ResultSet) receptionist.getAllPatients();
		buildPatientTable(rs);
	}

	public void buildPatientTable(ResultSet rs) throws SQLException {

		ObservableList<Record> data = FXCollections.observableArrayList();

		// check fields of patient
		while (rs.next()) {

			data.add(new PatientRecord(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5)));
		}

		patientIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		patientNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
		patientGenderColumn.setCellValueFactory(new PropertyValueFactory<>("Gender"));
		patientTelephoneColumn.setCellValueFactory(new PropertyValueFactory<>("Telephone"));
		patientRegisterationColumn.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));

		patientsTable.setItems(data);

	}

	public void buildAppTable(ResultSet rs) throws SQLException {

		ObservableList<Record> data = FXCollections.observableArrayList();
		// check fields of patient
		while (rs.next()) {
			data.add(new AppointmentRecord(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getDate(5)));
		}

		appIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
		appPatientIdColumn.setCellValueFactory(new PropertyValueFactory<>("patientId"));
		appDoctorIdColumn.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
		appDateColumn.setCellValueFactory(new PropertyValueFactory<>("AppointmentDate"));
		appHourColumn.setCellValueFactory(new PropertyValueFactory<>("Hour"));

		appTable.setItems(data);

	}

}
