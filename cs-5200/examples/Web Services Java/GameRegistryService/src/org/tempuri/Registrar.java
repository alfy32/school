
package org.tempuri;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "Registrar", targetNamespace = "http://tempuri.org/", wsdlLocation = "http://cs5200web.serv.usu.edu/Registrar.svc?wsdl")
public class Registrar
    extends Service
{

    private final static URL REGISTRAR_WSDL_LOCATION;
    private final static WebServiceException REGISTRAR_EXCEPTION;
    private final static QName REGISTRAR_QNAME = new QName("http://tempuri.org/", "Registrar");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://cs5200web.serv.usu.edu/Registrar.svc?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        REGISTRAR_WSDL_LOCATION = url;
        REGISTRAR_EXCEPTION = e;
    }

    public Registrar() {
        super(__getWsdlLocation(), REGISTRAR_QNAME);
    }

    public Registrar(WebServiceFeature... features) {
        super(__getWsdlLocation(), REGISTRAR_QNAME, features);
    }

    public Registrar(URL wsdlLocation) {
        super(wsdlLocation, REGISTRAR_QNAME);
    }

    public Registrar(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, REGISTRAR_QNAME, features);
    }

    public Registrar(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Registrar(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns IRegistrar
     */
    @WebEndpoint(name = "BasicHttpBinding_IRegistrar")
    public IRegistrar getBasicHttpBindingIRegistrar() {
        return super.getPort(new QName("http://tempuri.org/", "BasicHttpBinding_IRegistrar"), IRegistrar.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IRegistrar
     */
    @WebEndpoint(name = "BasicHttpBinding_IRegistrar")
    public IRegistrar getBasicHttpBindingIRegistrar(WebServiceFeature... features) {
        return super.getPort(new QName("http://tempuri.org/", "BasicHttpBinding_IRegistrar"), IRegistrar.class, features);
    }

    private static URL __getWsdlLocation() {
        if (REGISTRAR_EXCEPTION!= null) {
            throw REGISTRAR_EXCEPTION;
        }
        return REGISTRAR_WSDL_LOCATION;
    }

}
