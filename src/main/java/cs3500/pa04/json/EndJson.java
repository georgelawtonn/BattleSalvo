package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.salvoenums.GameResult;

/**
 * Represents a record of the end message
 *
 * @param result the result of the game
 * @param reason the reason to why the result of the game is the result
 */
public record EndJson(
    @JsonProperty("result") GameResult result,
    @JsonProperty("reason") String reason) {
}
