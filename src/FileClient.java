import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;

public class FileClient {
    public static void iniciarClient() {
        String serverAddress = "127.0.0.1"; // Replace with the server's IP address
        int serverPort = 8080; // Replace with the server's port number

        try (Socket socket = new Socket(serverAddress, serverPort)) {
            // Get the input stream from the server
            InputStream inputStream = socket.getInputStream();

            // Specify the destination file
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