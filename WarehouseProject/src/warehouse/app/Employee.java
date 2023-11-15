package warehouse.app;

public class Employee {

	String name = "";
	String position = "";
	int salary = 0;
	int hours = 0;
	
	public Employee(String name, String position, int salary, int hours) {
		this.name = name;
		this.position = position;
		this.salary = salary;
		this.hours = hours;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	
}
