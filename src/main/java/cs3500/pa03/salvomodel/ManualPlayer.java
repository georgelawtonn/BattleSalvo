package cs3500.pa03.salvomodel;

import cs3500.pa03.salvoenums.GameResult;
import cs3500.pa03.salvoenums.ShipType;
import cs3500.pa03.salvoview.BattleSalvoDisplay;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * The ManualPlayer object which implements the Player interface
 */
public class ManualPlayer implements Player {
  private BattleSalvoBoard board;
  private final ActiveGameData activeGameData;
  private final BattleSalvoDisplay display;
  private Random random = new Random();

  /**
   * The main constructor for the ManualPlayer object
   *
   * @param activeGameData An ActiveGameData object for holding certain data
   * @param display A BattleSalvoDisplay object for certain display methods in this class
   */
  public ManualPlayer(ActiveGameData activeGameData, BattleSalvoDisplay display) {
    this.activeGameData = activeGameData;
    this.display = display;
  }

  /**
   * The random seeded constructor for the ManualPlayer object
   *
   * @param activeGameData An ActiveGameData object for holding certain data
   * @param display A BattleSalvoDisplay object for certain display methods in this class
   * @param random The seeded random object
   */
  public ManualPlayer(ActiveGameData activeGameData, BattleSalvoDisplay display, Random random) {
    this(activeGameData, display);
    this.random = random;
  }

  /**
   * Get the player's name.
   *
   * @return the player's name
   */
  @Override
  public String name() {
    return "Player";
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
    activeGameData.setManualBoard(board);
    return board.initializeBoard(specifications);
  }

  /**
   * Returns this player's shots on the opponent's board. The number of shots returned should
   * equal the number of ships on this player's board that have not sunk.
   *
   * @return the locations of shots on the opponent's board
   */
  @Override
  public List<Coord> takeShots() {
    return activeGameData.getManualShots();
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
    StringBuilder successfulHits = new StringBuilder("Successful Shots: ");
    for (Coord coord : shotsThatHitOpponentShips) {
      successfulHits.append(" (" + coord.getX() + ", " + coord.getY() + ") ");
    }
    display.displayMessage(successfulHits);
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
    StringBuilder message = new StringBuilder(reason);
    display.displayMessage(message);
  }
}
