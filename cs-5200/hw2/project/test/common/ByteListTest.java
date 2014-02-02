package common;

import java.nio.ByteBuffer;
import org.junit.Test;
import static org.junit.Assert.*;

public class ByteListTest {

  public ByteListTest() {
  }

  @Test
  public void testReadWriteInt() {
    int value = 13;
    int size = 4;

    ByteList instance = new ByteList();

    instance.writeInt(value, size);
    instance.goToStart();

    assertEquals(value, instance.readInt(size));
  }

  @Test
  public void testMultipleReadWriteInt() {
    int value1 = 1;
    int value2 = 203;
    int size1 = 1;
    int size2 = 4;

    ByteList instance = new ByteList();

    instance.writeInt(value1, size1);
    instance.writeInt(value2, size2);
    instance.goToStart();

    assertEquals(value1, instance.readInt(size1));
    assertEquals(value2, instance.readInt(size2));
  }

  @Test
  public void testReadWriteString() {
    String value = "Hi mom!";

    ByteList instance = new ByteList();

    instance.writeString(value);
    instance.goToStart();

    assertEquals(value, instance.readString());
  }

  @Test
  public void testMultipleReadWriteString() {
    String value1 = "Hi mom!";
    String value2 = "ok";

    ByteList instance = new ByteList();

    instance.writeString(value1);
    instance.writeString(value2);
    instance.goToStart();

    assertEquals(value1, instance.readString());
    assertEquals(value2, instance.readString());
  }
}
