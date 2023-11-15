import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {
    public static void iniciarServer() {
        int portNumber = 8080; // Choose a suitable port number

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Server is waiting for a connection...");

            // Wait for a client to connect
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected!");

            // Get the output stream to send data to the client
            OutputStream outputStream = clientSocket.getOutputStream();

            // Specify the source file
            String sourceFilePath = "D:\\DownloadsD\\file\\filme\\filme.mkv";

            // Create a FileInputStream to read the source file
            try (FileInputStream fileInputStream = new FileInputStream(sourceFilePath)) {
                byte[] buffer = new byte[8192]; // Adjust the buffer size as needed
                int bytesRead;

                // Read from the file and send to the client
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                System.out.println("File sent successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}