package employeeview;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeUnitOfWork {
	
	
	private static Connection getCurrentConnection() throws Exception {				
		Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
		return DriverManager.getConnection("jdbc:derby://localhost:1527/myDB;create=true;user=me;password=mine");
	}
	
	public static ArrayList<Employee> getAllEmployees() throws Exception{
		ArrayList<Employee> employees = new ArrayList<Employee>();

		Connection con = getCurrentConnection();
		// Выполнение SQL-запроса
		ResultSet rs = con.createStatement().executeQuery(
		"Select * from employee");
		
		// Перечисление результатов запроса
		while (rs.next()) {
			Employee emp = new Employee(
				rs.getLong(1), 
				rs.getString(2), 
				rs.getString(3), 
				rs.getString(4), 
				rs.getString(5));
			
			employees.add(emp);
		}
		
		rs.close();
		con.close();
		
		return employees;
	}
	public static ArrayList<Employee> getAllEmployeesByLastName(String lastName) throws Exception{
		ArrayList<Employee> employees = new ArrayList<Employee>();

		Connection con = getCurrentConnection();
		
		// Выполнение SQL-запроса
		ResultSet rs = con.createStatement().executeQuery(
				"Select * "
				+ "from employee " + "Where last_name like '%"
				+ lastName + "%'");
		
		// Перечисление результатов запроса
		while (rs.next()) {
			Employee emp = new Employee(
				rs.getLong(1), 
				rs.getString(2), 
				rs.getString(3), 
				rs.getString(4), 
				rs.getString(5));
			
			employees.add(emp);
		}
		
		rs.close();
		con.close();
		
		return employees;
	}
	public static Employee getEmployeeById(String id) throws Exception {
		Connection con = getCurrentConnection();
		PreparedStatement st = con.prepareStatement("Select first_name, last_name, designation, phone From employee Where id = ?");
		st.setInt(1, Integer.parseInt(id));
		
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			return new Employee(Long.parseLong(id),	rs.getString(1), rs.getString(2), rs.getString(3),  rs.getString(4));
		}
		rs.close();
		con.close();
		
		return null;
	}		
	public static void addEmployee(Employee employee) throws Exception {
		Connection con = getCurrentConnection();
		PreparedStatement st = con.prepareStatement(
				"Insert into employee " +
				"(id, first_name, last_name, designation, phone) " +
				"values (?, ?, ?, ?, ?)");
		st.setLong(1, employee.getId());
		st.setString(2, employee.getFirstName());
		st.setString(3, employee.getLastName());
		st.setString(4, employee.getDesignation());
		st.setString(5, employee.getPhone());
		st.executeUpdate();
		con.close();
	}			
	public static void setEmployee(Employee employee) throws Exception {
		Connection con = getCurrentConnection();
		PreparedStatement st = con.prepareStatement(
				"Update employee " +
				"Set first_name=?, last_name=?, designation=?, phone=? " +
				"Where id=?");	
		Employee emp = (Employee)(getEmployeeById(employee.getId().toString()));
		st.setLong(5, employee.getId());
		
		if (!employee.getFirstName().equals("")) {
			st.setString(1, employee.getFirstName());
		}else {
			st.setString(1, emp.getFirstName());
		}
		if (!employee.getLastName().equals("")) {
			st.setString(2, employee.getLastName());
		}else {
			st.setString(2, emp.getLastName());
		}
		if (!employee.getDesignation().equals("")) {	
			st.setString(3, employee.getDesignation());
		}else {
			st.setString(3, emp.getDesignation());
		}
		if (!employee.getPhone().equals("")) {	
			st.setString(4, employee.getPhone());
		}else {
			st.setString(4, emp.getPhone());
		}
		
		st.executeUpdate();
		con.close();
	}	
}
