public class RunServer {
    private final static int portNumber = 5000;
    private final static int maxNumberOfClients = 10;

    public static void main(String[] args) {
        Server server = new Server();
        server.startServer(portNumber, maxNumberOfClients);
    }
}