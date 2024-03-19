package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.salvoenums.Direction;
import cs3500.pa03.salvomodel.Coord;
import org.junit.jupiter.api.Test;

/**
 * Tests for ShipJson
 */
class ShipJsonTest {

  /**
   * Tests for ShipJson
   */
  @Test
  void testShip() {
    Coord coord1 = new Coord(0, 1);
    ShipJson ship = new ShipJson(coord1, 5, Direction.HORIZONTAL);
    assertEquals(1, ship.coord().getY());
    assertEquals(5, ship.length());
    assertEquals(Direction.HORIZONTAL, ship.direction());
  }

}