package cs3500.pa03.salvoenums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for ShipState enum
 */
class ShipStateTest {
  ShipState destroyed;
  ShipState alive;

  /**
   * Setup for testing
   */
  @BeforeEach
  public void setUp() {
    destroyed = ShipState.DESTROYED;
    alive = ShipState.ALIVE;
  }

  /**
   * Tests for ordinal values
   */
  @Test
  void values() {
    assertEquals(0, destroyed.ordinal());
    assertEquals(1, alive.ordinal());
  }

  /**
   * Tests for name
   */
  @Test
  void valueOf() {
    assertEquals("DESTROYED", destroyed.name());
    assertEquals("ALIVE", alive.name());
  }
}