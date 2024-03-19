package cs3500.pa04.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utils class used to hold static methods that help with serializing and deserializing JSON.
 */
public class JsonUtils {

  /**
   * Converts a given record object to a JsonNode.
   *
   * @param record the record to convert
   * @return the JsonNode representation of the given record
   * @throws IllegalArgumentException if the record could not be converted correctly
   */
  public static JsonNode serializeRecord(Record record) {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.convertValue(record, JsonNode.class);
  }
}