package cs3500.pa03.salvomodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa03.salvoenums.ShipType;
import cs3500.pa03.salvoenums.VisualType;
import cs3500.pa03.salvoview.BattleSalvoDisplay;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for ActiveGameModel
 */
class ActiveGameModelTest {
  ActiveGameModel activeGameModel;
  HashMap<ShipType, Integer> specifications;
  ArrayList<Coord> remainingCoords;
  ActiveGameData activeGameData;
  StringBuilder output;
  ArrayList<Coord> coords;

  /**
   * Setup for testing
   */
  @BeforeEach
  public void setUp() {
    output = new StringBuilder();
    BattleSalvoDisplay display = new BattleSalvoDisplay(output);
    activeGameData = new ActiveGameData();
    Player aiPlayer = new AiPlayer(activeGameData, display, new Random(3));
    Player manualPlayer = new ManualPlayer(activeGameData, display, new Random(3));
    activeGameModel = new ActiveGameModel(aiPlayer, manualPlayer, activeGameData);
    specifications = new HashMap<>();
    specifications.put(ShipType.CARRIER, 1);
    specifications.put(ShipType.BATTLESHIP, 1);
    specifications.put(ShipType.DESTROYER, 1);
    specifications.put(ShipType.SUBMARINE, 1);
    activeGameModel.setupPlayers(6, 6, specifications);
    coords = new ArrayList<>(Arrays.asList(
        new Coord(0, 2),
        new Coord(1, 2),
        new Coord(2, 2),
        new Coord(3, 2),
        new Coord(4, 2),
        new Coord(5, 2)
    ));
    remainingCoords = new ArrayList<>(Arrays.asList(
        new Coord(0, 0),
        new Coord(1, 0),
        new Coord(2, 0),
        new Coord(3, 0),
        new Coord(4, 0),
        new Coord(1, 4),
        new Coord(2, 4),
        new Coord(3, 4),
        new Coord(4, 4),
        new Coord(0, 3),
        new Coord(1, 3),
        new Coord(2, 3)
    ));
    activeGameData.setManualShots(coords);
  }

  /**
   * Tests for getManualShotCount
   */
  @Test
  void getManualShotCount() {
    assertEquals(activeGameModel.getManualShotCount(), 4);
  }

  /**
   * Tests for shootVolley and retrieveBoards
   */
  @Test
  void retrieveBoards() {
    activeGameModel.shootVolley();
    assertEquals(activeGameModel.retrieveBoards(VisualType.OPPONENT,
            VisualType.USER).toString().trim(),
        "Opponent Board:" + System.lineSeparator()
            + "0 0 0 0 0 0 " + System.lineSeparator()
            + "0 0 0 0 0 0 " + System.lineSeparator()
            + "H H H H H H " + System.lineSeparator()
            + "0 0 0 0 0 0 " + System.lineSeparator()
            + "0 0 0 0 0 0 " + System.lineSeparator()
            + "0 0 0 0 0 0 " + System.lineSeparator()
            + System.lineSeparator()
            + "Your Board:" + System.lineSeparator()
            + "S S S S S 0 " + System.lineSeparator()
            + "0 0 0 0 M 0 " + System.lineSeparator()
            + "S S S S S S " + System.lineSeparator()
            + "H S S 0 0 0 " + System.lineSeparator()
            + "0 S S H S M " + System.lineSeparator()
            + "0 0 0 0 0 0");
  }

  /**
   * Tests for gameContinues
   */
  @Test
  void gameContinues() {
    assertTrue(activeGameModel.gameContinues());
    activeGameModel.shootVolley();
    activeGameData.setManualShots(remainingCoords);
    activeGameModel.shootVolley();
    assertFalse(activeGameModel.gameContinues());
  }

  /**
   * More Tests for gameContinues
   */
  @Test
  void gameContinuesTwo() {
    activeGameData.setManualShots(remainingCoords);
    activeGameModel.shootVolley();
    activeGameData.setManualShots(new ArrayList<>(List.of(new Coord(0, 0))));
    for (int i = 0; i < 31; i++) {
      activeGameModel.shootVolley();
    }
    assertFalse(activeGameModel.gameContinues());
  }

  /**
   * Tests for determineWinner
   */
  @Test
  void determineWinnerMan() {
    activeGameModel.determineWinner();
    assertEquals(output.toString().trim(),
        "---------------------------------------------------------"
        + System.lineSeparator()
        + "Manual Victory: Destroyed All Ships"
        + System.lineSeparator()
        + "---------------------------------------------------------"
        + System.lineSeparator()
        + "AI Loss : Lost All Ships");
  }

  /**
   * More tests for determineWinner
   */
  @Test
  void determineWinnerDraw() {
    // Sets Up Draw
    activeGameData.setManualShots(remainingCoords);
    activeGameModel.shootVolley();
    activeGameData.setManualShots(new ArrayList<>(List.of(new Coord(0, 0))));
    for (int i = 0; i < 31; i++) {
      activeGameModel.shootVolley();
    }
    activeGameData.setManualShots(coords);
    activeGameModel.shootVolley();
    activeGameModel.determineWinner();
    assertTrue(output.toString().trim().endsWith("Manual Draw : Lost All Ships"
        + System.lineSeparator()
        + "---------------------------------------------------------"
        + System.lineSeparator()
        + "AI Draw : Lost All Ships"));
  }

  /**
   * More tests for determineWinner
   */
  @Test
  void determineWinnerAi() {
    activeGameData.setManualShots(remainingCoords);
    activeGameModel.shootVolley();
    activeGameData.setManualShots(new ArrayList<>(List.of(new Coord(0, 0))));
    for (int i = 0; i < 32; i++) {
      activeGameModel.shootVolley();
    }
    activeGameModel.determineWinner();
    assertTrue(output.toString().trim().endsWith("Manual Loss : Lost All Ships"
            + System.lineSeparator()
        + "---------------------------------------------------------"
        + System.lineSeparator()
        + "AI Victory : Destroyed All Ships"));
  }

  /**
   * Tests for validateCoords
   */
  @Test
  void validateCoords() {
    assertTrue(activeGameModel.validateCoords(coords, 6, 6));
    assertFalse(activeGameModel.validateCoords(coords, 6, 6));
    remainingCoords.add(new Coord(0, 0));
    assertFalse(activeGameModel.validateCoords(remainingCoords, 6, 6));
  }

  /**
   * More tests for validateCoords
   */
  @Test
  void validateCoordsTwo() {
    assertFalse(activeGameModel.validateCoords(coords, 5, 5));
    assertFalse(activeGameModel.validateCoords(coords, 5, 5));
    remainingCoords.add(new Coord(0, 0));
    assertFalse(activeGameModel.validateCoords(remainingCoords, 5, 5));
  }
}