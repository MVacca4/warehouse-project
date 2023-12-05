package warehouse.app;

import java.sql.*;

public class WarehouseDB {
	
	Connection connect;
	
	public void connect() {
	
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_data", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createEmployee(String name, String position, int salary, float hours) {
		
		connect();

		try {
			
			String query = "insert into employees (name, position, salary, hours) values (?, ?, ?, ?)";
			PreparedStatement prepStmt = connect.prepareStatement(query);
			prepStmt.setString(1, name);
			prepStmt.setString(2, position);
			prepStmt.setInt(3, salary);
			prepStmt.setFloat(4, hours);
			
			prepStmt.execute();
			
			connect.close();
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void deleteEmployee(String name) {
		
		connect();

		try {
			
			String query = "delete from employees where name = ?";
			PreparedStatement prepStmt = connect.prepareStatement(query);
			prepStmt.setString(1, name);
			
			prepStmt.execute();
			
			connect.close();
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void viewEmployees() {
		
		connect();

		try {
			
			String query = "select * from employees";
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while (rs.next()) {
				String name = rs.getString("name");
				System.out.println(name);
			}
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public boolean getEmployee(String passedName) {
		
		try {
			
			String query = "select * from employees where name = ?";
			PreparedStatement prepStmt = connect.prepareStatement(query);
			prepStmt.setString(1, passedName);
			
			ResultSet rs = prepStmt.executeQuery();
			
			if (rs.next()) {
				String name = rs.getString("name");
				String position = rs.getString("position");
				int salary = rs.getInt("salary");
				float hours = rs.getFloat("hours");
				
				System.out.println("\nName: " + name + "\nPosition: " + position + "\nSalary: " + salary + "\nHours: " + hours);
				return true;
			} else {
				System.out.println("That name does not exist in the system.");
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			return false;
		}
	}

	public boolean updateEmployee(String name, String newField, int column) {

		String query;
		PreparedStatement prepStmt;
		
		try {

			try {

				switch (column) {
					case 1:
						query = "update employees set name = ? where name = ?";
						prepStmt = connect.prepareStatement(query);
						prepStmt.setString(1, newField);
						prepStmt.setString(2, name);
						prepStmt.executeUpdate();
						break;
					case 2:
						query = "update employees set position = ? where name = ?";
						prepStmt = connect.prepareStatement(query);
						prepStmt.setString(1, newField);
						prepStmt.setString(2, name);
						prepStmt.executeUpdate();
						break;
					case 3:
						query = "update employees set salary = ? where name = ?";
						prepStmt = connect.prepareStatement(query);
						prepStmt.setInt(1, Integer.parseInt(newField));
						prepStmt.setString(2, name);
						prepStmt.executeUpdate();
						break;
					case 4:
						query = "update employees set hours = ? where name = ?";
						prepStmt = connect.prepareStatement(query);
						prepStmt.setFloat(1, Float.parseFloat(newField));
						prepStmt.setString(2, name);
						prepStmt.executeUpdate();
						break;
					default:
				}

			} catch (NumberFormatException e) {
				return false;
			}

		} catch (SQLException e) {
			System.err.println("Error interacting with database." + e.getMessage());
		}
		return true;
	}

	public int getSize() {

		connect();

		int count = 0;

		try {

			String query = "select * from employees";
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				++count;
			}

			connect.close();
			return count;

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return count;
		}
	}
}
