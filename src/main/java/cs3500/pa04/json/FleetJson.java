package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Represents a record that stores a list of ships for the game
 *
 * @param ships in a list of ShipJsons
 */
public record FleetJson(
    @JsonProperty("fleet") List<ShipJson> ships) {
}
