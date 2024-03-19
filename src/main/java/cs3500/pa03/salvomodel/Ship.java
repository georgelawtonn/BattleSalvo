package cs3500.pa03.salvomodel;

import com.fasterxml.jackson.annotation.JsonCreator;
import cs3500.pa03.salvoenums.ShipState;
import cs3500.pa03.salvoenums.ShipType;

/**
 * The Ship Class maintains location and health of a Ship
 */
public class Ship {
  private final Coord[] coords;
  private final ShipType shipType;
  private int shipHealth;

  /**
   * initalizes a ship with the given location and ship type
   *
   * @param coords given location of the ship
   * @param shipType the type of ship
   */
  public Ship(Coord[] coords, ShipType shipType) {
    this.coords = coords;
    this.shipType = shipType;
    this.shipHealth = shipType.getSize();
  }

  /**
   * Gets a ShipState representation of this Ships current health
   *
   * @return ShipState (Alive or Destroyed)
   */
  public ShipState getShipState() {
    if (shipHealth > 0) {
      return ShipState.ALIVE;
    } else {
      return ShipState.DESTROYED;
    }
  }

  /**
   * Determines whether this Ship is occupying the given location
   *
   * @param location The given Coord
   * @return A boolean representing whether the Ship is located in the given location
   */
  public boolean isLocatedHere(Coord location) {
    for (Coord coord : coords) {
      if (coord.isSameAs(location)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Decrements the shipHealth field
   */
  public void decrementShipHealth() {
    shipHealth--;
  }

  /**
   * Gets the coord of this Ship object
   *
   * @return Coord array
   */
  public Coord[] getCoords() {
    return coords;
  }

  /**
   * Gets the ShipType of this Ship object
   *
   * @return ShipType enum
   */
  public ShipType getShipType() {
    return shipType;
  }
}
