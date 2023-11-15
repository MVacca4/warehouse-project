package warehouse.app;
import java.util.Scanner;

public class Warehouse {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		
		System.out.print("Welcome to the warehouse database system. ");
		System.out.println("Please type the corresponding number of your choice and hit enter:\n");
		System.out.println("1. Create new employee");
		System.out.println("2. Delete existing employee");
		System.out.println("3. View employee info");
		choice = sc.nextInt();
		
		switch (choice) {
			case 1:
				createEmployee();
				break;
			case 2:
				deleteEmployee();
				break;
			case 3:
				viewEmployee();
				break;
			default:
		}
		
		// Continue figuring out logic from here.
	}
	
	public static void createEmployee() {
		
		Scanner sc = new Scanner(System.in);
		String choice = "";
		String name = "";
		String position = "";
		int salary = 0;
		float hours = 0;
	
		System.out.println("Fill in the following questions, if the answer is unknown leave the field blank.");
		System.out.print("Enter employee's name: ");
		name = sc.nextLine();
		System.out.print("Enter employee's position: ");
		position = sc.nextLine();
		
		// User entered nothing for salary or hours.
		try {
			System.out.print("Enter employee's salary, no spaces, no commas: ");
			salary = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {salary = 0;} // Assign default value of 0.
		try {
			System.out.print("Enter employee's hours: ");
			hours = Float.parseFloat(sc.nextLine());
		} catch (NumberFormatException e) {hours = 0;} // Assign default value of 0.
		
		Employee employeePartialInfo = new Employee(name, position, salary, hours);
		System.out.println("Employee entry created!");
	}
	
	public static void deleteEmployee() {
		
	}
	
	public static void viewEmployee() {
		
	}
}
