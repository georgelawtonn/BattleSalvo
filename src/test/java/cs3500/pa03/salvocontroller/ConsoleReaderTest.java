package cs3500.pa03.salvocontroller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa03.salvomodel.Coord;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for ConsoleReader
 */
public class ConsoleReaderTest {
  private ConsoleReader consoleReader;
  private ConsoleReader consoleReaderTwo;
  private ConsoleReader consoleReaderThree;

  /**
   * Setup for testing
   */
  @BeforeEach
  public void setUp() {
    String input = "1 2 3 4 5";
    String inputTwo = "1 2" + System.lineSeparator() + "3 4";
    String inputThree = "";
    consoleReader = new ConsoleReader(new StringReader(input));
    consoleReaderTwo = new ConsoleReader(new StringReader(inputTwo));
    consoleReaderThree = new ConsoleReader(new StringReader(inputThree));
  }

  /**
   * Tests for read
   */
  @Test
  public void testRead() {
    ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    assertEquals(expected, consoleReader.read());
    assertTrue(consoleReaderThree.read().isEmpty());
  }

  /**
   * Tests for readShots
   */
  @Test
  public void testReadShots() {
    int shotCount = 2;
    ArrayList<Coord> expected = new ArrayList<>(Arrays.asList(
        new Coord(1, 2),
        new Coord(3, 4)
    ));
    ArrayList<Coord> actual = consoleReaderTwo.readShots(shotCount);
    assertEquals(expected.get(0).getX(), actual.get(0).getX());
    assertEquals(expected.get(1).getY(), actual.get(1).getY());
    assertEquals(expected.get(0).getX(), actual.get(0).getX());
    assertEquals(expected.get(1).getY(), actual.get(1).getY());
    assertTrue(consoleReaderThree.readShots(1).isEmpty());
  }
}
