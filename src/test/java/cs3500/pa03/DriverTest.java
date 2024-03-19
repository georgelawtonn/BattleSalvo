package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa04.Driver;
import java.io.IOException;
import org.junit.jupiter.api.Test;

/**
 * Unable to test Driver
 */
class DriverTest {

  /**
   * Tests for main/driver
   */
  @Test
  public void test() throws IOException {
    String[] args = {"0.0.0.0", "bad"};
    Driver driver = new Driver();
    assertThrows(IOException.class, () -> {
      driver.main(args);
    });

    String[] threeArgs = {"0.0.0.0", "bad", "badAgain"};
    assertThrows(IOException.class, () -> {
      driver.main(threeArgs);
    });
  }
}