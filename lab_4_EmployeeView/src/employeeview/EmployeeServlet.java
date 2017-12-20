package employeeview;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EmployeeServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	static final long serialVersionUID = 1L;
	
	public EmployeeServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html;charset=UTF-8");
			// Получение из http-запроса значения параметра lasname
			String lastName = request.getParameter("lastname");
			
			// Коллекция для хранения найденных сотрудников
			ArrayList<Employee> employees = null;
			
			if (lastName == null || lastName.equals("")) {
				employees = EmployeeUnitOfWork.getAllEmployees();
			}
			else {
				employees = EmployeeUnitOfWork.getAllEmployeesByLastName(lastName);
			}
			
			// Помещение результатов в параметр запроса employeesFound
			request.setAttribute("employeesFound", employees);
			request.setAttribute("searchQuery", lastName == null ? "" : lastName);
			
			// Перенаправление http-запроса на страницу index.jsp
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");			
			dispatcher.forward(request, response);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ServletException(ex);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
