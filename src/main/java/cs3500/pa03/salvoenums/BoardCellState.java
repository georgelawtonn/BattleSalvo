package cs3500.pa03.salvoenums;

/**
 * Represents a cell within the BattleSalvoBoard
 */
public enum BoardCellState {
  HIT("H"),
  MISS("M"),
  EMPTY("0"),
  CARRIER("C"),
  BATTLESHIP("B"),
  DESTROYER("D"),
  SUBMARINE("S"),
  SHIP("S");

  private final String boardStringRepresentation;

  /**
   * BoardCellState constructor
   *
   * @param boardStringRepresentation Represents the string representation of the cellState for
   *                                  displaying boards
   */
  BoardCellState(String boardStringRepresentation) {
    this.boardStringRepresentation = boardStringRepresentation;
  }

  /**
   * Gets the boardStringRepresentation of an BoardCellState
   *
   * @return The string representation of the BoardCellState
   */
  public String getBoardStringRepresentation() {
    return boardStringRepresentation;
  }
}
