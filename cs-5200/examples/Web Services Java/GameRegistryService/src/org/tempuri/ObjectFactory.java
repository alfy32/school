
package org.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import org.datacontract.schemas._2004._07.common.ArrayOfGameInfo;
import org.datacontract.schemas._2004._07.common.EndPoint;
import org.datacontract.schemas._2004._07.common.GameInfo;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.tempuri package. 
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

    private final static QName _RegisterGameResponseRegisterGameResult_QNAME = new QName("http://tempuri.org/", "RegisterGameResult");
    private final static QName _GetGamesResponseGetGamesResult_QNAME = new QName("http://tempuri.org/", "GetGamesResult");
    private final static QName _RegisterGamePublicEP_QNAME = new QName("http://tempuri.org/", "publicEP");
    private final static QName _RegisterGameLabel_QNAME = new QName("http://tempuri.org/", "label");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.tempuri
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ChangeStatus }
     * 
     */
    public ChangeStatus createChangeStatus() {
        return new ChangeStatus();
    }

    /**
     * Create an instance of {@link RegisterGameResponse }
     * 
     */
    public RegisterGameResponse createRegisterGameResponse() {
        return new RegisterGameResponse();
    }

    /**
     * Create an instance of {@link ChangeStatusResponse }
     * 
     */
    public ChangeStatusResponse createChangeStatusResponse() {
        return new ChangeStatusResponse();
    }

    /**
     * Create an instance of {@link AmAliveResponse }
     * 
     */
    public AmAliveResponse createAmAliveResponse() {
        return new AmAliveResponse();
    }

    /**
     * Create an instance of {@link AmAlive }
     * 
     */
    public AmAlive createAmAlive() {
        return new AmAlive();
    }

    /**
     * Create an instance of {@link GetGames }
     * 
     */
    public GetGames createGetGames() {
        return new GetGames();
    }

    /**
     * Create an instance of {@link GetGamesResponse }
     * 
     */
    public GetGamesResponse createGetGamesResponse() {
        return new GetGamesResponse();
    }

    /**
     * Create an instance of {@link RegisterGame }
     * 
     */
    public RegisterGame createRegisterGame() {
        return new RegisterGame();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GameInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "RegisterGameResult", scope = RegisterGameResponse.class)
    public JAXBElement<GameInfo> createRegisterGameResponseRegisterGameResult(GameInfo value) {
        return new JAXBElement<GameInfo>(_RegisterGameResponseRegisterGameResult_QNAME, GameInfo.class, RegisterGameResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfGameInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "GetGamesResult", scope = GetGamesResponse.class)
    public JAXBElement<ArrayOfGameInfo> createGetGamesResponseGetGamesResult(ArrayOfGameInfo value) {
        return new JAXBElement<ArrayOfGameInfo>(_GetGamesResponseGetGamesResult_QNAME, ArrayOfGameInfo.class, GetGamesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EndPoint }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "publicEP", scope = RegisterGame.class)
    public JAXBElement<EndPoint> createRegisterGamePublicEP(EndPoint value) {
        return new JAXBElement<EndPoint>(_RegisterGamePublicEP_QNAME, EndPoint.class, RegisterGame.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "label", scope = RegisterGame.class)
    public JAXBElement<String> createRegisterGameLabel(String value) {
        return new JAXBElement<String>(_RegisterGameLabel_QNAME, String.class, RegisterGame.class, value);
    }

}
