package employeeview;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 public class UpdateEmployee extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;

	public UpdateEmployee() {
		super();
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {			
			response.setContentType("text/html;charset=UTF-8");
			// Получение из http-запроса значения параметра id
			String id = request.getParameter("id");
			
			Employee employee = EmployeeUnitOfWork.getEmployeeById(id);
			
			if (employee == null) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");			
				dispatcher.forward(request, response);
			}
					
			// Помещение результатов в параметр запроса
			request.setAttribute("selectedEmployee", employee);
			
			// Перенаправление http-запроса на страницу updateEmployee.jsp
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/updateEmployee.jsp");			
			dispatcher.forward(request, response);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ServletException(ex);
		}
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Employee employee = new Employee(
				Long.parseLong(request.getParameter("id")),
				request.getParameter("FirstName"),
				request.getParameter("LastName"),
				request.getParameter("Designation"),
				request.getParameter("Phone")
		);
		
		try {
			EmployeeUnitOfWork.setEmployee(employee);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("EmployeeServlet");
	}   	  	    
}