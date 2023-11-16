package warehouse.app;
import java.util.*;

public class Warehouse {
	
	static List<Employee> employeeData = new ArrayList<Employee>();
	
	public static void main(String[] args) {
		
		boolean end = false; // False keeps program running, true ends program.
		
		while (end == false) {
		
			Scanner sc = new Scanner(System.in);
			int choice = 0;
			optionDisplay();
			choice = sc.nextInt();
			
			switch (choice) {
				case 0:
					end = true;
					break;
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
		}
	}
	
	public static void optionDisplay() {
		
		System.out.print("Welcome to the warehouse database system. ");
		System.out.println("Please type the corresponding number of your choice and hit enter:\n");
		System.out.println("0. Exit program.");
		System.out.println("1. Create new employee.");
		System.out.println("2. Delete existing employee.");
		System.out.println("3. View employee info.");
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
	}
	
	public static void deleteEmployee() {
		
		Scanner sc = new Scanner(System.in);
		String name = "";
		boolean loopEnd = false;
		
		while (loopEnd == false) {
			
			System.out.print("Enter the name of the employee you wish to delete, or enter 0 to go back to the Main Menu: ");
			name = sc.nextLine();
			
			try {
				if (Integer.parseInt(name) == 0) {loopEnd = true;} // If user entered 0, end while loop.
			} catch (NumberFormatException e) {
				// Loop through all employee entries and delete an entry if it matches the name typed.
				for (int i = 0; i < employeeData.size(); i++) {
					if (name.equalsIgnoreCase(employeeData.get(i).name)) {
						employeeData.remove(i);
						System.out.println("Employee entry has been deleted!\n");
						break;
					} else {
						if (i == employeeData.size() - 1) {	// Display message if loop has gotten to last employee entry.
							System.out.println("Sorry, that employee name doesn't match any in our records. Please ensure you entered the name correctly.\n");
						}
					}
				}
			}
		}
	}
	
	public static void viewEmployee() {
		
		System.out.println("Choose between 1 and " + employeeData.size() + " to see information for the corresdponding employee:");
		
		for (int i = 1; i <= employeeData.size(); i++) {			// FIGURE OUT INDEX ISSUE
			System.out.println(i + "." + employeeData.get(i).name);
		}
	}
}
