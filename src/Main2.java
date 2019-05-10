import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.chart.XYChart;
import model.functionalities.GetReports;
import model.users.Manager;

public class Main2 {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		GetReports m = new GetReports();
		Manager mg = new Manager();
		ResultSet rs = mg.getTotalPricesOfEachMedicine();
		
		while (!rs.isLast()) {
			rs.next();
			System.out.println(rs.getString(1)+  "  " +rs.getDouble(2));
		}

	}

}
