import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

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
            // Send a message to the server
            String messageToSend = "Hello from the client!";
            System.out.println("Sending: " + messageToSend);
            outputStream.write(messageToSend.getBytes());
            outputStream.flush();

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a string: ");
            InputStream inputStream = socket.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            while (true) {
                try {
                    String inputString = scanner.nextLine();
                    System.out.println("You entered: " + inputString);
                    outputStream.write(inputString.getBytes());
                    outputStream.flush();

                    // Get the input stream to receive data from the server
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    bytesRead = bufferedInputStream.read(buffer);
                    if (bytesRead != -1) {
                        System.out.println("Received " + bytesRead + " bytes: " + new String(buffer, 0, bytesRead));
                    }
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}