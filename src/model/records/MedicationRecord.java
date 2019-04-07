package model.records;

import java.util.*;

public class MedicationRecord implements Record {

	private int price;
	private int patientId;
	private Date datePrescribed;

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public Date getDatePrescribed() {
		return datePrescribed;
	}

	public void setDatePrescribed(Date datePrescribed) {
		this.datePrescribed = datePrescribed;
	}
}
