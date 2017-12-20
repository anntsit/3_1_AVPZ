package avps.labs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogInServlet
 */
@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {			
			System.gc();
			// Загрузка драйвера БД Derby				
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
			
			response.setContentType("text/html;charset=UTF-8");
	        String userName = request.getParameter("userName");
	        String password = request.getParameter("password");
	        
            HttpSession session = request.getSession(true);
	        if (userName != "" && password != "" && loginIsValid(userName, password)) {
	            session.setAttribute("user", userName);
	            session.setAttribute("booksBasket", new HashMap<String, Integer[]>());
	        }
	        else {
	            session.removeAttribute("user");
	            session.removeAttribute("booksBasket");
	        }
	        session.setAttribute("count1", 1);
	        session.setAttribute("count2", 1);
	        session.setAttribute("count3", 1);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
	        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/bookStore.jsp");
	        dispatcher.forward(request, response);
		}
	}
	private boolean loginIsValid(String userName, String actualPass) throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/myDB;create=true;user=me;password=mine");
		
		ResultSet rs = con.createStatement().executeQuery(
					"SELECT password FROM login WHERE user_name = '" + userName + "'");
		String expectedPass = "";
		while (rs.next())
			expectedPass = rs.getString(1);
		
		rs.close();
		con.close();
		
		return expectedPass.equals(actualPass);
	}
}
