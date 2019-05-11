package model.records;

import java.util.Date;

public class AvailablityRecord implements Record {

	private int doctorId;
	private Date date;
	private String time;

	public AvailablityRecord(int id, String time, Date date) {

		this.date = date;
		this.doctorId = id;
		this.time = time;
	}

	public AvailablityRecord(Date date,String time) {

		this.date = date;
		this.time = time;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
