package cs3500.pa03.salvocontroller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.StringReader;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for BattleSalvoController
 */
class BattleSalvoControllerTest {
  BattleSalvoController bsc;
  StringBuilder output;
  Random random;

  /**
   * Setup for testing
   */
  @BeforeEach
  public void setUp() {
    output = new StringBuilder();
    random = new Random(3);
  }

  /**
   * Tests for initializeGame
   */
  @Test
  void initializeGame() {
    String input = "6 6" + System.lineSeparator() + "1 1 1 1" + System.lineSeparator()
        + "0 0" + System.lineSeparator() + "1 1" + System.lineSeparator() + "2 2"
        + System.lineSeparator() + "3 3";
    Readable readable = new StringReader(input);
    bsc = new BattleSalvoController(output, readable, random);
    bsc.run();
    String expectedOut = "Hello! Welcome to BattleSalvo the game!" + System.lineSeparator()
        + "---------------------------------------------------------" + System.lineSeparator()
        + "To start we'll have you input the dimensions of the board" + System.lineSeparator()
        + "Make sure to verify that both dimensions fit within 6 and 15 (inclusive)"
        + System.lineSeparator()
        + "Please input the height, the width, and press Enter (Format: 6 6) "
        + System.lineSeparator()
        + "---------------------------------------------------------" + System.lineSeparator()
        + "---------------------------------------------------------" + System.lineSeparator()
        + "Now onto ship selection!" + System.lineSeparator()
        + "Enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine]"
        + System.lineSeparator()
        + "Tip: Your fleet must not exceed size 6 and you must have one of each ship type"
        + System.lineSeparator()
        + "---------------------------------------------------------" + System.lineSeparator()
        + "---------------------------------------------------------" + System.lineSeparator()
        + "Opponent Board:" + System.lineSeparator()
        + "0 0 0 0 0 0 " + System.lineSeparator()
        + "0 0 0 0 0 0 " + System.lineSeparator()
        + "0 0 0 0 0 0 " + System.lineSeparator()
        + "0 0 0 0 0 0 " + System.lineSeparator()
        + "0 0 0 0 0 0 " + System.lineSeparator()
        + "0 0 0 0 0 0 " + System.lineSeparator()
        + "" + System.lineSeparator()
        + "Your Board:" + System.lineSeparator()
        + "0 0 0 0 0 0 " + System.lineSeparator()
        + "B B B B B 0 " + System.lineSeparator()
        + "D D D D 0 0 " + System.lineSeparator()
        + "0 0 S S S 0 " + System.lineSeparator()
        + "C C C C C C " + System.lineSeparator()
        + "0 0 0 0 0 0 " + System.lineSeparator()
        + "" + System.lineSeparator()
        + "---------------------------------------------------------" + System.lineSeparator()
        + "Please enter 4 Shots in the format: \"x y\" and enter" + System.lineSeparator()
        + "---------------------------------------------------------" + System.lineSeparator()
        + "---------------------------------------------------------" + System.lineSeparator()
        + "Successful Shots:  (2, 2)  (0, 0) " + System.lineSeparator()
        + "---------------------------------------------------------" + System.lineSeparator()
        + "Enemy Successful Shots:  (3, 4)  (2, 1)  (1, 2) " + System.lineSeparator()
        + "---------------------------------------------------------" + System.lineSeparator()
        + "Opponent Board:" + System.lineSeparator()
        + "H 0 0 0 0 0 " + System.lineSeparator()
        + "0 M 0 0 0 0 " + System.lineSeparator()
        + "0 0 H 0 0 0 " + System.lineSeparator()
        + "0 0 0 M 0 0 " + System.lineSeparator()
        + "0 0 0 0 0 0 " + System.lineSeparator()
        + "0 0 0 0 0 0 " + System.lineSeparator()
        + "" + System.lineSeparator()
        + "Your Board:" + System.lineSeparator()
        + "M 0 0 0 0 0 " + System.lineSeparator()
        + "S S H S S 0 " + System.lineSeparator()
        + "S H S S 0 0 " + System.lineSeparator()
        + "0 0 S S S 0 " + System.lineSeparator()
        + "S S S H S S " + System.lineSeparator()
        + "0 0 0 0 0 0 ";
    assertTrue(output.toString().startsWith(expectedOut));
  }

  /**
   * Additional tests for initializeGame
   */
  @Test
  void initializeGameTwo() {
    String input = "6 5" + System.lineSeparator();
    Readable readable = new StringReader(input);
    bsc = new BattleSalvoController(output, readable, random);
    assertThrows(StackOverflowError.class, () -> {
      bsc.run();
    });
  }
}