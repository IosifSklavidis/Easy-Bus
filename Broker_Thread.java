package buspubsub;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Broker_Thread extends Thread {

    int a, b;
    String IP;
    Node node;
    public static List<Broker> brokers = new ArrayList<>();

    Broker_Thread(int a, int b, String IP) {
        this.a = a;
        this.b = b;
        this.IP = IP;
    }

    public void run() {

        Socket requestSocket = null;
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        try {

            requestSocket = new Socket(IP, 4321);
            out = new ObjectOutputStream(requestSocket.getOutputStream());
            in = new ObjectInputStream(requestSocket.getInputStream());

            out.writeInt(a);
            out.flush();
            out.writeInt(b);
            out.flush();

//            Object broker_area = in.readObject();
//            System.out.println("Broker area: " + broker_area);
            Broker broker = new Broker();

            for (int i = a; i < b; i++) {

                broker.pull(node.topics.get(i));

            }
            brokers.add(broker);

        } catch (UnknownHostException unknownHost) {
            System.err.println("You are trying to connect to an unknown host!");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } // TODO Auto-generated catch block
        finally {
            try {
                in.close();
                out.close();
                requestSocket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

}
