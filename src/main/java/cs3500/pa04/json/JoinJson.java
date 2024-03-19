package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the join message record
 *
 * @param name the name of the player
 * @param gameType the game mode that the player wants to play
 */
public record JoinJson(
    @JsonProperty("name") String name,
    @JsonProperty("game-type") String gameType) {
}
