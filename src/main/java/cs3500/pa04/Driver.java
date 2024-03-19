package cs3500.pa04;

import cs3500.pa03.salvocontroller.BattleSalvoController;
import cs3500.pa04.proxycontroller.ProxyController;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Represents the driver class of the program
 */
public class Driver {

  /**
   * the program entry point to the game
   *
   * @param args the arguments for the two game modes of the game
   * @throws IOException if there are any input and output errors
   */
  public static void main(String[] args) throws IOException {
    if (args.length == 0) {
      Appendable appendable = System.out;
      Readable readable = new InputStreamReader(System.in);
      BattleSalvoController battleSalvoController = new BattleSalvoController(appendable, readable);
      battleSalvoController.run();
    } else if (args.length == 2) {
      try {
        int portInt = Integer.parseInt(args[1]);

        ProxyController controller;
        Socket socket = new Socket(args[0], portInt);
        controller = new ProxyController(socket);
        controller.run();
      } catch (NumberFormatException e) {
        throw new IOException("please enter the right input for host and port to the game server");
      }

    } else {
      throw new IOException("please enter the right input for the desired game mode");
    }
  }
}
