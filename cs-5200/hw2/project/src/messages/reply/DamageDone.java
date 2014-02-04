package messages.reply;

import common.ByteList;
import common.Agent;
import messages.Reply;

public class DamageDone extends Reply {

  //<editor-fold desc="Static Variables" defaultstate="collapsed">
  private static final int ARRAY_SIZE = 2;
  private static final int DAMAGE_SIZE = 4;
  //</editor-fold>
  //<editor-fold desc="Static Functions" defaultstate="collapsed">
  //</editor-fold>

  private Agent[] agentsHit;
  private int[] damages;

  public DamageDone() {
    super(ReplyType.DAMAGE_DONE_REPLY);
  }

  //<editor-fold desc="Create Functions" defaultstate="collapsed">
  public static DamageDone create(ByteList byteList) throws Exception {
    DamageDone damageDone = new DamageDone();

    damageDone.decode(byteList);

    return damageDone;
  }
  //</editor-fold>

  @Override
  public void encode(ByteList byteList) {
    super.encode(byteList);

    byteList.writeInt(agentsHit.length, ARRAY_SIZE);
    for (Agent agent : agentsHit) {
      agent.encode(byteList);
    }
    byteList.writeInt(damages.length, ARRAY_SIZE);
    for (int damage : damages) {
      byteList.writeInt(damage, DAMAGE_SIZE);
    }
  }

  @Override
  public void decode(ByteList byteList) throws Exception {
    super.decode(byteList);

    int size = byteList.readInt(ARRAY_SIZE);
    agentsHit = new Agent[size];
    for (Agent agent : agentsHit) {
      agent = new Agent();
      agent.decode(byteList);
    }

    size = byteList.readInt(ARRAY_SIZE);
    damages = new int[size];
    for (int i = 0; i < size; i++) {
      damages[i] = byteList.readInt(DAMAGE_SIZE);
    }
  }

  //<editor-fold desc="Getter/Setter" defaultstate="collapsed">
  public Agent[] getAgentsHit() {
    return agentsHit;
  }

  public void setAgentsHit(Agent[] agentsHit) {
    this.agentsHit = agentsHit;
  }

  public int[] getDamages() {
    return damages;
  }

  public void setDamages(int[] damages) {
    this.damages = damages;
  }
  //</editor-fold>
}
