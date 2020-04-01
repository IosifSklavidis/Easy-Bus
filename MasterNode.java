package buspubsub;

import java.io.*;
import java.net.*;
import java.util.Map;

public class MasterNode extends Thread {

    ObjectInputStream in;
    ObjectOutputStream out;

    public MasterNode(Socket connection) {
        try {
            out = new ObjectOutputStream(connection.getOutputStream());
            in = new ObjectInputStream(connection.getInputStream());
        } catch (IOException e) {
            // e.printStackTrace();
        }
    }

    public void run() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (NullPointerException e) {
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

}
