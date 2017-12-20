package avps.labs.client;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import avps.labs.client.stubs.*;

/**
 * Servlet implementation class CurrencyServlet
 */
@WebServlet("/CurrencyServlet")
public class CurrencyServlet extends HttpServlet implements javax.servlet.Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CurrencyServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		// Создаем сервис-фабрику
		CurrencyWSService service = new CurrencyWSService();
		// Создаем прокси-объект для доступа к веб-сервису
		CurrencyWS currencyWS = service.getCurrencyWSPort();

		String currencyFrom = request.getParameter("currencyFrom");
		String currencyTo = request.getParameter("currencyTo");
		request.setAttribute("currencyTo", currencyTo);
		float sum = Float.parseFloat(request.getParameter("sum"));
		request.setAttribute("initSum", sum);
		
		Currency currencyInfo = currencyWS.getCurrencyInfo(currencyFrom);
		request.setAttribute("currencyInfo", currencyInfo);
		Float resultSum = currencyWS.getConvertedSum(currencyInfo, currencyTo, sum);
		request.setAttribute("resultSum", resultSum);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
