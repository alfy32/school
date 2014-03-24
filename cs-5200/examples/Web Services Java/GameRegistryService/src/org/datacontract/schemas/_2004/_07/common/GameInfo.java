
package org.datacontract.schemas._2004._07.common;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for GameInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GameInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AliveTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="CommmunicationEndPoint" type="{http://schemas.datacontract.org/2004/07/Common}EndPoint" minOccurs="0"/>
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="Label" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MaxAgentId" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="NextAgentId" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://schemas.datacontract.org/2004/07/Common}GameInfo.GameStatus" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GameInfo", propOrder = {
    "aliveTimestamp",
    "commmunicationEndPoint",
    "id",
    "label",
    "maxAgentId",
    "nextAgentId",
    "status"
})
public class GameInfo {

    @XmlElement(name = "AliveTimestamp")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar aliveTimestamp;
    @XmlElementRef(name = "CommmunicationEndPoint", namespace = "http://schemas.datacontract.org/2004/07/Common", type = JAXBElement.class, required = false)
    protected JAXBElement<EndPoint> commmunicationEndPoint;
    @XmlElement(name = "Id")
    protected Short id;
    @XmlElementRef(name = "Label", namespace = "http://schemas.datacontract.org/2004/07/Common", type = JAXBElement.class, required = false)
    protected JAXBElement<String> label;
    @XmlElement(name = "MaxAgentId")
    protected Short maxAgentId;
    @XmlElement(name = "NextAgentId")
    protected Short nextAgentId;
    @XmlElement(name = "Status")
    protected GameInfoGameStatus status;

    /**
     * Gets the value of the aliveTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAliveTimestamp() {
        return aliveTimestamp;
    }

    /**
     * Sets the value of the aliveTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAliveTimestamp(XMLGregorianCalendar value) {
        this.aliveTimestamp = value;
    }

    /**
     * Gets the value of the commmunicationEndPoint property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link EndPoint }{@code >}
     *     
     */
    public JAXBElement<EndPoint> getCommmunicationEndPoint() {
        return commmunicationEndPoint;
    }

    /**
     * Sets the value of the commmunicationEndPoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link EndPoint }{@code >}
     *     
     */
    public void setCommmunicationEndPoint(JAXBElement<EndPoint> value) {
        this.commmunicationEndPoint = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setId(Short value) {
        this.id = value;
    }

    /**
     * Gets the value of the label property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLabel() {
        return label;
    }

    /**
     * Sets the value of the label property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLabel(JAXBElement<String> value) {
        this.label = value;
    }

    /**
     * Gets the value of the maxAgentId property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getMaxAgentId() {
        return maxAgentId;
    }

    /**
     * Sets the value of the maxAgentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setMaxAgentId(Short value) {
        this.maxAgentId = value;
    }

    /**
     * Gets the value of the nextAgentId property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getNextAgentId() {
        return nextAgentId;
    }

    /**
     * Sets the value of the nextAgentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setNextAgentId(Short value) {
        this.nextAgentId = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link GameInfoGameStatus }
     *     
     */
    public GameInfoGameStatus getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link GameInfoGameStatus }
     *     
     */
    public void setStatus(GameInfoGameStatus value) {
        this.status = value;
    }

}
