package cs3500;

import java.io.OutputStream;

/**
 * A MockOutputStream
 */
public class MockOutputStream extends OutputStream {
  private StringBuilder stringBuilder = new StringBuilder();

  /**
   * A writer for the MockOutputStream
   *
   * @param b   the {@code byte}.
   */
  @Override
  public void write(int b) {
    stringBuilder.append((char) b);
  }

  /**
   * Another writer for the MockOutputStream
   *
   * @param b     the data.
   * @param off   the start offset in the data.
   * @param len   the number of bytes to write.
   */
  @Override
  public void write(byte[] b, int off, int len)  {
    String str = new String(b, off, len);
    stringBuilder.append(str);
  }

  /**
   * A method to get the output of the MockOutputStream
   *
   * @return the output
   */
  public String getOutput() {
    return stringBuilder.toString();
  }
}

