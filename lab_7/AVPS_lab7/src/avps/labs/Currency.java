package avps.labs;

public class Currency {
	private String currencyName;
	private float USD;
	private float EUR;
	private float UAH;
	
	public Currency() {}

	public Currency(String currencyName, float USD, float EUR, float UAH) {
		this.currencyName = currencyName;
		this.USD = USD;
		this.EUR = EUR;
		this.UAH = UAH;
	}

	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public float getUSD() {
		return USD;
	}
	public void setUSD(float USD) {
		this.USD = USD;
	}

	public float getEUR() {
		return EUR;
	}
	public void setEUR(float EUR) {
		this.EUR = EUR;
	}

	public float getUAH() {
		return UAH;
	}
	public void setUAH(float UAH) {
		this.UAH = UAH;
	}
}
