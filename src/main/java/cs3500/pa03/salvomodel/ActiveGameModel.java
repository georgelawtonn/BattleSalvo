package cs3500.pa03.salvomodel;

import cs3500.pa03.salvoenums.GameResult;
import cs3500.pa03.salvoenums.ShipType;
import cs3500.pa03.salvoenums.VisualType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The main logic point of the overarching game, maintains interactions between players,
 * and determines winner
 */
public class ActiveGameModel {
  private final Player aiPlayer;
  private final Player manualPlayer;
  private final ActiveGameData activeGameData;
  private List<Ship> manualShips;
  private List<Ship> aiShips;
  private final List<Coord> alreadyShot = new ArrayList<>();

  /**
   * The constructor for the ActiveGameModel
   *
   * @param aiPlayer The aiPlayer Player object
   * @param manualPlayer The manualPlayer Player object
   * @param activeGameData An activeGameData object
   */
  public ActiveGameModel(Player aiPlayer, Player manualPlayer, ActiveGameData activeGameData) {
    this.aiPlayer = aiPlayer;
    this.manualPlayer = manualPlayer;
    this.activeGameData = activeGameData;
  }

  /**
   * Setups both ai and manual players, sets the output of setup method to List of Ship fields
   * for future use
   *
   * @param height The height of the board
   * @param width The width of the board
   * @param specifications The specifications for ShipType to number of ShipType
   */
  public void setupPlayers(int height, int width, HashMap<ShipType, Integer> specifications) {
    aiShips = aiPlayer.setup(height, width, specifications);
    manualShips = manualPlayer.setup(height, width, specifications);
  }

  /**
   * Returns the number of ALIVE ships in the manualShips list
   *
   * @return An integer representation of the number of living ships
   */
  public int getManualShotCount() {
    return BattleSalvoBoard.getAliveShipCount(manualShips);
  }

  /**
   * The main interactions between the aiPlayer and manualPlayer, takesShots from both, determines
   * resulting damage, and reports successful to both
   */
  public void shootVolley() {
    List<Coord> manualShots = manualPlayer.takeShots();
    List<Coord> aiShots = aiPlayer.takeShots();
    List<Coord> successfulShotsOnManual = manualPlayer.reportDamage(aiShots);
    List<Coord> successfulShotsOnAi = aiPlayer.reportDamage(manualShots);
    manualPlayer.successfulHits(successfulShotsOnAi);
    aiPlayer.successfulHits(successfulShotsOnManual);
  }

  /**
   * Creates a full opponent board, user board visual, with board visual being determined by the
   * passed in VisualType
   *
   * @param aiVisual The VisualType of the aiBoard
   * @param userVisual The VisualType of the manualBoard
   * @return The StringBuilder representation of both boards
   */
  public StringBuilder retrieveBoards(VisualType aiVisual, VisualType userVisual) {
    StringBuilder boards = new StringBuilder("Opponent Board:");
    boards.append(System.lineSeparator());
    boards.append(activeGameData.getAiBoard(aiVisual));
    boards.append(System.lineSeparator());
    boards.append("Your Board:");
    boards.append(System.lineSeparator());
    boards.append(activeGameData.getManualBoard(userVisual));
    return boards;
  }

  /**
   * Determines whether the game should continue based on aliveShipCounts
   *
   * @return A boolean to continue or not
   */
  public boolean gameContinues() {
    return BattleSalvoBoard.getAliveShipCount(manualShips) > 0
        && BattleSalvoBoard.getAliveShipCount(aiShips) > 0;
  }

  /**
   * Determines which Player object has won/lost/drew the game and calls upon the respective
   * endGame methods
   */
  public void determineWinner() {
    int manualShipsAlive = BattleSalvoBoard.getAliveShipCount(manualShips);
    int aiAlive = BattleSalvoBoard.getAliveShipCount(aiShips);
    if (manualShipsAlive == 0 && aiAlive == 0) {
      manualPlayer.endGame(GameResult.DRAW, "Manual Draw : Lost All Ships");
      aiPlayer.endGame(GameResult.DRAW, "AI Draw : Lost All Ships");
    } else if (manualShipsAlive > 0) {
      manualPlayer.endGame(GameResult.WIN, "Manual Victory: Destroyed All Ships");
      aiPlayer.endGame(GameResult.LOSE, "AI Loss : Lost All Ships");
    } else {
      manualPlayer.endGame(GameResult.LOSE, "Manual Loss : Lost All Ships");
      aiPlayer.endGame(GameResult.WIN, "AI Victory : Destroyed All Ships");
    }
  }

  /**
   * Determines whether the given List of coord are valid as defined within the rules of BattleSalvo
   *
   * @param coords The List of coord to be validated
   * @param height The height of the board
   * @param width The width of the board
   * @return A boolean stating whether the List was valid or not
   */
  public boolean validateCoords(List<Coord> coords, int height, int width) {
    if (hasDuplicates(coords)) {
      return false;
    }
    for (Coord coord : coords) {
      if (coord.getY() > height - 1 || coord.getX() > width - 1
          || BattleSalvoBoard.alreadyShotCoord(coord, alreadyShot)) {
        return false;
      }
    }
    alreadyShot.addAll(coords);
    return true;
  }

  /**
   * Determines whether the List of coords contains any duplicate elements
   *
   * @param coords The List of coords to be questioned
   * @return A boolean stating whether the List had duplicates
   */
  private boolean hasDuplicates(List<Coord> coords) {
    for (int i = 0; i < coords.size(); i++) {
      Coord currentCoord = coords.get(i);
      for (int j = 0; j < coords.size(); j++) {
        Coord otherCoord = coords.get(j);
        if (otherCoord.isSameAs(currentCoord) && i != j) {
          return true;
        }
      }
    }
    return false;
  }
}
