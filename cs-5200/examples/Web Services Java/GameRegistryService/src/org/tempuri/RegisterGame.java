
package org.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.common.EndPoint;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="label" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="publicEP" type="{http://schemas.datacontract.org/2004/07/Common}EndPoint" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "label",
    "publicEP"
})
@XmlRootElement(name = "RegisterGame")
public class RegisterGame {

    @XmlElementRef(name = "label", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> label;
    @XmlElementRef(name = "publicEP", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<EndPoint> publicEP;

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
     * Gets the value of the publicEP property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link EndPoint }{@code >}
     *     
     */
    public JAXBElement<EndPoint> getPublicEP() {
        return publicEP;
    }

    /**
     * Sets the value of the publicEP property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link EndPoint }{@code >}
     *     
     */
    public void setPublicEP(JAXBElement<EndPoint> value) {
        this.publicEP = value;
    }

}
