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
		// ������� ������-�������
		CurrencyWSService service = new CurrencyWSService();
		// ������� ������-������ ��� ������� � ���-������� 
		CurrencyWS currencyWS = service.getCurrencyWSPort();
		
		List<String> currencies = currencyWS.getCurrencies();
		request.setAttribute("currencies", currencies);

		// ���������� ������ ����� �� ������� 
		chain.doFilter(request, response);
	}

    public void init(FilterConfig arg0) throws ServletException {}
}

