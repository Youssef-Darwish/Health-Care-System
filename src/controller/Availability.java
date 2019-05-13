package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.records.AvailablityRecord;
import model.records.Record;
import model.users.Receptionist;

public class Availability implements Initializable {

	@FXML // fx:id="availabilityTable"
	private TableView<Record> availabilityTable;

	@FXML // fx:id="dateColumn"
	private TableColumn<Record, Date> dateColumn;

	@FXML // fx:id="timeColumn"
	private TableColumn<Record, String> timeColumn;

	@FXML // fx:id="doctorNameLabel"
	private Label doctorNameLabel;

	@FXML // fx:id="selectButton"
	private Button selectButton;

	@FXML // fx:id="cancelButton"
	private Button cancelButton;

	private Receptionist receptionist = new Receptionist();

	public void buildAvailTable(ResultSet rs) throws SQLException {

		ObservableList<Record> data = FXCollections.observableArrayList();
		// check fields of patient
		while (rs.next()) {
			data.add(new AvailablityRecord(rs.getDate(3), rs.getString(4)));
		}

		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
		//
		availabilityTable.setItems(data);

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		selectButton.disableProperty().bind(Bindings.isEmpty(availabilityTable.getSelectionModel().getSelectedItems()));

		String id = AddAppointmentController.doctorID;
		String []splittedDoctor = id.split("id: ");
				
		splittedDoctor[1]=splittedDoctor[1].substring(0, splittedDoctor[1].length()-1); 

		ResultSet rs = receptionist.getAvailability(splittedDoctor[1]);
		try {
			buildAvailTable(rs);
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
	public void select(ActionEvent event) throws IOException {

		AvailablityRecord selectedRecord = (AvailablityRecord) availabilityTable.getSelectionModel().getSelectedItem();
		AddAppointmentController.appointmentDate = selectedRecord.getDate().toString();
		AddAppointmentController.appointmentTime = selectedRecord.getTime();

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
		show("/view/AddAppointment.fxml", event);

	}
	
	@FXML
	public void cancel(ActionEvent event) throws IOException{
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
		show("/view/AddAppointment.fxml", event);

	}
	
	
	
	
	
	

}
