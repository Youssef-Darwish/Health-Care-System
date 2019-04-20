package model.functionalities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.ResultSet;

import model.records.PatientCaseRecord;
import model.records.Record;

public class PatientCase extends Functionality {

	@Override
	public int add(Record record) {
		PatientCaseRecord r = (PatientCaseRecord) record;
		System.out.println("entered");

		try {
			PreparedStatement stmt1 = con.prepareStatement("SELECT * FROM MEDICATION "
					+ "WHERE NAME = ? ;");
			stmt1.setString(1, r.getMedication());
			//System.out.println(">>"+stmt1);
			ResultSet rs = (ResultSet)stmt1.executeQuery();
//			System.out.println(rs.getString("NAME"));
			if (rs.next() == false)
				return -1;
			//System.out.println(rs.getString("MINI"));
			if (r.getAmount() < rs.getInt("MINI") || r.getAmount() > rs.getInt("MAXM"))
				return -2;
			
			PreparedStatement stmt2 = con.prepareStatement("SELECT MEDCOMP FROM MEDCOMPONENTS "
					+ "JOIN PATIENTALERGIES ON "
					+ "MEDCOMPONENTS.MEDCOMP = PATIENTALERGIES.PATIENTALERGIE "
					+ "WHERE NAME = ? and PATIENTID = " + r.getPatientId() +" ;");
			stmt2.setString(1, r.getMedication());
			ResultSet rs2 = (ResultSet)stmt2.executeQuery();
			
			if (rs2.next() == true)
				return -3;

			PreparedStatement stmt = con
					.prepareStatement("INSERT INTO PATIENTCASE (ID, PATIENTID, DISEASE, MEDICATION, AMOUNT) Values(1,?,?,?,?) ;");

			stmt.setInt(1, r.getPatientId());
			stmt.setString(2, r.getDisease());
			stmt.setString(3, r.getMedication());
			stmt.setInt(4, r.getAmount());

			return stmt.executeUpdate();
			//return 0;
		} catch (SQLException e) {
			System.out.println(e);
			return -1;
		}
	}

	@Override
	public int edit(String key, String value,  Record record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String key, String value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ResultSet getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet search(String key,String value) {
		// TODO Auto-generated method stub
		return null;
	}

}
