package cs3500.pa04.proxycontroller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.MockInputStream;
import cs3500.MockOutputStream;
import cs3500.MockSocket;
import cs3500.pa03.salvoenums.GameResult;
import cs3500.pa03.salvoenums.ShipType;
import cs3500.pa04.json.CoordJson;
import cs3500.pa04.json.CoordinatesJson;
import cs3500.pa04.json.EndJson;
import cs3500.pa04.json.JsonUtils;
import cs3500.pa04.json.MessageJson;
import cs3500.pa04.json.SetupJson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for ProxyController
 */
class ProxyControllerTest {
  JsonNode setupNodeMessage;
  JsonNode joinNodeMessage;
  JsonNode takeShotsNodeMessage;
  JsonNode reportDamageMessage;
  JsonNode successfulHitsMessage;
  JsonNode endMessage;
  MockSocket mockSocket;
  ProxyController proxyController;

  /**
   * Setup for ProxyController tests
   */
  @BeforeEach
  void setUp() {
    Map<ShipType, Integer> fleetSpec = new HashMap<>();
    fleetSpec.put(ShipType.CARRIER, 1);
    fleetSpec.put(ShipType.BATTLESHIP, 1);
    fleetSpec.put(ShipType.DESTROYER, 1);
    fleetSpec.put(ShipType.SUBMARINE, 1);
    SetupJson setupJson = new SetupJson(6, 6, fleetSpec);
    JsonNode setupNode = JsonUtils.serializeRecord(setupJson);
    MessageJson messageSetup = new MessageJson("setup", setupNode);
    setupNodeMessage = JsonUtils.serializeRecord(messageSetup);
    ObjectMapper mapper = new ObjectMapper();
    JsonNode emptyNode = mapper.createObjectNode();
    MessageJson messageJoin = new MessageJson("join", emptyNode);
    joinNodeMessage = JsonUtils.serializeRecord(messageJoin);
    MessageJson messageTakeShots = new MessageJson("take-shots", emptyNode);
    takeShotsNodeMessage = JsonUtils.serializeRecord(messageTakeShots);
    ArrayList<CoordJson> coordJsonArrayListJson = new ArrayList<>(List.of(new CoordJson(0, 0)));
    CoordinatesJson coordinatesJson = new CoordinatesJson(coordJsonArrayListJson);
    JsonNode coordsJson = JsonUtils.serializeRecord(coordinatesJson);
    MessageJson messageReportDamage = new MessageJson("report-damage", coordsJson);
    reportDamageMessage = JsonUtils.serializeRecord(messageReportDamage);
    MessageJson messageSuccessfulHits = new MessageJson("successful-hits", coordsJson);
    successfulHitsMessage = JsonUtils.serializeRecord(messageSuccessfulHits);
    EndJson endJson = new EndJson(GameResult.WIN, "WINNER");
    JsonNode endNode = JsonUtils.serializeRecord(endJson);
    MessageJson messageEnd = new MessageJson("end-game", endNode);
    endMessage = JsonUtils.serializeRecord(messageEnd);
  }

  /**
   * Tests for handleJoin
   */
  @Test
  void runJoin() {
    String data = joinNodeMessage.toString();
    MockInputStream inputStream = new MockInputStream(data);
    MockOutputStream outputStream = new MockOutputStream();
    mockSocket = new MockSocket(inputStream, outputStream);
    proxyController = new ProxyController(mockSocket, new Random(3));
    proxyController.run();
    String output = outputStream.getOutput();
    assertEquals(output.trim(),
        ("{\"method-name\":\"join\",\"arguments\":{\"name\":"
            + "\"georgelawtonn\",\"game-type\":\"SINGLE\"}}").trim());
  }

  /**
   * Tests for handleSetup, handleTakeShots and handleDamage
   */
  @Test
  void runVolley() {
    String data = setupNodeMessage.toString() + takeShotsNodeMessage.toString()
        + reportDamageMessage.toString();
    MockInputStream inputStream = new MockInputStream(data);
    MockOutputStream outputStream = new MockOutputStream();
    mockSocket = new MockSocket(inputStream, outputStream);
    proxyController = new ProxyController(mockSocket, new Random(3));
    proxyController.run();
    String output = outputStream.getOutput();
    String output1 = "{\"method-name\":\"setup\",\"arguments\":{\"fleet\":"
        + "[{\"coord\":{\"x\":0,\"y\":2},\"length\":6,\"direction\":\"HORIZONTAL\"},"
        + "{\"coord\":{\"x\":0,\"y\":0},\"length\":5,\"direction\":\"HORIZONTAL\"},"
        + "{\"coord\":{\"x\":1,\"y\":4},\"length\":4,\"direction\":\"HORIZONTAL\"},{\"coord\":"
        + "{\"x\":0,\"y\":3},\"length\":3,\"direction\":\"HORIZONTAL\"}]}}";
    String output2 =
        "{\"method-name\":\"take-shots\",\"arguments\":{\"coordinates\":[{\"x\":5,\"y\":4},"
            + "{\"x\":3,\"y\":4},{\"x\":0,\"y\":3},{\"x\":4,\"y\":1}]}}";
    String output3 =
        "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[{\"x\":0,\"y\":0}]}}";
    assertEquals(output.trim(),
        (output1 + System.lineSeparator() + output2 + System.lineSeparator() + output3).trim());

  }

  /**
   * Tests for handleSuccessfulHits
   */
  @Test
  void runSuccessfulHits() {
    String data = successfulHitsMessage.toString();
    MockInputStream inputStream = new MockInputStream(data);
    MockOutputStream outputStream = new MockOutputStream();
    mockSocket = new MockSocket(inputStream, outputStream);
    proxyController = new ProxyController(mockSocket, new Random(3));
    proxyController.run();
    String output = outputStream.getOutput();
    assertEquals(output.trim(), "{\"method-name\":\"successful-hits\",\"arguments\":{}}");
  }

  /**
   * Tests for handleEnd
   */
  @Test
  void runEndMessage() {
    String data = endMessage.toString();
    MockInputStream inputStream = new MockInputStream(data);
    MockOutputStream outputStream = new MockOutputStream();
    mockSocket = new MockSocket(inputStream, outputStream);
    proxyController = new ProxyController(mockSocket, new Random(3));
    proxyController.run();
    String output = outputStream.getOutput();
    assertEquals(output.trim(), "{\"method-name\":\"end-game\",\"arguments\":{}}");
  }

}