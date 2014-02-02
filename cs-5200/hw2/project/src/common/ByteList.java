package common;

public class ByteList {
  private static final int STRING_LENGTH_DIGITS = 2;

  private int currentPosition = 0;
  private String message = "";

  public ByteList() {
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

  public String readString() {
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

  @Override
  public String toString() {
    return message;
  }
}
