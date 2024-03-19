package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.salvoenums.ShipType;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the setup message record in this format
 *
 * @param width the width of the board
 * @param height the height of the board
 * @param specs the number of ships per type for the game
 */
public record SetupJson(
    @JsonProperty("width") int width,
    @JsonProperty("height") int height,
    @JsonProperty("fleet-spec") Map<ShipType, Integer> specs) {
}
