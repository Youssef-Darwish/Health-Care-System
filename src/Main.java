import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.functionalities.PatientCase;
import model.records.PatientCaseRecord;

public class Main  {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		PatientCase p = new PatientCase();
		System.out.print(p.add(new PatientCaseRecord(1,"3yan","dawa",7)));
		//launch(args);
	}

//	public void start(Stage primaryStage) throws Exception {
//		Parent root = (Parent) FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
//		primaryStage.setScene(new Scene(root));
//		primaryStage.setResizable(false);
//		primaryStage.show();
//	}

}