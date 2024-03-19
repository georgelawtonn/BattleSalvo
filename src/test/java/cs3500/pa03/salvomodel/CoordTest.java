package cs3500.pa03.salvomodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for coord
 */
public class CoordTest {
  private int xcoord;
  private int ycoord;
  private Coord coord;

  /**
   * Setup for testing
   */
  @BeforeEach
  public void setUp() {
    xcoord = 2;
    ycoord = 3;
    coord = new Coord(xcoord, ycoord);
  }

  /**
   * Tests for getX
   */
  @Test
  public void testGetX() {
    assertEquals(xcoord, coord.getX());
  }

  /**
   * Tests for getY
   */
  @Test
  public void testGetY() {
    assertEquals(ycoord, coord.getY());
  }

  /**
   * Tests for isSameAs
   */
  @Test
  public void testIsSameAsTrue() {
    Coord otherCoord = new Coord(xcoord, ycoord);
    assertTrue(coord.isSameAs(otherCoord));
  }

  /**
   * More tests for is same as
   */
  @Test
  public void testIsSameAsFalse() {
    Coord otherCoord = new Coord(7, 5);
    assertFalse(coord.isSameAs(otherCoord));
  }
}
