package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Represents a record of list of shots (coordinates)
 *
 * @param shots in a list in CoordJson form
 */
public record CoordinatesJson(
    @JsonProperty("coordinates") List<CoordJson> shots) {
}
