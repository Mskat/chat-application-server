import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private ExecutorService pool = null;
    private ChatParticipant chatParticipants = new ChatParticipant();

    public void startServer(int portNumber, int maxNumberOfClients) {
        pool = Executors.newFixedThreadPool(maxNumberOfClients);
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Server is waiting for connection");
            while (true) {
                Socket socket = serverSocket.accept();
                pool.execute(new ServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
    }

    class ServerHandler implements Runnable {
        private Socket socket;
        private BufferedReader input;
        private PrintWriter output;

        public ServerHandler(Socket socket) throws IOException {
            this.socket = socket;
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Client accepted");
            chatParticipants.addParticipant(output);
        }

        @Override
        public void run() {
            try {
                printMessage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String getUserInput() throws IOException {
            return input.readLine();
        }

        private void printMessage(String message) {
            for (PrintWriter participant : chatParticipants.getChatParticipants()) {
                participant.println(message);
            }
        }
    }
}
