package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
	private TableColumn<Record, Integer> staffid ;
	
	@FXML // fx:id="staffname"
	private TableColumn<Record, String> staffname;
	
	@FXML // fx:id="staffrole"

	private TableColumn<Record, Integer> staffrole;
	
	@FXML // fx:id="stafftelephone"
	private TableColumn<Record, String> stafftelephone;
	
	@FXML // fx:id="staffsalary"
	private TableColumn<Record, Double> staffsalary ;
	
	
	public void buildStaffTable() throws SQLException {

		System.out.println(staffTable == null);
		

		String SQL = "SELECT * from STAFF";
		// ResultSet
		ResultSet rs = (ResultSet) LoginController.con.createStatement().executeQuery(SQL);
		ObservableList<Record> data = FXCollections.observableArrayList();

		while (rs.next()) {

			data.add(new StaffRecord(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getDouble(5)));
		}
		
		staffid.setCellValueFactory(new PropertyValueFactory<>("id"));
		staffname.setCellValueFactory(new PropertyValueFactory<>("name"));
		staffrole.setCellValueFactory(new PropertyValueFactory<>("role"));
		stafftelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
		staffsalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

//		 staffTable.setItems(null);
		staffTable.setItems(data);

	}


	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	//	ObservableList<String> choices = FXCollections.observableArrayList("Doctor", "Receptionist", "Manager");
		
	try {
		this.buildStaffTable();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//	role.setItems(choices);

	}

	private void show(String uml, ActionEvent event) throws IOException {
		Parent pageParent = FXMLLoader.load(getClass().getResource(uml));
		Scene adminPageScene = new Scene(pageParent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(adminPageScene);
		
		stage.show();
	}

//	public int editStaffMember (String key, String value) {
//		//form new record before sending
//		Record r;
//		return ((Admin) LoginController.loggedIn).editStaffMember(key, value, r);
//	}
//	
//	public int deleteStaffMember (String key, String value) {
//		System.out.println();
//		return ((Admin) LoginController.loggedIn).deleteStaffMember(key, value);
//	}

	@FXML
	public void addStaffMember(ActionEvent event) throws IOException {
		show("/view/AddStaffMember.fxml", event);
	}
	
}
