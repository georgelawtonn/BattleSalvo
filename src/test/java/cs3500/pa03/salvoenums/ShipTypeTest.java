package cs3500.pa03.salvoenums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for ShipType enum
 */
class ShipTypeTest {
  ShipType carrier;
  ShipType battleship;
  ShipType destroyer;
  ShipType submarine;

  /**
   * Setup for testing
   */
  @BeforeEach
  public void setUp() {
    carrier = ShipType.CARRIER;
    battleship = ShipType.BATTLESHIP;
    destroyer = ShipType.DESTROYER;
    submarine = ShipType.SUBMARINE;
  }

  /**
   * Tests for getSize
   */
  @Test
  void getSize() {
    assertEquals(6, carrier.getSize());
    assertEquals(5, battleship.getSize());
    assertEquals(4, destroyer.getSize());
    assertEquals(3, submarine.getSize());
  }

  /**
   * Tests for getBoardCellState
   */
  @Test
  void getBoardCellState() {
    assertEquals(BoardCellState.CARRIER, carrier.getBoardCellState());
    assertEquals(BoardCellState.BATTLESHIP, battleship.getBoardCellState());
    assertEquals(BoardCellState.DESTROYER, destroyer.getBoardCellState());
    assertEquals(BoardCellState.SUBMARINE, submarine.getBoardCellState());
  }

  /**
   * Tests for ordinal values
   */
  @Test
  void values() {
    assertEquals(0, carrier.ordinal());
    assertEquals(1, battleship.ordinal());
    assertEquals(2, destroyer.ordinal());
    assertEquals(3, submarine.ordinal());
  }

  /**
   * Tests for name
   */
  @Test
  void valueOf() {
    assertEquals("CARRIER", carrier.name());
    assertEquals("BATTLESHIP", battleship.name());
    assertEquals("DESTROYER", destroyer.name());
    assertEquals("SUBMARINE", submarine.name());
  }
}