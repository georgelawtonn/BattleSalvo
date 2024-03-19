package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Tests for the CoordinatesJson
 */
class CoordinatesJsonTest {

  /**
   * Tests for the CoordinatesJson
   */
  @Test
  void shots() {
    CoordJson coord1 = new CoordJson(0, 1);
    CoordJson coord2 = new CoordJson(0, 2);
    CoordJson coord3 = new CoordJson(0, 3);

    List<CoordJson> listOfShots = List.of(coord1, coord2, coord3);

    CoordinatesJson coordsJson = new CoordinatesJson(listOfShots);
    assertEquals(3, coordsJson.shots().size());
  }
}