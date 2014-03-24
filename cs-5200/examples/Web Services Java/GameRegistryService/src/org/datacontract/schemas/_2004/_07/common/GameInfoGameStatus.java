
package org.datacontract.schemas._2004._07.common;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GameInfo.GameStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="GameInfo.GameStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NOT_INITIAlIZED"/>
 *     &lt;enumeration value="AVAILABLE"/>
 *     &lt;enumeration value="RUNNING"/>
 *     &lt;enumeration value="COMPLETED"/>
 *     &lt;enumeration value="DEAD"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "GameInfo.GameStatus")
@XmlEnum
public enum GameInfoGameStatus {

    @XmlEnumValue("NOT_INITIAlIZED")
    NOT_INITI_AL_IZED("NOT_INITIAlIZED"),
    AVAILABLE("AVAILABLE"),
    RUNNING("RUNNING"),
    COMPLETED("COMPLETED"),
    DEAD("DEAD");
    private final String value;

    GameInfoGameStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GameInfoGameStatus fromValue(String v) {
        for (GameInfoGameStatus c: GameInfoGameStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
