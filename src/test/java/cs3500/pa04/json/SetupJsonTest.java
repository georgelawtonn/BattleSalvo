package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.salvoenums.ShipType;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * Tests for SetupJson
 */
class SetupJsonTest {

  /**
   * Tests for SetupJson
   */
  @Test
  void setup() {
    Map<ShipType, Integer> specs = new HashMap<ShipType, Integer>();
    specs.put(ShipType.CARRIER, 3);
    specs.put(ShipType.BATTLESHIP, 1);
    specs.put(ShipType.DESTROYER, 2);
    specs.put(ShipType.SUBMARINE, 1);

    SetupJson setup = new SetupJson(7, 9, specs);
    assertEquals(7, setup.width());
    assertEquals(9, setup.height());
    assertEquals(3, setup.specs().get(ShipType.CARRIER));
  }
}