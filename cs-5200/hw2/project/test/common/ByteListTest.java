package common;

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

  @Test
  public void testBool() throws Exception {
    ByteList byteList = new ByteList();

    boolean testBool = true;

    byteList.writeBool(testBool);
    byteList.goToStart();
    boolean readBool = byteList.readBool();

    assertEquals(testBool, readBool);
  }

  @Test
  public void testBoolRead() throws Exception {
    ByteList byteList = new ByteList();

    byteList.setBytes("0");
    byteList.goToStart();

    assertFalse(byteList.readBool());
  }

  @Test
  public void testChar() throws Exception {
    ByteList byteList = new ByteList();

    char testChar = 'A';

    byteList.writeChar(testChar);
    byteList.goToStart();
    char readChar = byteList.readChar();

    assertEquals(testChar, readChar);
  }

  @Test
  public void testCharRead() throws Exception {
    ByteList byteList = new ByteList();
    
    byteList.setBytes("F");
    byteList.goToStart();

    assertEquals('F', byteList.readChar());
  }
}
