package cs3500.pa03.salvoenums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for BoardCellState enum
 */
class BoardCellStateTest {
  BoardCellState hit;
  BoardCellState miss;
  BoardCellState empty;
  BoardCellState carrier;
  BoardCellState battleship;
  BoardCellState destroyer;
  BoardCellState submarine;
  BoardCellState ship;

  /**
   * Setup for testing
   */
  @BeforeEach
  public void setUp() {
    hit = BoardCellState.HIT;
    miss = BoardCellState.MISS;
    empty = BoardCellState.EMPTY;
    carrier = BoardCellState.CARRIER;
    battleship = BoardCellState.BATTLESHIP;
    destroyer = BoardCellState.DESTROYER;
    submarine = BoardCellState.SUBMARINE;
    ship = BoardCellState.SHIP;
  }

  /**
   * Tests for getBoardStringRepresentation
   */
  @Test
  void getBoardStringRepresentation() {
    assertEquals("H", hit.getBoardStringRepresentation());
    assertEquals("M", miss.getBoardStringRepresentation());
    assertEquals("0", empty.getBoardStringRepresentation());
    assertEquals("C", carrier.getBoardStringRepresentation());
    assertEquals("B", battleship.getBoardStringRepresentation());
    assertEquals("D", destroyer.getBoardStringRepresentation());
    assertEquals("S", submarine.getBoardStringRepresentation());
    assertEquals("S", ship.getBoardStringRepresentation());
  }

  /**
   * Tests for ordinal values
   */
  @Test
  void values() {
    assertEquals(0, hit.ordinal());
    assertEquals(1, miss.ordinal());
    assertEquals(2, empty.ordinal());
    assertEquals(3, carrier.ordinal());
    assertEquals(4, battleship.ordinal());
    assertEquals(5, destroyer.ordinal());
    assertEquals(6, submarine.ordinal());
    assertEquals(7, ship.ordinal());
  }

  /**
   * Tests for name
   */
  @Test
  public void testBoardCellStateNames() {
    assertEquals("HIT", hit.name());
    assertEquals("MISS", miss.name());
    assertEquals("EMPTY", empty.name());
    assertEquals("CARRIER", carrier.name());
    assertEquals("BATTLESHIP", battleship.name());
    assertEquals("DESTROYER", destroyer.name());
    assertEquals("SUBMARINE", submarine.name());
    assertEquals("SHIP", ship.name());
  }
}