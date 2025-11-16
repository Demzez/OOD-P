import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] arg) {
        ServerSocket serverSocket = null;
        Socket clientAccepted = null;
        ObjectInputStream sois = null;
        ObjectOutputStream soos = null;

        try {
            System.out.println("Server starting....");
            serverSocket = new ServerSocket(5252);

            while (true) {
                System.out.println("Waiting for client connection...");
                clientAccepted = serverSocket.accept();
                System.out.println("Connection established....");

                sois = new ObjectInputStream(clientAccepted.getInputStream());
                soos = new ObjectOutputStream(clientAccepted.getOutputStream());


                new ClientHandler(clientAccepted, sois, soos).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (sois != null) sois.close();
                if (soos != null) soos.close();
                if (clientAccepted != null) clientAccepted.close();
                if (serverSocket != null) serverSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

