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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.records.PatientRecord;
import model.records.Record;
import model.users.Doctor;

public class DoctorController implements Initializable {

	@FXML // fx:id="showCaseButton"
	private Button showCaseButton;

	@FXML
	private Button logoutButton;

	@FXML // fx:id="patientIdColumn"
	private TableColumn<Record, Integer> patientIdColumn;

	@FXML // fx:id="patientNameColumn"
	private TableColumn<Record, String> patientNameColumn;
	
	@FXML // fx:id="medicineIdColumn"
	private TableColumn<Record, Integer> medicineIdColumn;

	@FXML // fx:id="medicinePriceColumn"
	private TableColumn<Record, Integer> medicinePriceColumn;

	@FXML // fx:id="patientsTable"
	private TableView<Record> patientsTable;
	
	@FXML // fx:id="medicinesTable"
	private TableView<Record> medicinesTable;

	@FXML // fx:id="tabPane"
	private TabPane tabPane;

	@FXML // fx:id="medicinesTab"
	private Tab medicinesTab;

	@FXML // fx:id="patientsTab"
	private Tab patientsTab;

	private Doctor doctor = new Doctor();
	public static PatientRecord selectedRecord;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		showCaseButton.disableProperty()
				.bind(Bindings.isEmpty(patientsTable.getSelectionModel().getSelectedItems()));
		
		int id = ((Doctor)(LoginController.loggedIn)).getId();
		ResultSet rs = (ResultSet) doctor.getDoctorPatients(((Doctor)(LoginController.loggedIn)).getId());
		System.out.println(rs==null);
		try {
			this.buildPatientTable(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void showCase(ActionEvent event) throws IOException {
		selectedRecord = (PatientRecord) patientsTable.getSelectionModel().getSelectedItem();
		show("/view/PatientCase.fxml", event);
	}

	@FXML
	private void logout(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
		show("/view/Login.fxml", event);
	}
	
	private void show(String uml, ActionEvent event) throws IOException {
		Parent pageParent = FXMLLoader.load(getClass().getResource(uml));
		Scene adminPageScene = new Scene(pageParent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		stage.setScene(adminPageScene);

		stage.show();
	}

//	@FXML
//	public void selectAppTab(Event e) throws SQLException{
//		System.out.println("app tab selected");
//		ResultSet rs = (ResultSet) receptionist.getAllAppointments();
//		buildAppTable(rs);
//	}
	

	public void buildPatientTable(ResultSet rs) throws SQLException {
		
		ObservableList<Record> data = FXCollections.observableArrayList();
		
		// check fields of Medicines
		while (rs.next()) {
	
			data.add(new PatientRecord(rs.getInt(1), rs.getString(2)));
		}
		
		patientIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		patientNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		System.out.println("size  " + String.valueOf(data.size()));
		patientsTable.setItems(data);
		
		
	}

}
