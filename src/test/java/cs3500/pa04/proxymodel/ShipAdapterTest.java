package cs3500.pa04.proxymodel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.salvoenums.Direction;
import cs3500.pa03.salvoenums.ShipType;
import cs3500.pa03.salvomodel.Coord;
import cs3500.pa03.salvomodel.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for ShipAdapter
 */
class ShipAdapterTest {

  ShipAdapter shipA;
  Coord coordStart;

  /**
   * Setup for ShipAdapter tests
   */
  @BeforeEach
  void setUp() {
    ShipType shipType = ShipType.SUBMARINE;
    coordStart = new Coord(1, 1);
    Coord[] coords =
        new Coord[] {coordStart, new Coord(1, 2), new Coord(1, 3)};
    Ship ship = new Ship(coords, shipType);

    shipA = new ShipAdapter(ship);
  }

  /**
   * Tests for getCoord
   */
  @Test
  void getCoord() {
    assertEquals(coordStart, shipA.getCoord());
  }

  /**
   * Tests for getLength
   */
  @Test
  void getLength() {
    assertEquals(3, shipA.getLength());
  }

  /**
   * Tests for getDirection
   */
  @Test
  void getDirection() {
    assertEquals(Direction.VERTICAL, shipA.getDirection());

    Coord[] coords =
        new Coord[] {new Coord(0, 2), new Coord(1, 2), new Coord(2, 2)};
    Ship ship = new Ship(coords, ShipType.SUBMARINE);
    shipA = new ShipAdapter(ship);
    assertEquals(Direction.HORIZONTAL, shipA.getDirection());
  }
}