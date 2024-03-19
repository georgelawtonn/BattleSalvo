package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.salvoenums.GameResult;
import org.junit.jupiter.api.Test;

/**
 * Tests for EndJson
 */
class EndJsonTest {

  /**
   * Tests for EndJson
   */
  @Test
  void testEnd() {
    GameResult win = GameResult.WIN;
    String reason = "beat all enemy ships";

    EndJson end = new EndJson(win, reason);
    assertEquals(GameResult.WIN, end.result());
    assertEquals("beat all enemy ships", end.reason());
  }

}