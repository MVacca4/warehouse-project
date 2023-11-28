package warehouse.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The base class for the Warehouse management program.
 * @author Massimiliano Vacca
 **/ 
public class Warehouse {
	
	String name = ""; // Used when an employee's name is needed in methods.
	Scanner sc = new Scanner(System.in); // Takes user input.
	List<Employee> employeeData = new ArrayList<Employee>(); // Container for holding employee data.
	WarehouseDB wdb = new WarehouseDB(); // WarehouseDB object to manipulate data with.
	
	public static void main(String[] args) {
		
		Warehouse wh = new Warehouse(); // Warehouse object to call methods with.
		boolean end = false; // False keeps program running, true ends program.
		
		// Main menu loop, continues until the user enters 0, ending the program.
		while (end == false) {
		
			Scanner mainSc = new Scanner(System.in);
			int choice = 0; // Holds choice for main menu.
			wh.optionDisplay(); // Display main menu options.
			choice = mainSc.nextInt(); // Get choice selection from user.
			
			// Call method based on which choice the user made, or end the program if 0 was selected.
			switch (choice) {
				case 0:
					end = true;
					break;
				case 1:
					wh.createEmployee();
					break;
				case 2:
					wh.deleteEmployee();
					break;
				case 3:
					wh.viewEmployee();
					break;
				default:
			}
		}
	}
	
	public void createEmployee() {
		
		sc = new Scanner(System.in);
		int salary = 0;
		float hours = 0;
	
		System.out.println("Fill in the following questions, if the answer is unknown leave the field blank.");
		System.out.print("Enter employee's name: ");
		name = sc.nextLine();
		System.out.print("Enter employee's position: ");
		String position = sc.nextLine();
		
		// Surround with try...catch in case user enters nothing for salary or hours.
		try {
			System.out.print("Enter employee's salary, no spaces, no commas: ");
			salary = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {salary = 0;} // Assign default value of 0.
		try {
			System.out.print("Enter employee's hours: ");
			hours = Float.parseFloat(sc.nextLine());
		} catch (NumberFormatException e) {hours = 0;} // Assign default value of 0.
		
		Employee employeeInfo = new Employee(name, position, salary, hours);
		employeeData.add(employeeInfo);
		wdb.connect();
		wdb.createEmployee(name, position, salary, hours);
		System.out.println("Employee entry created!\n-----------------------");
		wait(1500);
	}
	
	public void deleteEmployee() {
		
		sc = new Scanner(System.in);
		boolean end = false;
		
		while (end == false) {

			if (employeeData.size() == 0) {
				System.out.println("The employee list currently has no entries. Returning you to the main menu.\n--------------------------------");
				end = true;
				wait(2000);
			} else {
				System.out.println("Enter the name of the employee you wish to delete, or enter 0 to go back to the main menu: ");
				name = sc.nextLine();
				
				try {
					if (Integer.parseInt(name) == 0) {end = true;} // If user entered 0, end while loop.
				} catch (NumberFormatException e) {
					// Loop through all employee entries and delete an entry if it matches the name typed.
					for (int i = 0; i < employeeData.size(); i++) {
						if (name.equalsIgnoreCase(employeeData.get(i).name)) {
							employeeData.remove(i);
							wdb.connect();
							wdb.deleteEmployee(name);
							System.out.println("Employee entry has been deleted!\n--------------------------------");
							wait(1500);
							break;
						} else {
							if (i == employeeData.size() - 1) {	// Display message if loop has gotten to last employee entry.
								System.out.println("Sorry, that employee name doesn't match any in our records. Please ensure you entered the name correctly.\n");
								wait(1500);
							}
						}
					}
				}
			}
		}
	}	
	
	public void viewEmployee() {
		
		sc = new Scanner(System.in);
		boolean end = false;
		Scanner detectEnter = new Scanner(System.in);
		
		if (employeeData.size() == 0) {
			System.out.println("There are no existing employee entries, returning you to the main menu.");
			System.out.println("--------------------------------");
			wait(2000);
		} else {
			while (end == false) {
				System.out.println("Choose between 1 and " + employeeData.size() + " to see information for the corresdponding employee:");
				wait(1500);
				
				for (int i = 0; i < employeeData.size(); i++) {
					System.out.println(i + 1 + ". " + employeeData.get(i).name);
				}
				
				try {
					System.out.println("Enter 0 to go back to the main menu.");
					int choice = sc.nextInt();
					
					if (choice == 0) {
						end = true;
					}
					
					for (int i = 0; i < employeeData.size(); i++) {
						if (choice == 1 + employeeData.indexOf(employeeData.get(i))) {
							System.out.println("Name: " + employeeData.get(i).name + "\nPosition: " + employeeData.get(i).position
									+ "\nSalary: " + employeeData.get(i).salary + "\nHours: " + employeeData.get(i).hours);
							wait(1500);
							System.out.println("\nHit enter to see the list of employees again.");
							if (detectEnter.hasNextLine()) {String s = detectEnter.nextLine();} // Detects enter key press.
						}
					}
				} catch (Exception e) {
					System.out.println("Invalid entry, please use numbers only\n--------------------------------");
					sc = new Scanner(System.in);
					wait(1500);
				}
			}
		}
	}
	
	public static void wait(int ms) {
		
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
	public void optionDisplay() {
		
		System.out.print("Welcome to the warehouse database system. ");
		System.out.println("Please type the corresponding number of your choice and hit enter:\n");
		System.out.println("0. Exit program.");
		System.out.println("1. Create new employee.");
		System.out.println("2. Delete existing employee.");
		System.out.println("3. View employee list.");
	}
}
