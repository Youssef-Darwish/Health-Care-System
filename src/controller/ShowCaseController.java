package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.records.Record;


public class ShowCaseController  implements Initializable{
	
	@FXML // fx:id="addPatientCaseButton"
	private Button addPatientCaseButton;


	@FXML // fx:id="caseIdColumn"
	private TableColumn<Record, Integer> patientIdColumn;

	@FXML // fx:id="doctorIdColumns"
	private TableColumn<Record, Integer> doctorIdColumns;
	
	@FXML // fx:id="diseaseColumn"
	private TableColumn<Record, Integer> diseaseColumn;
	
	@FXML // fx:id="medicationColumn"
	private TableColumn<Record, Integer> medicationColumn;

	@FXML // fx:id="patientCaseTable"
	private TableView<Record> patientCaseTable;
	

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		System.out.println(DoctorController.selectedRecord.getId());
		
		
	}
	
	
	@FXML
	public void addPatientCase(ActionEvent event){
		
		
	}

}
