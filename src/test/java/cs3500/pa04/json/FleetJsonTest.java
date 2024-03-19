package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.salvoenums.Direction;
import cs3500.pa03.salvomodel.Coord;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Tests for FleetJson
 */
class FleetJsonTest {

  /**
   * Tests for FleetJson
   */
  @Test
  void ships() {
    Coord coord1 = new Coord(0, 1);
    ShipJson ship1 = new ShipJson(coord1, 5, Direction.HORIZONTAL);

    Coord coord2 = new Coord(1, 1);
    ShipJson ship2 = new ShipJson(coord2, 6, Direction.VERTICAL);

    Coord coord3 = new Coord(3, 3);
    ShipJson ship3 = new ShipJson(coord3, 3, Direction.HORIZONTAL);

    Coord coord4 = new Coord(4, 2);
    ShipJson ship4 = new ShipJson(coord4, 4, Direction.HORIZONTAL);

    List<ShipJson> ships = List.of(ship1, ship2, ship3, ship4);
    FleetJson fleet = new FleetJson(ships);
    assertEquals(4, fleet.ships().size());
  }
}