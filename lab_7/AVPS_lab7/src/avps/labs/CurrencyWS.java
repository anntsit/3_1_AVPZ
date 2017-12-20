package avps.labs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class CurrencyWS {
		private Connection getConnection() throws Exception {
			System.gc();
			// Подгрузка драйвера БД Derby
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
			// Получение соединения с БД
			return DriverManager.getConnection("jdbc:derby://localhost:1527/myDB;create=true;user=me;password=mine");
		}

		/**
		 * Возвращает список валют, по которым 
		 * имеется информация
		 * @return список валют
		 */
		@WebMethod
		public List<String> getCurrencies() {
			try {
				List<String> currencies = new ArrayList<String>();
				
				Connection con = getConnection();
				ResultSet rs = con.createStatement().executeQuery("SELECT currency FROM exchange");
				while (rs.next())
					currencies.add(rs.getString(1));
				rs.close();
				con.close();
				
				return currencies;
			} 
			catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
		}

		/**
		 * Возвращает валюту
		 * @param city аббревиатура валюты
		 * @return валюта
		 */
		@WebMethod
		public Currency getCurrencyInfo(String currency) {
			try {
				Currency currencyInfo = null;

				Connection con = getConnection();
				ResultSet rs = con.createStatement().executeQuery(
			   		"SELECT currency, USD, EUR, UAH " +
	 				"FROM exchange WHERE currency like '" + currency + "'");
				if (rs.next())
					currencyInfo = new Currency(rs.getString(1), rs.getFloat(2), 
													rs.getFloat(3), rs.getFloat(4));
				rs.close();
				con.close();

				return currencyInfo;
			} 
			catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
		}
		public Float getConvertedSum(Currency currentCurrency, String currencyTo, float sum) {
			try {
				Float convertedSum = null;
				float coefficient = 0;
				
				Connection con = getConnection();
				ResultSet rs = con.createStatement().executeQuery(
				   		"SELECT " + currencyTo +
		 				" FROM exchange WHERE currency like '" + currentCurrency.getCurrencyName() + "'");
				if (rs.next())
					coefficient = rs.getFloat(1);
				
				rs.close();
				con.close();
				
				if (coefficient != 0)
					convertedSum = sum * coefficient;
				return convertedSum;
			} 
			catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
		}
}
