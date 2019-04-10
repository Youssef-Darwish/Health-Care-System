package model.records;

public class StaffRecord implements Record {

	private int id;
	private String name;
	private int role;
	private String telephone;
	private double salary;
	
	public StaffRecord (int id, String name, int role, String telephone, double salary) {
		this.id = id;
		this.name = name;
		this.role = role;
		this.telephone = telephone;
		this.salary = salary;
		
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

}
