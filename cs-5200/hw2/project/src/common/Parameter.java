package common;

public class Parameter {

  //<editor-fold desc="Static Variables" defaultstate="collapsed">
  //</editor-fold>
  //<editor-fold desc="Static Functions" defaultstate="collapsed">
  //</editor-fold>
  private String parameter;
  private String value;

  public Parameter() {
    this.parameter = "";
    this.value = "";
  }

  //<editor-fold desc="Create Functions" defaultstate="collapsed">
  public static Parameter create() {
    return new Parameter();
  }

  public static Parameter create(ByteList byteList) throws Exception {
    Parameter coordinate = new Parameter();

    coordinate.decode(byteList);

    return coordinate;
  }
  //</editor-fold>

  public void encode(ByteList byteList) {
    byteList.writeString(parameter);
    byteList.writeString(value);
  }

  public void decode(ByteList byteList) throws Exception {
    parameter = byteList.readString();
    value = byteList.readString();
  }

  //<editor-fold desc="Getters/Setters" defaultstate="collapsed">
  public String getParameter() {
    return parameter;
  }

  public void setParameter(String parameter) {
    this.parameter = parameter;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
  //</editor-fold>
}
