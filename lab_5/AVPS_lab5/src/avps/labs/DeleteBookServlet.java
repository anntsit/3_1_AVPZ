package avps.labs;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DeleteBookServlet
 */
@WebServlet("/DeleteBookServlet")
public class DeleteBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBookServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		HashMap<String, Integer[]> booksBasket = (HashMap<String, Integer[]>) session.getAttribute("booksBasket");
		
		try {
			String bookName = request.getParameter("bookName");
			String bookCountStr = request.getParameter("bookCount");
			Integer countToDelete = (bookCountStr == null || Integer.parseInt(bookCountStr) <= 0) ? null : new Integer(bookCountStr);
			if (bookName != null && countToDelete != null) {
				Integer oldCount = booksBasket.get(bookName)[1];
				Integer newCount = oldCount - countToDelete;
				if (newCount <= 0)
					booksBasket.remove(bookName);
				else {
					Integer bookPrice = booksBasket.get(bookName)[0];
					booksBasket.put(bookName, new Integer[] {bookPrice, newCount});
				}
			}
		}
		catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/bookCart.jsp");			
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
