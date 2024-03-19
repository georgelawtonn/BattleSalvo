package cs3500.pa03.salvoenums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * represents the tester class for the direction enum
 */
public class DirectionTest {
  Direction hor = Direction.HORIZONTAL;
  Direction ver = Direction.VERTICAL;

  /**
   * tests the values of the direction
   */
  @Test
  void test() {
    assertEquals("HORIZONTAL", hor.toString());
    assertEquals("VERTICAL", ver.toString());
  }



}
