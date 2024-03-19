package cs3500.pa04.proxycontroller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa03.salvoenums.GameResult;
import cs3500.pa03.salvomodel.ActiveGameData;
import cs3500.pa03.salvomodel.AiPlayer;
import cs3500.pa03.salvomodel.Coord;
import cs3500.pa03.salvomodel.Player;
import cs3500.pa03.salvomodel.Ship;
import cs3500.pa04.json.CoordJson;
import cs3500.pa04.json.CoordinatesJson;
import cs3500.pa04.json.EndJson;
import cs3500.pa04.json.FleetJson;
import cs3500.pa04.json.JoinJson;
import cs3500.pa04.json.JsonUtils;
import cs3500.pa04.json.MessageJson;
import cs3500.pa04.json.SetupJson;
import cs3500.pa04.json.ShipJson;
import cs3500.pa04.proxymodel.ShipAdapter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents the controller for the battleship with a server
 */
public class ProxyController {
  Player aiPlayer;
  private final ActiveGameData activeGameData = new ActiveGameData();
  private final Socket server;
  private final InputStream in;
  private final PrintStream out;
  private final ObjectMapper mapper = new ObjectMapper();

  /**
   * initalizes the controller for server games
   *
   * @param server the server that is hosting the game
   */
  public ProxyController(Socket server) {
    this.aiPlayer = new AiPlayer(activeGameData, false);
    this.server = server;
    try {
      this.in = server.getInputStream();
      this.out = new PrintStream(server.getOutputStream());
    } catch (IOException e) {
      throw new IllegalStateException("Invalid Server");
    }
  }

  /**
   * initalizes the controller for server games with a seeded random
   *
   * @param server that is hosting the game
   * @param random seed for testing
   */
  public ProxyController(Socket server, Random random) {
    this.aiPlayer = new AiPlayer(activeGameData, random);
    this.server = server;
    try {
      this.in = server.getInputStream();
      this.out = new PrintStream(server.getOutputStream());
    } catch (IOException e) {
      throw new IllegalStateException("Invalid Server");
    }
  }

  /**
   * runs the entire game by delegating and responding to the server messages
   */
  public void run() {
    try {
      JsonParser parser = this.mapper.getFactory().createParser(this.in);

      while (!this.server.isClosed()) {
        MessageJson message = parser.readValueAs(MessageJson.class);
        delegateMessage(message);

      }
    } catch (IOException e) {
      // Disconnected from server or parsing exception
    }
  }

  /**
   * delegates the messages depending on which method-name it is under
   *
   * @param message received from the server
   */
  private void delegateMessage(MessageJson message) throws IOException {
    String name = message.methodName();
    JsonNode arguments = message.arguments();

    switch (name) {
      case "join":
        handleJoin();
        break;
      case "setup":
        handleSetup(arguments);
        break;
      case "take-shots":
        handleTakeShots();
        break;
      case "report-damage":
        handleDamage(arguments);
        break;
      case "successful-hits":
        handleSuccessfulHits(arguments);
        break;
      case "end-game":
        handleEnd(arguments);
        break;
      default:
        break;
    }
  }

  /**
   * converts the response and the given method-name to
   * a messageJson and send it back to the server
   *
   * @param response to the message
   * @param methodName that the message is under
   */
  private void serialize(JsonNode response, String methodName) {
    MessageJson message = new MessageJson(methodName, response);
    JsonNode jsonResponse = JsonUtils.serializeRecord(message);
    this.out.println(jsonResponse);
  }


  /**
   * handles the join message by responding with name and game mode
   */
  private void handleJoin() {
    //single is against TA ai
    String name = aiPlayer.name();
    String gameType = "SINGLE";
    JoinJson response = new JoinJson(name, gameType);
    JsonNode jsonResponse = JsonUtils.serializeRecord(response);
    serialize(jsonResponse, "join");
  }


  /**
   * handles the setup message by taking the given arguments for the
   * game and set up our board and send back a list of ships
   *
   * @param arguments specifications for the setup of the game
   */
  private void handleSetup(JsonNode arguments) {
    SetupJson setupJson = this.mapper.convertValue(arguments, SetupJson.class);
    List<Ship> aiShips = aiPlayer.setup(setupJson.height(), setupJson.width(), setupJson.specs());
    List<ShipJson> ships = new ArrayList<>();
    for (Ship ship : aiShips) {
      ShipAdapter shipAdapter = new ShipAdapter(ship);
      ShipJson shipJson = new ShipJson(shipAdapter.getCoord(), shipAdapter.getLength(),
          shipAdapter.getDirection());
      ships.add(shipJson);
    }

    FleetJson fleet = new FleetJson(ships);
    JsonNode jsonResponse = JsonUtils.serializeRecord(fleet);
    serialize(jsonResponse, "setup");
  }


  /**
   * handles the take shots phase of the game and returns a list of shots that were fired
   */
  private void handleTakeShots() {
    List<Coord> aiShots = aiPlayer.takeShots();
    List<CoordJson> shots = toCoordJson(aiShots);
    CoordinatesJson coords = new CoordinatesJson(shots);
    JsonNode jsonResponse = JsonUtils.serializeRecord(coords);
    serialize(jsonResponse, "take-shots");
  }

  /**
   * converts a list of coords to list of coordJsons for easier communication with the server
   *
   * @param coords the list of coords to be convered
   * @return list of coordJsons
   */
  private List<CoordJson> toCoordJson(List<Coord> coords) {
    List<CoordJson> coordJsons = new ArrayList<>();
    for (Coord c : coords) {
      CoordJson coordJson = new CoordJson(c.getX(), c.getY());
      coordJsons.add(coordJson);
    }
    return coordJsons;
  }

  /**
   * Converts a List of CoordJson into a List of Coord
   *
   * @param coordJsons A List of coordJson
   * @return The converted List of CoordJson as Coords
   */
  private List<Coord> toCoord(List<CoordJson> coordJsons) {
    List<Coord> coord = new ArrayList<>();

    for (CoordJson cjson : coordJsons) {
      Coord c = new Coord(cjson.x(), cjson.y());
      coord.add(c);
    }
    return coord;
  }


  /**
   * handles the report damage phase with the given list of shots from the other player
   * and reports the successful hits on our board
   *
   * @param arguments the list of shots from the other player
   */
  private void handleDamage(JsonNode arguments) {
    //list of shots from the server
    CoordinatesJson fromServer = this.mapper.convertValue(arguments, CoordinatesJson.class);
    List<CoordJson> listOfShotJson = fromServer.shots();

    List<Coord> listOfShots = toCoord(listOfShotJson);
    List<Coord> hits = aiPlayer.reportDamage(listOfShots);
    List<CoordJson> hitsJson = toCoordJson(hits);

    CoordinatesJson coords = new CoordinatesJson(hitsJson);
    JsonNode jsonResponse = JsonUtils.serializeRecord(coords);
    serialize(jsonResponse, "report-damage");


  }


  /**
   * handles the successful phase of the game (received a list of coords that hit the enemy ships)
   *
   * @param arguments the shots that hit enemy ships
   */
  private void handleSuccessfulHits(JsonNode arguments) {
    CoordinatesJson successfulHitsToServer =
        this.mapper.convertValue(arguments, CoordinatesJson.class);
    List<CoordJson> listOfShotJson = successfulHitsToServer.shots();
    List<Coord> listOfSuccessfulShots = toCoord(listOfShotJson);
    aiPlayer.successfulHits(listOfSuccessfulShots);

    JsonNode emptyJsonNode = mapper.createObjectNode();
    serialize(emptyJsonNode, "successful-hits");
  }


  /**
   * handles the end message that was sent from the server
   *
   * @param arguments the game result and reason to the result of the game
   */
  private void handleEnd(JsonNode arguments) throws IOException {
    EndJson endJson = this.mapper.convertValue(arguments, EndJson.class);
    GameResult result = endJson.result();
    String reason = endJson.reason();
    aiPlayer.endGame(result, reason);

    JsonNode emptyJsonNode = mapper.createObjectNode();
    serialize(emptyJsonNode, "end-game");
    server.close();
  }
}
