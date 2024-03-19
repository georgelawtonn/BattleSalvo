package cs3500.pa03.salvomodel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.salvoenums.ShipType;
import cs3500.pa03.salvoenums.VisualType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for ActiveGameData
 */
class ActiveGameDataTest {

  private BattleSalvoBoard bsb;
  private BattleSalvoBoard bsbTwo;
  private ArrayList<Coord> coords;
  private ActiveGameData activeGameData;

  /**
   * Setup for testing
   */
  @BeforeEach
  public void setUp() {
    activeGameData = new ActiveGameData();
    bsb = new BattleSalvoBoard(6, 6, new Random(3));
    bsbTwo = new BattleSalvoBoard(6, 6, new Random(4));
    HashMap<ShipType, Integer> specifications = new HashMap<>();
    specifications.put(ShipType.CARRIER, 1);
    specifications.put(ShipType.BATTLESHIP, 1);
    specifications.put(ShipType.DESTROYER, 1);
    specifications.put(ShipType.SUBMARINE, 1);
    bsb.initializeBoard(specifications);
    bsbTwo.initializeBoard(specifications);
    coords = new ArrayList<>(Arrays.asList(
        new Coord(0, 2),
        new Coord(1, 2),
        new Coord(2, 2),
        new Coord(3, 2),
        new Coord(4, 2),
        new Coord(5, 2)
    ));
  }

  /**
   * Tests for setManualShots and getManualShots
   */
  @Test
  void getManualShots() {
    activeGameData.setManualShots(coords);
    assertEquals(activeGameData.getManualShots(), coords);
  }

  /**
   * Tests for setAiBoard and getAiBoard
   */
  @Test
  void getAiBoard() {
    activeGameData.setAiBoard(bsb);
    assertEquals("B B B B B 0 " + System.lineSeparator()
        + "0 0 0 0 0 0 " + System.lineSeparator()
        + "C C C C C C " + System.lineSeparator()
        + "S S S 0 0 0 " + System.lineSeparator()
        + "0 D D D D 0 " + System.lineSeparator()
        + "0 0 0 0 0 0", activeGameData.getAiBoard(VisualType.INITIAL).toString().trim());
  }

  /**
   * Tests for setManualBoard and getManualBoard
   */
  @Test
  void getManualBoard() {
    activeGameData.setManualBoard(bsbTwo);
    assertEquals("0 0 S S S 0 " + System.lineSeparator()
        + "0 0 0 0 0 0 " + System.lineSeparator()
        + "0 0 D D D D " + System.lineSeparator()
        + "0 B B B B B " + System.lineSeparator()
        + "C C C C C C " + System.lineSeparator()
        + "0 0 0 0 0 0", activeGameData.getManualBoard(VisualType.INITIAL).toString().trim());
  }
}