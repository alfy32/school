package common;

public class ByteList {

  private static final int STRING_LENGTH_DIGITS = 2;

  private int currentPosition = 0;
  private String message = "";

  public ByteList() {
  }

  public byte[] getBytes() {
    return message.getBytes();
  }

  public void setBytes(byte[] bytes) {
    message = new String(bytes);
  }
  
  public void setBytes(String bytes) {
    message = bytes;
  }

  public void goToStart() {
    this.currentPosition = 0;
  }

  public int readInt(int bytes) {
    if (currentPosition + bytes <= message.length()) {
      String value = message.substring(currentPosition, currentPosition + bytes);

      currentPosition += bytes;

      return Integer.parseInt(value);
    } else {
      throw new IndexOutOfBoundsException("The message is not that long.");
    }
  }

  public void writeInt(int value, int bytes) {
    String toWrite = String.format("%0" + bytes + "d", value);

    message += toWrite;

    currentPosition += bytes;
  }

  public String readString() throws IndexOutOfBoundsException {
    int bytes = this.readInt(STRING_LENGTH_DIGITS);

    if (currentPosition + bytes <= message.length()) {
      String value = message.substring(currentPosition, currentPosition + bytes);

      currentPosition += bytes;

      return value;
    } else {
      throw new IndexOutOfBoundsException("The message is not that long.");
    }
  }

  public void writeString(String value) {
    this.writeInt(value.length(), STRING_LENGTH_DIGITS);

    message += value;

    currentPosition += value.length();
  }

  public boolean readBool() throws Exception {
    char bool = message.charAt(currentPosition);
    currentPosition++;

    if (bool == '0') {
      return false;
    } else if (bool == '1') {
      return true;
    }

    throw new Exception("Invalid boolean value");
  }

  public void writeBool(boolean value) {
    if (value == true) {
      message += "1";
    } else {
      message += "0";
    }
    currentPosition++;
  }

  public char readChar() {
    if (currentPosition < message.length()) {
      char bool = message.charAt(currentPosition);
      currentPosition++;
      return bool;
    }
    throw new IndexOutOfBoundsException("The message is not that long.");
  }

  public void writeChar(char value) {
    message += value;
    currentPosition++;
  }

  @Override
  public String toString() {
    return message;
  }
}
