package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

/**
 * Tests for JsonUtils
 */
class JsonUtilsTest {

  /**
   * Tests for serializeRecord
   */
  @Test
  void serializeRecord() {
    String name = "George";
    String gameType = "SINGLE";

    JsonUtils utils = new JsonUtils();

    JoinJson response = new JoinJson(name, gameType);
    JsonNode jsonResponse = utils.serializeRecord(response);

    assertEquals("{\"name\":\"George\",\"game-type\":\"SINGLE\"}",
        jsonResponse.toString());
  }
}