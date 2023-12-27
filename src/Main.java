import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author chenjp
 * @version 1.0
 * @date ${DATE} ${TIME}
 */
public class Main {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 8000;

        try (Socket socket = new Socket(serverAddress, serverPort)) {
            System.out.println("Connected to server");

            // Get the output stream to send data to the server
            OutputStream outputStream = socket.getOutputStream();

            // Get the input stream to receive data from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Send a message to the server
            String messageToSend = "Hello from the client!";
            System.out.println("Sending: " + messageToSend);
            outputStream.write(messageToSend.getBytes());
            outputStream.flush();

            // Receive a response from the server
            String receivedMessage = reader.readLine();
            System.out.println("Received: " + receivedMessage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}