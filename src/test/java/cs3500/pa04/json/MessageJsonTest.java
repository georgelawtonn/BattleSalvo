package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

/**
 * Tests for MessageJson
 */
class MessageJsonTest {

  /**
   * Tests for MessageJson
   */
  @Test
  void methodName() {
    String name = "George";
    String gameType = "SINGLE";

    JoinJson join = new JoinJson(name, gameType);
    JsonNode jsonResponse = JsonUtils.serializeRecord(join);

    MessageJson message = new MessageJson("join", jsonResponse);

    assertEquals("join", message.methodName());
    assertEquals("{\"name\":\"George\",\"game-type\":\"SINGLE\"}",
        message.arguments().toString());
  }
}