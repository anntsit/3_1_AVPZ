package avps.labs.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import avps.labs.client.stubs.*;

public class CurrenciesFilter implements Filter {

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {		
		// Создаем сервис-фабрику
		CurrencyWSService service = new CurrencyWSService();
		// Создаем прокси-объект для доступа к веб-сервису 
		CurrencyWS currencyWS = service.getCurrencyWSPort();
		
		List<String> currencies = currencyWS.getCurrencies();
		request.setAttribute("currencies", currencies);

		// Направляем запрос далее по цепочке 
		chain.doFilter(request, response);
	}

    public void init(FilterConfig arg0) throws ServletException {}
}

