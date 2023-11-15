package warehouse.app;
import java.util.Scanner;

public class Warehouse {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		int choice = 0;
		
		System.out.print("Welcome to the warehouse database system.");
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
		
		sc.close();
	}
	
	public static void createEmployee() {
		
		System.out.println("Do you currently have the name, position, salary, and hours for the employee? (y/n)");
		String choice = sc.nextLine();
		
		if (choice.equals("y")) {
			System.out.println("Enter employee info in the format 'Name, Position, Salary, Hours' including commas.");
			System.out.println("Example: Tom Harris, CEO, 200000, 40");
			String tempHold = sc.nextLine();
			String[] words = tempHold.split("\\W+");
			Employee employee = new Employee(words[0], words[1], Integer.parseInt(words[2]), Integer.parseInt(words[3]));
		} else {
			// Continue employee creation with partial data here.
		}
	}
	
	public static void deleteEmployee() {
		
	}
	
	public static void viewEmployee() {
		
	}
}
