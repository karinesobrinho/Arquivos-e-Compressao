public class Main {

    public static void main(String[] args) throws InterruptedException {

        // Create threads for server and client tasks
        Thread serverThread = new Thread(FileServer::iniciarServer);
        Thread clientThread = new Thread(FileClient::iniciarClient);

        serverThread.start();
        Thread.sleep(1000);
        clientThread.start();

    }
}
