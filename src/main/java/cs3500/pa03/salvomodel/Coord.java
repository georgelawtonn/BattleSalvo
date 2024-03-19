package cs3500.pa03.salvomodel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Coord class maintains an x and y representing position
 */
public class Coord {
  private final int xcoord;
  private final int ycoord;

  /**
   * The main constructor for Coord
   *
   * @param x The integer representing the x coordinate
   * @param y The integer representing the y coordinate
   */
  @JsonCreator
  public Coord(@JsonProperty("x") int x,
               @JsonProperty("y") int y) {
    this.xcoord = x;
    this.ycoord = y;
  }

  /**
   * Gets x field
   *
   * @return The integer x field
   */
  public int getX() {
    return xcoord;
  }

  /**
   * Gets y field
   *
   * @return The integer y field
   */
  public int getY() {
    return ycoord;
  }

  /**
   * Determines whether this coord is the same as the given coord
   *
   * @param coord The coord to be compared against
   * @return A boolean stating whether the coord is the same or not the same
   */
  public boolean isSameAs(Coord coord) {
    return this.xcoord == coord.getX() && this.ycoord == coord.getY();
  }
}
