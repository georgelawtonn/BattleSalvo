package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for CoordJson
 */
class CoordJsonTest {

  /**
   * Tests for CoordJson
   */
  @Test
  void testCoordJson() {
    CoordJson coord = new CoordJson(1, 2);

    assertEquals(1, coord.x());
    assertEquals(2, coord.y());
  }

}