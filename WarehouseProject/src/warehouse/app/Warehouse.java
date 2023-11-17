package warehouse.app;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Warehouse {
	
	String name = "";
	Scanner sc = new Scanner(System.in);
	List<Employee> employeeData = new ArrayList<Employee>();
	
	public static void main(String[] args) {
		
		Warehouse wh = new Warehouse();
		boolean end = false; // False keeps program running, true ends program.
		
		while (end == false) {
		
			Scanner mainSc = new Scanner(System.in);
			int choice = 0;
			wh.optionDisplay();
			choice = mainSc.nextInt();
			
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
		System.out.println("Employee entry created!\n-----------------------");
		wait(1500);
	}
	
	public void deleteEmployee() {
		
		boolean end = false;
		
		while (end == false) {
			
			System.out.println("Enter the name of the employee you wish to delete, or enter 0 to go back to the main menu: ");
			name = sc.nextLine();
			
			try {
				if (Integer.parseInt(name) == 0) {end = true;} // If user entered 0, end while loop.
			} catch (NumberFormatException e) {
				// Loop through all employee entries and delete an entry if it matches the name typed.
				for (int i = 0; i < employeeData.size(); i++) {
					if (name.equalsIgnoreCase(employeeData.get(i).name)) {
						employeeData.remove(i);
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
	
	public void viewEmployee() {
		
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
					System.out.println("Invalid entry, please use numbers only");	// THIS IS BROKEN, FIX FIRST BEFORE DOING ANYTHING ELSE
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
