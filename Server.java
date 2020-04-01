package buspubsub;

import java.io.*;
import java.net.*;

public class Server {

    public static void main(String args[]) {
        new Server().openServer();
    }

    ServerSocket providerSocket;
    Socket connection = null;

    void openServer() {
        try {
            providerSocket = new ServerSocket(4321, 10);
            while (true) {
                connection = providerSocket.accept();
                Thread t = new MasterNode(connection);
                t.start();

            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            try {
                providerSocket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
