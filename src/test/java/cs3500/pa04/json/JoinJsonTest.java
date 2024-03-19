package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for JoinJson
 */
class JoinJsonTest {

  /**
   * Tests for JoinJson
   */
  @Test
  void join() {
    String name = "George";
    String gameType = "SINGLE";
    JoinJson join = new JoinJson(name, gameType);
    assertEquals("George", join.name());
    assertEquals("SINGLE", join.gameType());
  }
}