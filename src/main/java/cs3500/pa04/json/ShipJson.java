package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.salvoenums.Direction;
import cs3500.pa03.salvomodel.Coord;

/**
 * Represents a ship record
 *
 * @param coord the starting coord of the ship
 * @param length the length of the ship
 * @param direction the orientation of the ship
 */
public record ShipJson(
    @JsonProperty Coord coord,
    @JsonProperty int length,
    @JsonProperty Direction direction) {
}
