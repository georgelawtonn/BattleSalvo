package cs3500.pa03.salvocontroller;

import cs3500.pa03.salvomodel.Coord;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The console reader
 */
public class ConsoleReader {
  private final Scanner scanner;

  /**
   * Sets scanner type
   *
   * @param readable Readable location
   */
  public ConsoleReader(Readable readable) {
    scanner = new Scanner(readable);
  }

  /**
   * Reads the user input and returns it in the form of an ArrayList (Singular line input)
   *
   * @return The inputted user input in an ArrayList
   */
  public ArrayList<Integer> read() {
    ArrayList<Integer> inputs = new ArrayList<>();
    if (scanner.hasNextLine()) {
      String inputtedLine = scanner.nextLine();
      Scanner lineParser = new Scanner(inputtedLine);

      while (lineParser.hasNextInt()) {
        inputs.add(lineParser.nextInt());
      }
    }
    return inputs;
  }

  /**
   * Reads user inputs and formats them into Coords which it then returns (multiline input)
   *
   * @param shotCount The amount of coordinates a user can input
   * @return An ArrayList that contains the user inputs
   */
  public ArrayList<Coord> readShots(int shotCount) {
    ArrayList<Coord> inputs = new ArrayList<>();
    for (int i = 0; i < shotCount; i++) {
      if (scanner.hasNextLine()) {
        String inputtedLine = scanner.nextLine();
        Scanner lineParser = new Scanner(inputtedLine);
        int x = lineParser.nextInt();
        int y = lineParser.nextInt();
        Coord coord = new Coord(x, y);
        inputs.add(coord);
      }
    }
    return inputs;
  }
}
