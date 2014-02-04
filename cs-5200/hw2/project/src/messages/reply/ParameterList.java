package messages.reply;

import common.ByteList;
import common.Parameter;
import messages.Reply;

public class ParameterList extends Reply {

  //<editor-fold desc="Static Variables" defaultstate="collapsed">
  public static final int PARAMETER_SIZE = 2;
  //</editor-fold>
  //<editor-fold desc="Static Functions" defaultstate="collapsed">
  //</editor-fold>

  private Parameter[] parameters;

  public ParameterList() {
    super(ReplyType.PARAMETER_LIST_REPLY);
  }

  //<editor-fold desc="Create Functions" defaultstate="collapsed">
  public static ParameterList create(ByteList byteList) throws Exception {
    ParameterList parameterList = new ParameterList();

    parameterList.decode(byteList);

    return parameterList;
  }
  //</editor-fold>

  @Override
  public void encode(ByteList byteList) {
    super.encode(byteList);

    byteList.writeInt(parameters.length, PARAMETER_SIZE);
    for (Parameter parameter : parameters) {
      parameter.encode(byteList);
    }
  }

  @Override
  public void decode(ByteList byteList) throws Exception {
    super.decode(byteList);

    int size = byteList.readInt(PARAMETER_SIZE);
    parameters = new Parameter[size];
    for (Parameter parameter : parameters) {
      parameter = new Parameter();
      parameter.decode(byteList);
    }
  }

  //<editor-fold desc="Getter/Setter" defaultstate="collapsed">
  public Parameter[] getParameters() {
    return parameters;
  }

  public void setParameters(Parameter[] parameters) {
    this.parameters = parameters;
  }
  //</editor-fold>
}
