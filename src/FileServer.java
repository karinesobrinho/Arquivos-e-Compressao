import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {
    public static void iniciarServer() {
        int portNumber = 12345; // Choose a suitable port number

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Server is waiting for a connection...");

            // Wait for a client to connect
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected!");

            // Get the input stream from the client
            InputStream inputStream = clientSocket.getInputStream();

            // Specify the destination file
            // Origem e destino do arquivo
            String destinationFilePath = "D:\\DownloadsD\\file\\filme2\\filme.mkv";

            // Create a FileOutputStream to write the received file
            try (FileOutputStream fileOutputStream = new FileOutputStream(destinationFilePath)) {
                byte[] buffer = new byte[8192]; // Adjust the buffer size as needed
                int bytesRead;

                // Read from the input stream and write to the file
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }

                System.out.println("File received successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}