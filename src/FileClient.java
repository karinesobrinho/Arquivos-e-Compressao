import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;

public class FileClient {
    public static void iniciarClient() {
        String serverAddress = "127.0.0.1"; // Replace with the server's IP address
        int serverPort = 12345; // Replace with the server's port number

        try (Socket socket = new Socket(serverAddress, serverPort)) {
            // Specify the source file
            String sourceFilePath = "D:\\DownloadsD\\file\\filme\\filme.mkv";

            // Get the output stream to send data to the server
            OutputStream outputStream = socket.getOutputStream();

            // Create a FileInputStream to read the source file
            try (FileInputStream fileInputStream = new FileInputStream(sourceFilePath)) {
                byte[] buffer = new byte[8192]; // Adjust the buffer size as needed
                int bytesRead;

                // Read from the file and send to the server
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