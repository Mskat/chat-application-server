import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public void startServer(int portNumber, int maxNumberOfClients) {
        ExecutorService pool = Executors.newFixedThreadPool(maxNumberOfClients);
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
        }

        @Override
        public void run() {
            try {
                printMessage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void printMessage() throws IOException {
            String message;
            while ((message = readMessage()) != null) {
                System.out.println(message);
            }
        }

        private String readMessage() throws IOException {
            return input.readLine();
        }
    }
}
