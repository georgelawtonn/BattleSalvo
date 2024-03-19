package cs3500.pa03.salvoenums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for GameResult enum
 */
class GameResultTest {
  GameResult win;
  GameResult lose;
  GameResult draw;

  /**
   * Setup for testing
   */
  @BeforeEach
  public void setUp() {
    win = GameResult.WIN;
    lose = GameResult.LOSE;
    draw = GameResult.DRAW;
  }

  /**
   * Tests for ordinal values
   */
  @Test
  void values() {
    assertEquals(0, win.ordinal());
    assertEquals(1, lose.ordinal());
    assertEquals(2, draw.ordinal());
  }

  /**
   * Tests for name
   */
  @Test
  void valueOf() {
    assertEquals("WIN", win.name());
    assertEquals("LOSE", lose.name());
    assertEquals("DRAW", draw.name());
  }
}