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
            printWaitingForConnectionMessage();
            while (true) {
                connectToClientSocket(serverSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
    }

    private void printWaitingForConnectionMessage() {
        System.out.println("Server is waiting for connection");
    }

    private void connectToClientSocket(ServerSocket serverSocket) throws IOException {
        Socket socket = serverSocket.accept();
        pool.execute(new ServerHandler(socket));
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
                sendMessageToAll();
            } catch (IOException e) {
                printDisconnectedMessage();
            }
        }

        private void sendMessageToAll() throws IOException {
            String message;
            while ((message = getUserInput()) != null) {
                printMessage(message);
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

        private void printDisconnectedMessage() {
            System.out.println("Client disconnected: " + socket);
        }
    }
}
