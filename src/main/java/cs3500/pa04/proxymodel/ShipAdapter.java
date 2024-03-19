package cs3500.pa04.proxymodel;

import cs3500.pa03.salvoenums.Direction;
import cs3500.pa03.salvomodel.Coord;
import cs3500.pa03.salvomodel.Ship;

/**
 * Represents a class that adapts the server ships to the ships we originally had
 */
public class ShipAdapter {
  private final Coord coord;
  private final int length;
  private final Direction direction;

  /**
   * initializes a ship adapter
   *
   * @param myShip that was originally made
   */
  public ShipAdapter(Ship myShip) {
    this.coord = getCoordC(myShip);
    this.length = getLengthC(myShip);
    this.direction = getDirectionC(myShip);
  }

  /**
   * gets the starting coord of the ship for the constructor
   *
   * @param myShip the original ship
   * @return the first coord of the ship
   */
  private Coord getCoordC(Ship myShip) {
    Coord[] coords = myShip.getCoords();
    return coords[0];
  }

  /**
   * gets the length of the original ship
   *
   * @param myShip the original ship
   * @return the length of the ship
   */
  private int getLengthC(Ship myShip) {
    return myShip.getShipType().getSize();
  }

  /**
   * gets the direction that the ship is in
   *
   * @param myShip the original ship
   * @return the direction of the ship
   */
  private Direction getDirectionC(Ship myShip) {
    Coord[] coords = myShip.getCoords();
    Coord one = coords[0];
    Coord two = coords[1];
    if (one.getX() == two.getX()) {
      return Direction.VERTICAL;
    } else {
      return Direction.HORIZONTAL;
    }
  }

  /**
   * gets the first coord of the ship adapter
   *
   * @return the first coord
   */
  public Coord getCoord() {
    return coord;
  }

  /**
   * gets the length of the ship adapter
   *
   * @return the length
   */
  public int getLength() {
    return length;
  }

  /**
   * gets the direction of the ship adapter
   *
   * @return the direction
   */
  public Direction getDirection() {
    return direction;
  }
}
