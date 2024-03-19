package cs3500.pa03.salvoenums;

/**
 * Represents the possible ship types in a game of BattleSalvo
 */
public enum ShipType {
  CARRIER(6, BoardCellState.CARRIER),
  BATTLESHIP(5, BoardCellState.BATTLESHIP),
  DESTROYER(4, BoardCellState.DESTROYER),
  SUBMARINE(3, BoardCellState.SUBMARINE);

  private final int size;
  private final BoardCellState boardCellState;

  /**
   * The shipType constructor
   *
   * @param size The size of the type of ship
   * @param shipType The BoardCellState of said shipType
   */
  ShipType(int size, BoardCellState shipType) {
    this.size = size;
    this.boardCellState = shipType;
  }

  /**
   * Returns the size of a ShipType
   *
   * @return int size of a ShipType
   */
  public int getSize() {
    return size;
  }

  /**
   * Returns the corresponding BoardCellState of a ShipType
   *
   * @return BoardCellState of a ShipType
   */
  public BoardCellState getBoardCellState() {
    return boardCellState;
  }
}
