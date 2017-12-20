
package avps.labs.client.stubs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for currency complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="currency">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="currencyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EUR" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="UAH" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="USD" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "currency", propOrder = {
    "currencyName",
    "eur",
    "uah",
    "usd"
})
public class Currency {

    protected String currencyName;
    @XmlElement(name = "EUR")
    protected float eur;
    @XmlElement(name = "UAH")
    protected float uah;
    @XmlElement(name = "USD")
    protected float usd;

    /**
     * Gets the value of the currencyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyName() {
        return currencyName;
    }

    /**
     * Sets the value of the currencyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyName(String value) {
        this.currencyName = value;
    }

    /**
     * Gets the value of the eur property.
     * 
     */
    public float getEUR() {
        return eur;
    }

    /**
     * Sets the value of the eur property.
     * 
     */
    public void setEUR(float value) {
        this.eur = value;
    }

    /**
     * Gets the value of the uah property.
     * 
     */
    public float getUAH() {
        return uah;
    }

    /**
     * Sets the value of the uah property.
     * 
     */
    public void setUAH(float value) {
        this.uah = value;
    }

    /**
     * Gets the value of the usd property.
     * 
     */
    public float getUSD() {
        return usd;
    }

    /**
     * Sets the value of the usd property.
     * 
     */
    public void setUSD(float value) {
        this.usd = value;
    }

}
