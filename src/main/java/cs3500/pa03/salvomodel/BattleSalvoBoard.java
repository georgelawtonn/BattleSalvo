package cs3500.pa03.salvomodel;

import cs3500.pa03.salvoenums.BoardCellState;
import cs3500.pa03.salvoenums.ShipType;
import cs3500.pa03.salvoenums.VisualType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Represents the BattleSalvoBoard and maintains many of the methods necessary to modify said board
 * data
 */
public class BattleSalvoBoard {
  private final BoardCellState[][] userBoardSetupVisual;
  private final BoardCellState[][] userBoardOpponentPov;
  private final BoardCellState[][] userBoardUserPov;
  private final int height;
  private final int width;
  private final ArrayList<Ship> ships = new ArrayList<>();
  private final Random random;

  /**
   * The constructor for the BattleSalvoBoard
   *
   * @param width The width of the board
   * @param height The height of the board
   * @param random The random object to be used in this BattleSalvoBoard
   */
  public BattleSalvoBoard(int width, int height, Random random) {
    this.random = random;
    userBoardSetupVisual = generateBlankBoard(width, height);
    userBoardOpponentPov = generateBlankBoard(width, height);
    userBoardUserPov = generateBlankBoard(width, height);
    this.height = height;
    this.width = width;
  }

  /**
   * Initializes main data for board, (generates Ships, and base board generation)
   *
   * @param specifications The ShipType to number specification Map
   * @return The ArrayList of Ships that was generated within the board
   */
  public ArrayList<Ship> initializeBoard(Map<ShipType, Integer> specifications) {
    int numCarrier = specifications.get(ShipType.CARRIER);
    generateShipAmount(ShipType.CARRIER, numCarrier);
    int numBattleship = specifications.get(ShipType.BATTLESHIP);
    generateShipAmount(ShipType.BATTLESHIP, numBattleship);
    int numDestroyer = specifications.get(ShipType.DESTROYER);
    generateShipAmount(ShipType.DESTROYER, numDestroyer);
    int numSubmarine = specifications.get(ShipType.SUBMARINE);
    generateShipAmount(ShipType.SUBMARINE, numSubmarine);
    return ships;
  }

  /**
   * Generates a specified number of a singular shipType within the board
   *
   * @param shipType The type of ship to be generated
   * @param amount The number of ships to be generated
   */
  private void generateShipAmount(ShipType shipType, int amount) {
    for (int i = 0; i < amount; i++) {
      Ship ship = generateShip(shipType);
      ships.add(ship);
    }
  }

  /**
   * Generates a singular Ship that meets the board requirements
   *
   * @param shipType The type of ship to be generated
   * @return The Ship that was generated
   */
  private Ship generateShip(ShipType shipType) {
    int placementSize = shipType.getSize();
    Coord[] shipCoords = generatePossibleCoords(placementSize);
    for (Coord coord : shipCoords) {
      int x = coord.getX();
      int y = coord.getY();
      userBoardSetupVisual[x][y] = shipType.getBoardCellState();
      userBoardUserPov[x][y] = BoardCellState.SHIP;
    }
    return new Ship(shipCoords, shipType);
  }

  /**
   * Generates a possible Array of coords for a Ship
   *
   * @param placementSize The number of Coord in the Array
   * @return The Array of coords
   */
  private Coord[] generatePossibleCoords(int placementSize) {
    Coord[] generatedCoords = generateCoords(placementSize);
    while (overlappingCoords(generatedCoords)) {
      generatedCoords = generateCoords(placementSize);
    }
    return generatedCoords;
  }

  /**
   * Generates a possible/non-possible Array of coords for a Ship
   *
   * @param placementSize The number of Coord in the Array
   * @return The Array of coords
   */
  private Coord[] generateCoords(int placementSize) {
    int maxVertical = height;
    int maxHorizontal = width;
    boolean isHorizontalPlacement = random.nextBoolean();
    Coord[] coords = new Coord[placementSize];
    if (isHorizontalPlacement) {
      maxHorizontal = maxHorizontal - placementSize + 1;
    } else {
      maxVertical = maxVertical - placementSize + 1;
    }
    int randomY = random.nextInt(0, maxVertical);
    int randomX = random.nextInt(0, maxHorizontal);
    for (int i = 0; i < placementSize; i++) {
      coords[i] = new Coord(randomX, randomY);
      if (isHorizontalPlacement) {
        randomX++;
      } else {
        randomY++;
      }
    }
    return coords;
  }

  /**
   * Determines whether generatedCoords has any Coord that a Ship has already occupied
   *
   * @param generatedCoords The generatedCoord Array
   * @return A boolean to convey whether they overlapped or did not
   */
  private boolean overlappingCoords(Coord[] generatedCoords) {
    for (Ship ship : ships) {
      for (Coord coord : generatedCoords) {
        if (ship.isLocatedHere(coord)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Determines the number of ALIVE Ships within a List of Ship
   *
   * @param ships The List of Ship
   * @return The number of ALIVE Ships within the List of Ship
   */
  public static int getAliveShipCount(List<Ship> ships) {
    int aliveShips = 0;
    for (Ship ship : ships) {
      if (ship.getShipState().name().equals("ALIVE")) {
        aliveShips++;
      }
    }
    return aliveShips;
  }

  /**
   * Handles updating the boards based on the List of Coord input
   *
   * @param opponentsShotsOnBoard The locations of shots on the board
   * @return The successful shots on the board as an ArrayList of Coord
   */
  public ArrayList<Coord> checkShotLocations(List<Coord> opponentsShotsOnBoard) {
    ArrayList<Coord> successfulShots = handleSuccessfulShots(opponentsShotsOnBoard);
    ArrayList<Coord> opponentsShotsCopy = new ArrayList<>(opponentsShotsOnBoard);
    opponentsShotsCopy.removeAll(successfulShots);
    handleMissedShots(opponentsShotsCopy);
    return successfulShots;
  }

  /**
   * Handles updating the board for successful shots
   *
   * @param opponentsShotsOnBoard The locations of shots on the board
   * @return The successful shots on the board as an ArrayList of Coord
   */
  private ArrayList<Coord> handleSuccessfulShots(List<Coord> opponentsShotsOnBoard) {
    ArrayList<Coord> successfulShots = new ArrayList<>();
    for (Ship ship : ships) {
      for (Coord coord : opponentsShotsOnBoard) {
        if (ship.isLocatedHere(coord)) {
          int x = coord.getX();
          int y = coord.getY();
          userBoardUserPov[x][y] = BoardCellState.HIT;
          userBoardOpponentPov[x][y] = BoardCellState.HIT;
          successfulShots.add(coord);
          ship.decrementShipHealth();
        }
      }
    }
    return successfulShots;
  }

  /**
   * Handles updating the board for missed shots
   *
   * @param missedShots The locations of missed shots on the board
   */
  private void handleMissedShots(List<Coord> missedShots) {
    for (Coord coord : missedShots) {
      int x = coord.getX();
      int y = coord.getY();
      userBoardUserPov[x][y] = BoardCellState.MISS;
      userBoardOpponentPov[x][y] = BoardCellState.MISS;
    }
  }

  /**
   * Determines whether randomCoord is within the List of alreadyShot
   *
   * @param randomCoord The Coord that may/may not be within alreadyShot
   * @param alreadyShot The List of Coord that randomCoord may be in
   * @return A boolean that states whether randomCoord is in alreadyShot
   */
  public static boolean alreadyShotCoord(Coord randomCoord, List<Coord> alreadyShot) {
    for (Coord coord : alreadyShot) {
      if (coord.isSameAs(randomCoord)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Generates the StringBuilder repesentation of the board fields within BattleSalvoBoard
   *
   * @param visualType The type of visual to be generated
   * @return The StringBuilder representation of the board field that was chosen
   */
  public StringBuilder generateBoardVisual(VisualType visualType) {
    String visual = visualType.name();
    BoardCellState[][] visualBoard;
    if (visual.equals("INITIAL")) {
      visualBoard = this.userBoardSetupVisual;
    } else if (visual.equals("USER")) {
      visualBoard = this.userBoardUserPov;
    } else {
      visualBoard = this.userBoardOpponentPov;
    }
    StringBuilder boardInitial = new StringBuilder();
    for (int y = 0; y < height; y++) {
      StringBuilder row = new StringBuilder();
      for (int x = 0; x < width; x++) {
        row.append(visualBoard[x][y].getBoardStringRepresentation());
        row.append(" ");
      }
      row.append(System.lineSeparator());
      boardInitial.append(row);
    }
    return boardInitial;
  }

  /**
   * Generates an empty board with proper sizing based on width and height
   *
   * @param width The width of the board
   * @param height The height of the board
   * @return An (BoardCellState.Empty) Array of Arrays of BoardCellState
   */
  private BoardCellState[][] generateBlankBoard(int width, int height) {
    BoardCellState[][] blankBoard = new BoardCellState[width][height];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        blankBoard[x][y] = BoardCellState.EMPTY;
      }
    }
    return blankBoard;
  }

}
