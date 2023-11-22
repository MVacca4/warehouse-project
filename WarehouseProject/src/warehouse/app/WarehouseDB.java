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
}
