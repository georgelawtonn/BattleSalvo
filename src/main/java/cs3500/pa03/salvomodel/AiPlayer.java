package cs3500.pa03.salvomodel;

import cs3500.pa03.salvoenums.GameResult;
import cs3500.pa03.salvoenums.ShipType;
import cs3500.pa03.salvoview.BattleSalvoDisplay;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * The AiPlayer object which implements the Player interface
 */
public class AiPlayer implements Player {
  private int width;
  private int height;
  private BattleSalvoBoard board;
  private ArrayList<Ship> ships;
  private final ActiveGameData activeGameData;
  private BattleSalvoDisplay display = null;
  private final ArrayList<Coord> alreadyShot = new ArrayList<>();
  private Random random = new Random();
  private boolean isLocal = true;
  /**
   * The main constructor for the AiPlayer object
   *
   * @param activeGameData An ActiveGameData object for holding certain data
   * @param display A BattleSalvoDisplay object for certain display methods in this class
   */
  public AiPlayer(ActiveGameData activeGameData, BattleSalvoDisplay display) {
    this.activeGameData = activeGameData;
    this.display = display;
  }

  /**
   * Constructor for playing with the server
   *
   * @param activeGameData An ActiveGameData object for holding certain data
   * @param isLocal is the player playing locally or with a server
   */
  public AiPlayer(ActiveGameData activeGameData, Boolean isLocal) {
    this.activeGameData = activeGameData;
    this.isLocal = isLocal;
  }

  /**
   * The random seeded constructor for the AiPlayer object
   *
   * @param activeGameData An ActiveGameData object for holding certain data
   * @param display A BattleSalvoDisplay object for certain display methods in this class
   * @param random The seeded random object
   */
  public AiPlayer(ActiveGameData activeGameData, BattleSalvoDisplay display, Random random) {
    this(activeGameData, display);
    this.random = random;
  }

  /**
   * constructor with testing with the server
   *
   * @param activeGameData An ActiveGameData object for holding certain data
   * @param random The seeded random object
   */
  public AiPlayer(ActiveGameData activeGameData, Random random) {
    this(activeGameData, false);
    this.random = random;
  }

  /**
   * Get the player's name.
   *
   * @return the player's name
   */
  @Override
  public String name() {
    return "georgelawtonn";
  }

  /**
   * Given the specifications for a BattleSalvo board, return a list of ships with their locations
   * on the board.
   *
   * @param height         the height of the board, range: [6, 15] inclusive
   * @param width          the width of the board, range: [6, 15] inclusive
   * @param specifications a map of ship type to the number of occurrences each ship should
   *                       appear on the board
   * @return the placements of each ship on the board
   */
  @Override
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    this.board = new BattleSalvoBoard(width, height, random);
    this.width = width;
    this.height = height;
    activeGameData.setAiBoard(board);
    ships = board.initializeBoard(specifications);
    return ships;
  }

  /**
   * Returns this player's shots on the opponent's board. The number of shots returned should
   * equal the number of ships on this player's board that have not sunk.
   *
   * @return the locations of shots on the opponent's board
   */
  @Override
  public List<Coord> takeShots() {
    ArrayList<Coord> outboundShots = new ArrayList<>();
    int shotCount = BattleSalvoBoard.getAliveShipCount(ships);
    int remainingSlots = (height * width) - alreadyShot.size();

    if (shotCount > remainingSlots) {
      shotCount = remainingSlots;
    }
    for (int i = 0; i < shotCount; i++) {
      Coord randomCoord = generateRandomCoord();
      while (BattleSalvoBoard.alreadyShotCoord(randomCoord, alreadyShot)) {
        randomCoord = generateRandomCoord();
      }
      alreadyShot.add(randomCoord);
      outboundShots.add(randomCoord);
    }
    return outboundShots;
  }

  /**
   * Given the list of shots the opponent has fired on this player's board, report which
   * shots hit a ship on this player's board.
   *
   * @param opponentShotsOnBoard the opponent's shots on this player's board
   * @return a filtered list of the given shots that contain all locations of shots that hit a
   *         ship on this board
   */
  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    return board.checkShotLocations(opponentShotsOnBoard);
  }

  /**
   * Reports to this player what shots in their previous volley returned from takeShots()
   * successfully hit an opponent's ship.
   *
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   */
  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    if (isLocal) {
      StringBuilder successfulHits = new StringBuilder("Enemy Successful Shots: ");
      for (Coord coord : shotsThatHitOpponentShips) {
        successfulHits.append(" (" + coord.getX() + ", " + coord.getY() + ") ");
      }
      display.displayMessage(successfulHits);
    }
  }

  /**
   * Notifies the player that the game is over.
   * Win, lose, and draw should all be supported
   *
   * @param result if the player has won, lost, or forced a draw
   * @param reason the reason for the game ending
   */
  @Override
  public void endGame(GameResult result, String reason) {
    if (isLocal) {
      StringBuilder message = new StringBuilder(reason);
      display.displayMessage(message);
    }
  }

  /**
   * Generates a randomCoord based on this objects random
   *
   * @return A Coord with random numbers within the width and height of the board
   */
  private Coord generateRandomCoord() {
    int randomX;
    int randomY;
    randomX = random.nextInt(0, width);
    randomY = random.nextInt(0, height);
    return new Coord(randomX, randomY);
  }

}
