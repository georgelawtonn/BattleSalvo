package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * represents a coord record
 *
 * @param x position of the coord
 * @param y position of the coord
 */
public record CoordJson(
    @JsonProperty("x") int x,
    @JsonProperty("y") int y) {
}
