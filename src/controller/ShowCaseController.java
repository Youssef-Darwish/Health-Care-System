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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.records.PatientCaseRecord;
import model.records.Record;
import model.users.Doctor;

public class ShowCaseController implements Initializable {

	@FXML // fx:id="addPatientCaseButton"
	private Button addPatientCaseButton;

	@FXML // fx:id="editPatientCaseButton"
	private Button editPatientCaseButton;
	
	@FXML // fx:id="back"
	private Button back;
	
	@FXML // fx:id="caseIdColumn"
	private TableColumn<Record, Integer> caseIdColumn;

	@FXML // fx:id="diseaseColumn"
	private TableColumn<Record, Integer> diseaseColumn;

	@FXML // fx:id="medicationColumn"
	private TableColumn<Record, Integer> medicationColumn;

	@FXML // fx:id="patientCaseTable"
	private TableView<Record> patientCaseTable;

	private Doctor doctor = new Doctor();
	
	public static PatientCaseRecord selectedRecord;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		editPatientCaseButton.disableProperty().bind(Bindings.isEmpty(patientCaseTable.getSelectionModel().getSelectedItems()));

		int id = DoctorController.selectedRecord.getId();
		ResultSet rs = doctor.searchPatientCase("PATIENTID", String.valueOf(id));

		try {
			buildPatientCaseTable(rs);
		} catch (Exception e) {
			e.getMessage();
		}

	}

	@FXML
	public void addPatientCase(ActionEvent event) throws IOException {
		selectedRecord = (PatientCaseRecord) patientCaseTable.getSelectionModel().getSelectedItem();
		show("/view/AddPatientCase.fxml", event);

	}

	@FXML
	public void back(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
		show("/view/DoctorScene.fxml", event);
	}

	@FXML
	public void editPatientCase(ActionEvent event) throws IOException {
		selectedRecord = (PatientCaseRecord) patientCaseTable.getSelectionModel().getSelectedItem();
		show("/view/EditPatientCase.fxml", event);
	}

	private void show(String uml, ActionEvent event) throws IOException {
		Parent pageParent = FXMLLoader.load(getClass().getResource(uml));
		Scene adminPageScene = new Scene(pageParent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		stage.setScene(adminPageScene);

		stage.show();
	}

	public void buildPatientCaseTable(ResultSet rs) throws SQLException {

		ObservableList<Record> data = FXCollections.observableArrayList();
		while (rs.next()) {
			data.add(new PatientCaseRecord(rs.getInt(1), rs.getString(3), rs.getString(4)));

		}

		caseIdColumn.setCellValueFactory(new PropertyValueFactory<>("caseId"));
		diseaseColumn.setCellValueFactory(new PropertyValueFactory<>("disease"));
		medicationColumn.setCellValueFactory(new PropertyValueFactory<>("medication"));

		System.out.println("size  " + String.valueOf(data.size()));
		patientCaseTable.setItems(data);

	}

}
