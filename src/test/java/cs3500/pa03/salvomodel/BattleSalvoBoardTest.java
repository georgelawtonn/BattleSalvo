package cs3500.pa03.salvomodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa03.salvoenums.ShipType;
import cs3500.pa03.salvoenums.VisualType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for BattleSalvoBoard
 */
class BattleSalvoBoardTest {
  private BattleSalvoBoard bsb;
  private BattleSalvoBoard bsbTwo;
  private ArrayList<Ship> deadShip;
  private ArrayList<Ship> ships;
  private ArrayList<Coord> carrierCoord;
  private ArrayList<Ship> returnedShips;
  private ArrayList<Ship> returnedShipsTwo;
  private ArrayList<Coord> failedShot;

  /**
   * Setup for testing
   */
  @BeforeEach
  public void setUp() {
    bsb = new BattleSalvoBoard(6, 6, new Random(3));
    bsbTwo = new BattleSalvoBoard(6, 6, new Random(4));
    HashMap<ShipType, Integer> specifications = new HashMap<>();
    specifications.put(ShipType.CARRIER, 1);
    specifications.put(ShipType.BATTLESHIP, 1);
    specifications.put(ShipType.DESTROYER, 1);
    specifications.put(ShipType.SUBMARINE, 1);
    Coord[] placeHolder = new Coord[] {new Coord(0, 0)};
    Ship deadSubmarine = new Ship(placeHolder, ShipType.SUBMARINE);
    deadSubmarine.decrementShipHealth();
    deadSubmarine.decrementShipHealth();
    deadSubmarine.decrementShipHealth();
    deadShip = new ArrayList<>(List.of(deadSubmarine));
    Ship carrier = new Ship(placeHolder, ShipType.CARRIER);
    Ship battleship = new Ship(placeHolder, ShipType.BATTLESHIP);
    Ship destroyer = new Ship(placeHolder, ShipType.DESTROYER);
    Ship submarine = new Ship(placeHolder, ShipType.SUBMARINE);
    ships = new ArrayList<>(Arrays.asList(carrier, battleship, destroyer, submarine));
    carrierCoord = new ArrayList<>(Arrays.asList(
        new Coord(0, 2),
        new Coord(1, 2),
        new Coord(2, 2),
        new Coord(3, 2),
        new Coord(4, 2),
        new Coord(5, 2)
    ));
    returnedShips = bsb.initializeBoard(specifications);
    returnedShipsTwo = bsbTwo.initializeBoard(specifications);
    failedShot = new ArrayList<>(List.of(new Coord(5, 5)));
  }

  /**
   * Tests for initializeBoard
   */
  @Test
  void initializeBoard() {
    assertEquals(returnedShips.get(0).getShipState(), ships.get(0).getShipState());
    assertEquals(returnedShips.get(1).getShipState(), ships.get(1).getShipState());
    assertEquals(returnedShips.get(2).getShipState(), ships.get(2).getShipState());
    assertEquals(returnedShips.get(3).getShipState(), ships.get(3).getShipState());
    assertEquals(returnedShipsTwo.get(0).getShipState(), ships.get(0).getShipState());
    assertEquals(returnedShipsTwo.get(1).getShipState(), ships.get(1).getShipState());
    assertEquals(returnedShipsTwo.get(2).getShipState(), ships.get(2).getShipState());
    assertEquals(returnedShipsTwo.get(3).getShipState(), ships.get(3).getShipState());
  }

  /**
   * Tests for getAliveShipCount
   */
  @Test
  void getAliveShipCount() {
    assertEquals(BattleSalvoBoard.getAliveShipCount(ships), 4);
    assertEquals(BattleSalvoBoard.getAliveShipCount(deadShip), 0);
  }

  /**
   * Tests for checkShotLocations
   */
  @Test
  void checkShotLocations() {
    ArrayList<Coord> carrierShots = bsb.checkShotLocations(carrierCoord);
    assertTrue(carrierShots.get(0).isSameAs(new Coord(0, 2)));
    assertTrue(carrierShots.get(1).isSameAs(new Coord(1, 2)));
    assertTrue(carrierShots.get(2).isSameAs(new Coord(2, 2)));
    assertTrue(carrierShots.get(3).isSameAs(new Coord(3, 2)));
    assertTrue(carrierShots.get(4).isSameAs(new Coord(4, 2)));
    assertTrue(carrierShots.get(5).isSameAs(new Coord(5, 2)));
    ArrayList<Coord> noShots = bsb.checkShotLocations(failedShot);
    assertTrue(noShots.isEmpty());
  }

  /**
   * Tests for alreadyShotCoord
   */
  @Test
  void alreadyShotCoord() {
    assertTrue(BattleSalvoBoard.alreadyShotCoord(new Coord(0, 2),
        carrierCoord));
    assertFalse(BattleSalvoBoard.alreadyShotCoord(new Coord(5, 5),
        carrierCoord));
  }

  /**
   * Tests for generateBoardVisual
   */
  @Test
  void generateBoardVisual() {
    assertEquals("B B B B B 0 " + System.lineSeparator()
        + "0 0 0 0 0 0 " + System.lineSeparator()
        + "C C C C C C " + System.lineSeparator()
        + "S S S 0 0 0 " + System.lineSeparator()
        + "0 D D D D 0 " + System.lineSeparator()
        + "0 0 0 0 0 0", bsb.generateBoardVisual(VisualType.INITIAL).toString().trim());
    assertEquals("0 0 0 0 0 0 " + System.lineSeparator()
        + "0 0 0 0 0 0 " + System.lineSeparator()
        + "0 0 0 0 0 0 " + System.lineSeparator()
        + "0 0 0 0 0 0 " + System.lineSeparator()
        + "0 0 0 0 0 0 " + System.lineSeparator()
        + "0 0 0 0 0 0", bsb.generateBoardVisual(VisualType.OPPONENT).toString().trim());
    assertEquals("S S S S S 0 " + System.lineSeparator()
        + "0 0 0 0 0 0 " + System.lineSeparator()
        + "S S S S S S " + System.lineSeparator()
        + "S S S 0 0 0 " + System.lineSeparator()
        + "0 S S S S 0 " + System.lineSeparator()
        + "0 0 0 0 0 0", bsb.generateBoardVisual(VisualType.USER).toString().trim());
    assertEquals("0 0 S S S 0 " + System.lineSeparator()
        + "0 0 0 0 0 0 " + System.lineSeparator()
        + "0 0 D D D D " + System.lineSeparator()
        + "0 B B B B B " + System.lineSeparator()
        + "C C C C C C " + System.lineSeparator()
        + "0 0 0 0 0 0", bsbTwo.generateBoardVisual(VisualType.INITIAL).toString().trim());
  }
}