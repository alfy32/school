package messages.reply;

import common.Agent;
import common.ByteList;
import messages.Reply;

public class AgentList extends Reply {

  //<editor-fold desc="Static Variables" defaultstate="collapsed">
  private static final int LIST_SIZE = 4;
  //</editor-fold>
  //<editor-fold desc="Static Functions" defaultstate="collapsed">
  //</editor-fold>
  private Agent[] agentList;

  public AgentList() {
    super(ReplyType.ASSIGNMENT_REPLY);
  }

  //<editor-fold desc="Create Functions" defaultstate="collapsed">
  public static AgentList create(ByteList byteList) throws Exception {
    AgentList agentList = new AgentList();

    agentList.decode(byteList);

    return agentList;
  }
  //</editor-fold>

  @Override
  public void encode(ByteList byteList) {
    super.encode(byteList);

    byteList.writeInt(agentList.length, LIST_SIZE);
    for (Agent agent : agentList) {
      agent.encode(byteList);
    }
  }

  @Override
  public void decode(ByteList byteList) throws Exception {
    super.decode(byteList);

    int size = byteList.readInt(LIST_SIZE);
    agentList = new Agent[size];
    for (Agent agent : agentList) {
      agent = new Agent();
      agent.decode(byteList);
    }
  }

  //<editor-fold desc="Getter/Setter" defaultstate="collapsed">
  public Agent[] getAgentList() {
    return agentList;
  }

  public void setAgentList(Agent[] agentList) {
    this.agentList = agentList;
  }
  //</editor-fold>  
}
