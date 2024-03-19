package cs3500.pa03.salvoview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

/**
 * Tests for BattleSalvoDisplay
 */
public class BattleSalvoDisplayTest {
  private StringBuilder output;
  private BattleSalvoDisplay display;

  /**
   * Setup for testing
   */
  @BeforeEach
  public void setUp() {
    output = new StringBuilder();
    display = new BattleSalvoDisplay(output);
  }

  /**
   * Tests for initialDialogue
   */
  @Test
  public void testInitialDialogue() {
    display.initialDialogue();
    String methodOutput = "Hello! Welcome to BattleSalvo the game!" + System.lineSeparator()
        + "---------------------------------------------------------" + System.lineSeparator();
    assertEquals(methodOutput, output.toString());
  }

  /**
   * Tests for initialHeightPrompt
   */
  @Test
  public void testInitialHeightPrompt() {
    display.initialHeightPrompt();
    String methodOutput = "To start we'll have you input the dimensions of the board"
        + System.lineSeparator()
        + "Make sure to verify that both dimensions fit within 6 and 15 (inclusive)"
        + System.lineSeparator()
        + "Please input the height, the width, and press Enter (Format: 6 6) "
        + System.lineSeparator()
        + "---------------------------------------------------------"
        + System.lineSeparator();
    assertEquals(methodOutput, output.toString());
  }

  /**
   * Tests for shipSelectionPrompt
   */
  @Test
  public void testShipSelectionPrompt() {
    int fleetSize = 4;
    display.shipSelectionPrompt(fleetSize);
    String methodOutput = "---------------------------------------------------------"
        + System.lineSeparator()
        + "Now onto ship selection!" + System.lineSeparator()
        + "Enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine]"
        + System.lineSeparator()
        + "Tip: Your fleet must not exceed size 4 and you must have one of each ship type"
        + System.lineSeparator()
        + "---------------------------------------------------------" + System.lineSeparator();
    assertEquals(methodOutput, output.toString());
  }

  /**
   * tests for displayBoardData
   */
  @Test
  public void testDisplayBoardData() {
    StringBuilder boards = new StringBuilder();
    boards.append("Board data goes here");
    display.displayBoardData(boards);
    String methodOutput = "---------------------------------------------------------"
        + System.lineSeparator()
        + "Board data goes here" + System.lineSeparator();
    assertEquals(methodOutput, output.toString());
  }

  /**
   * Tests for displayShotPrompt
   */
  @Test
  public void testDisplayShotPrompt() {
    int shotCount = 3;
    display.displayShotPrompt(shotCount);
    String methodOutput = "---------------------------------------------------------"
        + System.lineSeparator()
        + "Please enter 3 Shots in the format: \"x y\" and enter" + System.lineSeparator()
        + "---------------------------------------------------------" + System.lineSeparator();
    assertEquals(methodOutput, output.toString());
  }

  /**
   * Tests for displayError
   */
  @Test
  public void testDisplayError() {
    StringBuilder errorMessage = new StringBuilder();
    errorMessage.append("Error message goes here");
    display.displayError(errorMessage);
    String methodOutput = "Error message goes here" + System.lineSeparator();
    assertEquals(methodOutput, output.toString());
  }

  /**
   * Tests for displayMessage
   */
  @Test
  public void testDisplayMessage() {
    StringBuilder message = new StringBuilder();
    message.append("Message goes here");
    display.displayMessage(message);
    String methodOutput = "---------------------------------------------------------"
        + System.lineSeparator()
        + "Message goes here" + System.lineSeparator();
    assertEquals(methodOutput, output.toString());
  }

  /**
   * Tests for the thrown error messages
   */
  @Test
  public void testThrows() {
    MockAppendable badOutput = new MockAppendable();
    BattleSalvoDisplay badDisplay = new BattleSalvoDisplay(badOutput);
    assertThrows(IllegalStateException.class, () -> {
      badDisplay.initialDialogue();
    });
    assertThrows(IllegalStateException.class, () -> {
      badDisplay.initialHeightPrompt();
    });
    assertThrows(IllegalStateException.class, () -> {
      badDisplay.shipSelectionPrompt(4);
    });
    assertThrows(IllegalStateException.class, () -> {
      badDisplay.displayBoardData(new StringBuilder("Cow"));
    });
    assertThrows(IllegalStateException.class, () -> {
      badDisplay.displayShotPrompt(4);
    });
    assertThrows(IllegalStateException.class, () -> {
      badDisplay.displayMessage(new StringBuilder("Duck"));
    });
    assertThrows(IllegalStateException.class, () -> {
      badDisplay.displayError(new StringBuilder("Pig"));
    });
  }
}

