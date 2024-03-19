package cs3500.pa03.salvocontroller;

import cs3500.pa03.salvoenums.ShipType;
import cs3500.pa03.salvoenums.VisualType;
import cs3500.pa03.salvomodel.ActiveGameData;
import cs3500.pa03.salvomodel.ActiveGameModel;
import cs3500.pa03.salvomodel.AiPlayer;
import cs3500.pa03.salvomodel.Coord;
import cs3500.pa03.salvomodel.ManualPlayer;
import cs3500.pa03.salvomodel.Player;
import cs3500.pa03.salvoview.BattleSalvoDisplay;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Manages and controls gamestate, gamephase and the relevant inputs necessary to play
 * BattleSalvo. Is initially called upon from main through the run method
 */
public class BattleSalvoController {
  private final BattleSalvoDisplay display;
  private final ConsoleReader consoleReader;
  private final ActiveGameData activeGameData = new ActiveGameData();
  private Player aiPlayer;
  private Player manualPlayer;
  private ActiveGameModel activeGameModel;
  private int height;
  private int width;

  /**
   * Base Constructor for BattleSalvoController
   *
   * @param output Output location
   * @param input Input location
   */
  public BattleSalvoController(Appendable output, Readable input) {
    display = new BattleSalvoDisplay(output);
    consoleReader = new ConsoleReader(input);
    aiPlayer = new AiPlayer(activeGameData, display);
    manualPlayer = new ManualPlayer(activeGameData, display);
    activeGameModel = new ActiveGameModel(aiPlayer, manualPlayer, activeGameData);
  }

  /**
   * The set seed Constructor for BattleSalvoController
   *
   * @param output Output location
   * @param input Input location
   * @param random Random Object
   */
  public BattleSalvoController(Appendable output, Readable input, Random random) {
    this(output, input);
    aiPlayer = new AiPlayer(activeGameData, display, random);
    manualPlayer = new ManualPlayer(activeGameData, display, random);
    activeGameModel = new ActiveGameModel(aiPlayer, manualPlayer, activeGameData);
  }

  /**
   * Calls upon necessary methods to run local BattleSalvo game
   */
  public void run() {
    display.initialDialogue();

    handleSetup();

    HashMap<ShipType, Integer> spec = shipSelectionPhase();
    setupGamePhase(spec);

    while (activeGameModel.gameContinues()) {
      handleTakeShots();
    }

    activeGameModel.determineWinner();
  }

  /**
   * Calls upon dimension prompt, then reads and checks for valid user input for width and height
   */
  public void handleSetup() {
    display.initialHeightPrompt();
    ArrayList<Integer> dimensions = consoleReader.read();
    try {
      height = dimensions.get(0);
      width = dimensions.get(1);
      if (height > 15 || height <= 5 || width > 15 || width <= 5) {
        throw new IllegalArgumentException("Invalid Input");
      }
    } catch (IndexOutOfBoundsException | IllegalArgumentException invalidInput) {
      StringBuilder error = new StringBuilder("Invalid Dimensions Please Try Again");
      display.displayError(error);
      handleSetup();
    }
  }

  /**
   * Calls upon shipSelectionPrompt, then reads and checks for valid user input for ships
   *
   * @return A HashMap containing the ShipType to amount of that ShipType
   */
  private HashMap<ShipType, Integer> shipSelectionPhase() {
    int leastDim = Math.min(height, width);
    display.shipSelectionPrompt(leastDim);
    ArrayList<Integer> shipCount = consoleReader.read();
    try {
      int carrier = shipCount.get(0);
      int battleship = shipCount.get(1);
      int destroyer = shipCount.get(2);
      int submarine = shipCount.get(3);
      int sumAll = carrier + battleship + destroyer + submarine;
      if (sumAll > leastDim || carrier < 1 || battleship < 1 || destroyer < 1 || submarine < 1) {
        throw new IllegalArgumentException("Invalid Ship Count");
      }
      return new HashMap<>() {{
          put(ShipType.CARRIER, carrier);
          put(ShipType.BATTLESHIP, battleship);
          put(ShipType.DESTROYER, destroyer);
          put(ShipType.SUBMARINE, submarine);
        }};
    } catch (IndexOutOfBoundsException | IllegalArgumentException invalidInput) {
      StringBuilder error = new StringBuilder("Invalid Ship Count Please Try Again");
      display.displayError(error);
      return shipSelectionPhase();
    }
  }

  /**
   * Sets up the player models needed for a game of BattleSalvo, and displays initial states
   *
   * @param specifications A HashMap containing the ShipType to amount of that ShipType
   */
  private void setupGamePhase(HashMap<ShipType, Integer> specifications) {
    activeGameModel.setupPlayers(height, width, specifications);
    StringBuilder boards = activeGameModel.retrieveBoards(VisualType.OPPONENT, VisualType.INITIAL);
    display.displayBoardData(boards);
  }

  /**
   * Calls upon displayShotPrompt, then reads and checks for valid shot inputs
   */
  public void handleTakeShots() {
    int manualShotCount = activeGameModel.getManualShotCount();
    display.displayShotPrompt(manualShotCount);
    try {
      ArrayList<Coord> manualShots = consoleReader.readShots(manualShotCount);
      if (activeGameModel.validateCoords(manualShots, height, width)) {
        activeGameData.setManualShots(manualShots);
        handleDamage();
      } else {
        throw new IllegalArgumentException("Shots Invalid");
      }
    } catch (NoSuchElementException | IndexOutOfBoundsException
             | IllegalArgumentException invalidInput) {
      StringBuilder error = new StringBuilder("Invalid Shots Please Try Again");
      display.displayError(error);
      handleTakeShots();
    }
  }

  /**
   * Handles the active damage phase of the game and retrieves/displays the board states
   */
  public void handleDamage() {
    activeGameModel.shootVolley();
    StringBuilder boards = activeGameModel.retrieveBoards(VisualType.OPPONENT, VisualType.USER);
    display.displayBoardData(boards);
  }


}
