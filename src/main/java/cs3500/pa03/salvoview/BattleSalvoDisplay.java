package cs3500.pa03.salvoview;

import java.io.IOException;

/**
 * Maintains the Display for the BattleSalvo game
 */
public class BattleSalvoDisplay {
  private final Appendable output;

  /**
   * The constructor for BattleSalvoDisplay
   *
   * @param output The output location
   */
  public BattleSalvoDisplay(Appendable output) {
    this.output = output;
  }

  /**
   * Displays the initialDialogue for BattleSalvo
   */
  public void initialDialogue() {
    try {
      output.append("Hello! Welcome to BattleSalvo the game!");
      output.append(System.lineSeparator());
      output.append("---------------------------------------------------------");
      output.append(System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException("Invalid Output");
    }
  }

  /**
   * Displays the initial height and width prompting for BattleSalvo
   */
  public void initialHeightPrompt() {
    try {
      output.append("To start we'll have you input the dimensions of the board");
      output.append(System.lineSeparator());
      output.append("Make sure to verify that both dimensions fit within 6 and 15 (inclusive)");
      output.append(System.lineSeparator());
      output.append("Please input the height, the width, and press Enter (Format: 6 6) ");
      output.append(System.lineSeparator());
      output.append("---------------------------------------------------------");
      output.append(System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException("Invalid Output");
    }
  }

  /**
   * Displays the shipSelection prompt to the user
   *
   * @param fleetSize The max number of ships to be displayed
   */
  public void shipSelectionPrompt(int fleetSize) {
    try {
      output.append("---------------------------------------------------------");
      output.append(System.lineSeparator());
      output.append("Now onto ship selection!");
      output.append(System.lineSeparator());
      output.append("Enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine]");
      output.append(System.lineSeparator());
      output.append("Tip: Your fleet must not exceed size " + fleetSize
          + " and you must have one of each ship type");
      output.append(System.lineSeparator());
      output.append("---------------------------------------------------------");
      output.append(System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException("Invalid Output");
    }
  }

  /**
   * Displays the boards to the user
   *
   * @param boards The formatted version of both opponent and player boards
   */
  public void displayBoardData(StringBuilder boards) {
    try {
      output.append("---------------------------------------------------------");
      output.append(System.lineSeparator());
      output.append(boards);
      output.append(System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException("Invalid Output");
    }
  }

  /**
   * Displays the shot prompt to the user
   *
   * @param shotCount The number of shots that the user can input
   */
  public void displayShotPrompt(int shotCount) {
    try {
      output.append("---------------------------------------------------------");
      output.append(System.lineSeparator());
      output.append("Please enter " + shotCount + " Shots in the format: \"x y\" and enter");
      output.append(System.lineSeparator());
      output.append("---------------------------------------------------------");
      output.append(System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException("Invalid Output");
    }

  }

  /**
   * Displays errorMessages to the user
   *
   * @param errorMessage The message to be shown
   */
  public void displayError(StringBuilder errorMessage) {
    try {
      output.append(errorMessage);
      output.append(System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException("Invalid Output");
    }
  }

  /**
   * Displays messages to the user
   *
   * @param message The message to be shown
   */
  public void displayMessage(StringBuilder message) {
    try {
      output.append("---------------------------------------------------------");
      output.append(System.lineSeparator());
      output.append(message);
      output.append(System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException("Invalid Output");
    }
  }
}
