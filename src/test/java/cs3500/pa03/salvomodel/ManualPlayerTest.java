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
 * Tests for ManualPlayer
 */
class ManualPlayerTest {
  private Player manualPlayer;
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
    manualPlayer = new ManualPlayer(activeGameData, display, new Random(3));
    HashMap<ShipType, Integer> specifications = new HashMap<>();
    specifications.put(ShipType.CARRIER, 1);
    specifications.put(ShipType.BATTLESHIP, 1);
    specifications.put(ShipType.DESTROYER, 1);
    specifications.put(ShipType.SUBMARINE, 1);
    ships = manualPlayer.setup(6, 6, specifications);
  }

  /**
   * Tests for name
   */
  @Test
  void name() {
    assertEquals(manualPlayer.name(), "Player");
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
    assertEquals(manualPlayer.takeShots(), coords);
  }

  /**
   * Tests for reportDamage
   */
  @Test
  void reportDamage() {
    assertEquals(manualPlayer.reportDamage(coords), coords);
  }

  /**
   * Tests for successfulHits
   */
  @Test
  void successfulHits() {
    manualPlayer.successfulHits(coords);
    assertEquals(output.toString().trim(),
        "---------------------------------------------------------"
        + System.lineSeparator()
        + "Successful Shots:  (0, 2)  (1, 2)  (2, 2)  (3, 2)  (4, 2)  (5, 2)");
  }

  /**
   * Tests for endGame
   */
  @Test
  void endGame() {
    manualPlayer.endGame(GameResult.WIN, "Manual Loss : Lost All Ships");
    assertEquals(output.toString().trim(),
        "---------------------------------------------------------"
            + System.lineSeparator()
        + "Manual Loss : Lost All Ships");
  }
}