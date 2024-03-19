package cs3500.pa03.salvoenums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for VisualType enum
 */
class VisualTypeTest {
  VisualType user;
  VisualType opponent;
  VisualType initial;

  /**
   * Setup for testing
   */
  @BeforeEach
  public void setUp() {
    user = VisualType.USER;
    opponent = VisualType.OPPONENT;
    initial = VisualType.INITIAL;
  }

  /**
   * Tests for ordinal values
   */
  @Test
  void values() {
    assertEquals(0, initial.ordinal());
    assertEquals(1, user.ordinal());
    assertEquals(2, opponent.ordinal());
  }

  /**
   * Tests for name
   */
  @Test
  void valueOf() {
    assertEquals("INITIAL", initial.name());
    assertEquals("USER", user.name());
    assertEquals("OPPONENT", opponent.name());
  }
}