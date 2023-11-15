import java.io.*;
import java.nio.file.*;

public class LargeFileTransfer {

    // Função que transfere o arquivo através de buffers
    public static void transfer() throws IOException {
        // Origem e destino do arquivo
        String sourcePath = "D:\\DownloadsD\\file\\filme\\filme.mkv";
        String destinationPath = "D:\\DownloadsD\\file\\filme2\\filme.mkv";

        // Definir tamanho do buffer
        int bufferSize = 1024 * 1024 * 10; // buffer de 10MB
        byte[] buffer = new byte[bufferSize];

        InputStream inputStream = new BufferedInputStream(new FileInputStream(sourcePath), bufferSize);
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(destinationPath), bufferSize);


        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        System.out.println("Feito");
    }

    // Função que transfere o arquivo através de um único buffer
    public static void transferAll() throws IOException {
//        String sourcePath = "D:\\DownloadsD\\file\\1\\video.mp4";
//        String destinationPath = "D:\\DownloadsD\\file\\2\\video.mp4";

        String sourcePath = "D:\\DownloadsD\\file\\filme\\filme.mkv";
        String destinationPath = "D:\\DownloadsD\\file\\filme2\\filme.mkv";

        byte[] fileData = Files.readAllBytes(Paths.get(sourcePath));

        Files.write(Paths.get(destinationPath), fileData, StandardOpenOption.CREATE);

        System.out.println("File transfer successful!");
    }
}