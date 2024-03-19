package cs3500.pa03.salvomodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa03.salvoenums.GameResult;
import cs3500.pa03.salvoenums.ShipType;
import cs3500.pa03.salvoview.BattleSalvoDisplay;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for AiPlayer
 */
class AiPlayerTest {
  private Player aiPlayer;
  private StringBuilder output;
  private ArrayList<Coord> coords;
  private List<Ship> ships;

  /**
   * Setup for testing
   */
  @BeforeEach
  public void setUp() {
    output = new StringBuilder();
    BattleSalvoDisplay display = new BattleSalvoDisplay(output);
    ActiveGameData activeGameData = new ActiveGameData();
    coords = new ArrayList<>(Arrays.asList(
        new Coord(0, 2),
        new Coord(1, 2),
        new Coord(2, 2),
        new Coord(3, 2),
        new Coord(4, 2),
        new Coord(5, 2)
    ));
    activeGameData.setManualShots(coords);
    aiPlayer = new AiPlayer(activeGameData, display, new Random(3));
    HashMap<ShipType, Integer> specifications = new HashMap<>();
    specifications.put(ShipType.CARRIER, 1);
    specifications.put(ShipType.BATTLESHIP, 1);
    specifications.put(ShipType.DESTROYER, 1);
    specifications.put(ShipType.SUBMARINE, 1);
    ships = aiPlayer.setup(6, 6, specifications);
  }


  /**
   * Tests for name
   */
  @Test
  void name() {
    assertEquals(aiPlayer.name(), "georgelawtonn");
  }

  /**
   * Tests for setup
   */
  @Test
  void setup() {
    assertTrue(ships.get(0).isLocatedHere(new Coord(0, 2)));
    assertTrue(ships.get(0).isLocatedHere(new Coord(1, 2)));
    assertTrue(ships.get(0).isLocatedHere(new Coord(2, 2)));
    assertTrue(ships.get(0).isLocatedHere(new Coord(3, 2)));
    assertTrue(ships.get(0).isLocatedHere(new Coord(4, 2)));
    assertTrue(ships.get(0).isLocatedHere(new Coord(5, 2)));
  }

  /**
   * Tests for takeShots
   */
  @Test
  void takeShots() {
    List<Coord> shots = aiPlayer.takeShots();
    assertEquals(shots.get(0).getX(), 5);
    assertEquals(shots.get(0).getY(), 4);
    assertEquals(shots.get(1).getX(), 3);
    assertEquals(shots.get(1).getY(), 4);
    assertEquals(shots.get(2).getX(), 0);
    assertEquals(shots.get(2).getY(), 3);
    assertEquals(shots.get(3).getX(), 4);
    assertEquals(shots.get(3).getY(), 1);
  }

  /**
   * Tests for reportDamage
   */
  @Test
  void reportDamage() {
    assertEquals(aiPlayer.reportDamage(coords), coords);
  }

  /**
   * Tests for successfulHits
   */
  @Test
  void successfulHits() {
    aiPlayer.successfulHits(coords);
    assertEquals(output.toString().trim(),
        "---------------------------------------------------------"
        + System.lineSeparator()
        + "Enemy Successful Shots:  (0, 2)  (1, 2)  (2, 2)  (3, 2)  (4, 2)  (5, 2)");
  }

  /**
   * Tests for endGame
   */
  @Test
  void endGame() {
    aiPlayer.endGame(GameResult.WIN, "AI Loss : Lost All Ships");
    assertEquals(output.toString().trim(),
        "---------------------------------------------------------"
            + System.lineSeparator()
            + "AI Loss : Lost All Ships");
  }
}