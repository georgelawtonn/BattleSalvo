package cs3500;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * A MockInputStream
 */
public class MockInputStream extends InputStream {
  private ByteArrayInputStream byteArrayInputStream;

  /**
   * MockInputStream constructor
   *
   * @param data The data to be passed into the byteArrayInputStream
   */
  public MockInputStream(String data) {
    byte[] bytes = data.getBytes();
    byteArrayInputStream = new ByteArrayInputStream(bytes);
  }

  /**
   * A read method for the data in this MockInputStream
   *
   * @return An int representing the byte
   */
  @Override
  public int read() {
    return byteArrayInputStream.read();
  }
}
