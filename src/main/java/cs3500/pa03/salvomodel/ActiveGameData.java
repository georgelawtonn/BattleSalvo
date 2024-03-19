package cs3500.pa03.salvomodel;

import cs3500.pa03.salvoenums.VisualType;
import java.util.ArrayList;

/**
 * Maintains certain pieces of data including manualShots, aiBoard, and manualBoard,
 * to allow access to the visualRepresentations and user inputted shots
 */
public class ActiveGameData {
  private ArrayList<Coord> manualShots;
  private BattleSalvoBoard aiBoard;
  private BattleSalvoBoard manualBoard;

  /**
   * Gets the ArrayList manualShots
   *
   * @return An ArrayList with the user inputs
   */
  public ArrayList<Coord> getManualShots() {
    return manualShots;
  }

  /**
   * Sets the manualShots field
   *
   * @param manualShots The ArrayList that manualShots will be set to
   */
  public void setManualShots(ArrayList<Coord> manualShots) {
    this.manualShots = manualShots;
  }

  /**
   * Sets the aiBoard field
   *
   * @param aiBoard The BattleSalvoBoard that aiBoard will be set to
   */
  public void setAiBoard(BattleSalvoBoard aiBoard) {
    this.aiBoard = aiBoard;
  }

  /**
   * Sets the manualBoard field
   *
   * @param manualBoard The BattleSalvoBoard that manualBoard will be set to
   */
  public void setManualBoard(BattleSalvoBoard manualBoard) {
    this.manualBoard = manualBoard;
  }

  /**
   * Gets the aiBoard field's visual StringBuilder representation
   *
   * @param visualType The perspective to be shown
   * @return A StringBuilder representation of the aiBoard
   */
  public StringBuilder getAiBoard(VisualType visualType) {
    return aiBoard.generateBoardVisual(visualType);
  }

  /**
   * Gets the manualBoard field's visual StringBuilder representation
   *
   * @param visualType The perspective to be shown
   * @return A StringBuilder representation of the manualBoard
   */
  public StringBuilder getManualBoard(VisualType visualType) {
    return manualBoard.generateBoardVisual(visualType);
  }
}
