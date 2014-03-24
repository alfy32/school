
package org.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import Common.GameInfo;



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
 *         &lt;element name="GetGamesResult" type="{http://schemas.datacontract.org/2004/07/Common}ArrayOfGameInfo" minOccurs="0"/>
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
    "getGamesResult"
})
@XmlRootElement(name = "GetGamesResponse")
public class GetGamesResponse {

    @XmlElementRef(name = "GetGamesResult", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<GameInfo> getGamesResult;

    /**
     * Gets the value of the getGamesResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfGameInfo }{@code >}
     *     
     */
    public JAXBElement<GameInfo> getGetGamesResult() {
        return getGamesResult;
    }

    /**
     * Sets the value of the getGamesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfGameInfo }{@code >}
     *     
     */
    public void setGetGamesResult(JAXBElement<GameInfo> value) {
        this.getGamesResult = value;
    }

}
