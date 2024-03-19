package cs3500;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * A MockSocket
 */
public class MockSocket extends Socket {

  InputStream inputStream;
  OutputStream outputStream;
  boolean isClosed = false;

  /**
   * MockSocket constructor
   *
   * @param inputStream The inputStream
   * @param outputStream The outputStream
   */
  public MockSocket(InputStream inputStream, OutputStream outputStream) {
    this.inputStream = inputStream;
    this.outputStream = outputStream;
  }

  /**
   * A inputStream getter
   *
   * @return This inputStream
   */
  @Override
  public InputStream getInputStream() {
    return this.inputStream;
  }

  /**
   * A outputStream getter
   *
   * @return This outputStream
   */
  @Override
  public OutputStream getOutputStream() {
    return this.outputStream;
  }

  /**
   * Determines if this MockSocket is closed
   *
   * @return boolean that indicates closure
   */
  @Override
  public boolean isClosed() {
    return this.isClosed;
  }
}
