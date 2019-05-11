package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import com.mysql.jdbc.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.records.PatientRecord;
import model.records.Record;

public class Availability implements Initializable{

	@FXML // fx:id="availabilityTable"
	private TableView<Record> availabilityTable;
	
	@FXML // fx:id="dateColumn"
	private TableColumn<Record, Date> dateColumn;
	
	@FXML // fx:id="timeColumn"
	private TableColumn<Record, String> timeColumn;
	
	@FXML // fx:id="doctorNameLabel"
	private Label doctorNameLabel;
	
	@FXML
	public void selectAvailTab(Event e) throws SQLException {
		System.out.println("Availablities tab selected");
		ResultSet rs = (ResultSet) receptionist.getAllDoctorsAvails();
		buildAvailTable(rs);
	}
	
	public void buildAvailTable(ResultSet rs) throws SQLException {

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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
