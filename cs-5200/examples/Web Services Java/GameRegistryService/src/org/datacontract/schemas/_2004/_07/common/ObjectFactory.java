
package org.datacontract.schemas._2004._07.common;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.datacontract.schemas._2004._07.common package. 
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

    private final static QName _DistributableObject_QNAME = new QName("http://schemas.datacontract.org/2004/07/Common", "DistributableObject");
    private final static QName _ArrayOfGameInfo_QNAME = new QName("http://schemas.datacontract.org/2004/07/Common", "ArrayOfGameInfo");
    private final static QName _GameInfo_QNAME = new QName("http://schemas.datacontract.org/2004/07/Common", "GameInfo");
    private final static QName _GameInfoGameStatus_QNAME = new QName("http://schemas.datacontract.org/2004/07/Common", "GameInfo.GameStatus");
    private final static QName _EndPoint_QNAME = new QName("http://schemas.datacontract.org/2004/07/Common", "EndPoint");
    private final static QName _GameInfoCommmunicationEndPoint_QNAME = new QName("http://schemas.datacontract.org/2004/07/Common", "CommmunicationEndPoint");
    private final static QName _GameInfoLabel_QNAME = new QName("http://schemas.datacontract.org/2004/07/Common", "Label");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.datacontract.schemas._2004._07.common
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GameInfo }
     * 
     */
    public GameInfo createGameInfo() {
        return new GameInfo();
    }

    /**
     * Create an instance of {@link ArrayOfGameInfo }
     * 
     */
    public ArrayOfGameInfo createArrayOfGameInfo() {
        return new ArrayOfGameInfo();
    }

    /**
     * Create an instance of {@link EndPoint }
     * 
     */
    public EndPoint createEndPoint() {
        return new EndPoint();
    }

    /**
     * Create an instance of {@link DistributableObject }
     * 
     */
    public DistributableObject createDistributableObject() {
        return new DistributableObject();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DistributableObject }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Common", name = "DistributableObject")
    public JAXBElement<DistributableObject> createDistributableObject(DistributableObject value) {
        return new JAXBElement<DistributableObject>(_DistributableObject_QNAME, DistributableObject.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfGameInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Common", name = "ArrayOfGameInfo")
    public JAXBElement<ArrayOfGameInfo> createArrayOfGameInfo(ArrayOfGameInfo value) {
        return new JAXBElement<ArrayOfGameInfo>(_ArrayOfGameInfo_QNAME, ArrayOfGameInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GameInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Common", name = "GameInfo")
    public JAXBElement<GameInfo> createGameInfo(GameInfo value) {
        return new JAXBElement<GameInfo>(_GameInfo_QNAME, GameInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GameInfoGameStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Common", name = "GameInfo.GameStatus")
    public JAXBElement<GameInfoGameStatus> createGameInfoGameStatus(GameInfoGameStatus value) {
        return new JAXBElement<GameInfoGameStatus>(_GameInfoGameStatus_QNAME, GameInfoGameStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EndPoint }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Common", name = "EndPoint")
    public JAXBElement<EndPoint> createEndPoint(EndPoint value) {
        return new JAXBElement<EndPoint>(_EndPoint_QNAME, EndPoint.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EndPoint }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Common", name = "CommmunicationEndPoint", scope = GameInfo.class)
    public JAXBElement<EndPoint> createGameInfoCommmunicationEndPoint(EndPoint value) {
        return new JAXBElement<EndPoint>(_GameInfoCommmunicationEndPoint_QNAME, EndPoint.class, GameInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Common", name = "Label", scope = GameInfo.class)
    public JAXBElement<String> createGameInfoLabel(String value) {
        return new JAXBElement<String>(_GameInfoLabel_QNAME, String.class, GameInfo.class, value);
    }

}
