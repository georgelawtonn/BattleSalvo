package cs3500.pa03.salvomodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa03.salvoenums.ShipState;
import cs3500.pa03.salvoenums.ShipType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for Ship
 */
public class ShipTest {
  private Ship ship;

  /**
   * Setup for testing
   */
  @BeforeEach
  public void setUp() {
    ShipType shipType = ShipType.SUBMARINE;
    Coord[] coords = new Coord[] {new Coord(1, 1), new Coord(1, 2), new Coord(1, 3)};
    ship = new Ship(coords, shipType);
  }

  /**
   * Tests for getShipState
   */
  @Test
  public void testGetShipStateAlive() {
    assertEquals(ShipState.ALIVE, ship.getShipState());
  }

  /**
   * More tests for getShipState
   */
  @Test
  public void testGetShipStateDestroyed() {
    ship.decrementShipHealth();
    ship.decrementShipHealth();
    ship.decrementShipHealth();
    assertEquals(ShipState.DESTROYED, ship.getShipState());
  }

  /**
   * Tests for isLocatedHere
   */
  @Test
  public void testIsLocatedHereTrue() {
    Coord location = new Coord(1, 2);
    assertTrue(ship.isLocatedHere(location));
  }

  /**
   * More tests for isLocatedHere
   */
  @Test
  public void testIsLocatedHereFalse() {
    Coord location = new Coord(2, 2);
    assertFalse(ship.isLocatedHere(location));
  }
}
