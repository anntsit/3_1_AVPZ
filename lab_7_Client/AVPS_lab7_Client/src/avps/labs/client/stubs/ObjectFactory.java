
package avps.labs.client.stubs;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the avps.labs.client.stubs package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetCurrenciesResponse_QNAME = new QName("http://labs.avps/", "getCurrenciesResponse");
    private final static QName _GetCurrencyInfo_QNAME = new QName("http://labs.avps/", "getCurrencyInfo");
    private final static QName _GetConvertedSum_QNAME = new QName("http://labs.avps/", "getConvertedSum");
    private final static QName _GetCurrencyInfoResponse_QNAME = new QName("http://labs.avps/", "getCurrencyInfoResponse");
    private final static QName _GetCurrencies_QNAME = new QName("http://labs.avps/", "getCurrencies");
    private final static QName _GetConvertedSumResponse_QNAME = new QName("http://labs.avps/", "getConvertedSumResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: avps.labs.client.stubs
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetConvertedSumResponse }
     * 
     */
    public GetConvertedSumResponse createGetConvertedSumResponse() {
        return new GetConvertedSumResponse();
    }

    /**
     * Create an instance of {@link GetCurrencies }
     * 
     */
    public GetCurrencies createGetCurrencies() {
        return new GetCurrencies();
    }

    /**
     * Create an instance of {@link GetCurrencyInfoResponse }
     * 
     */
    public GetCurrencyInfoResponse createGetCurrencyInfoResponse() {
        return new GetCurrencyInfoResponse();
    }

    /**
     * Create an instance of {@link GetConvertedSum }
     * 
     */
    public GetConvertedSum createGetConvertedSum() {
        return new GetConvertedSum();
    }

    /**
     * Create an instance of {@link GetCurrencyInfo }
     * 
     */
    public GetCurrencyInfo createGetCurrencyInfo() {
        return new GetCurrencyInfo();
    }

    /**
     * Create an instance of {@link GetCurrenciesResponse }
     * 
     */
    public GetCurrenciesResponse createGetCurrenciesResponse() {
        return new GetCurrenciesResponse();
    }

    /**
     * Create an instance of {@link Currency }
     * 
     */
    public Currency createCurrency() {
        return new Currency();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCurrenciesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://labs.avps/", name = "getCurrenciesResponse")
    public JAXBElement<GetCurrenciesResponse> createGetCurrenciesResponse(GetCurrenciesResponse value) {
        return new JAXBElement<GetCurrenciesResponse>(_GetCurrenciesResponse_QNAME, GetCurrenciesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCurrencyInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://labs.avps/", name = "getCurrencyInfo")
    public JAXBElement<GetCurrencyInfo> createGetCurrencyInfo(GetCurrencyInfo value) {
        return new JAXBElement<GetCurrencyInfo>(_GetCurrencyInfo_QNAME, GetCurrencyInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetConvertedSum }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://labs.avps/", name = "getConvertedSum")
    public JAXBElement<GetConvertedSum> createGetConvertedSum(GetConvertedSum value) {
        return new JAXBElement<GetConvertedSum>(_GetConvertedSum_QNAME, GetConvertedSum.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCurrencyInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://labs.avps/", name = "getCurrencyInfoResponse")
    public JAXBElement<GetCurrencyInfoResponse> createGetCurrencyInfoResponse(GetCurrencyInfoResponse value) {
        return new JAXBElement<GetCurrencyInfoResponse>(_GetCurrencyInfoResponse_QNAME, GetCurrencyInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCurrencies }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://labs.avps/", name = "getCurrencies")
    public JAXBElement<GetCurrencies> createGetCurrencies(GetCurrencies value) {
        return new JAXBElement<GetCurrencies>(_GetCurrencies_QNAME, GetCurrencies.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetConvertedSumResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://labs.avps/", name = "getConvertedSumResponse")
    public JAXBElement<GetConvertedSumResponse> createGetConvertedSumResponse(GetConvertedSumResponse value) {
        return new JAXBElement<GetConvertedSumResponse>(_GetConvertedSumResponse_QNAME, GetConvertedSumResponse.class, null, value);
    }

}
