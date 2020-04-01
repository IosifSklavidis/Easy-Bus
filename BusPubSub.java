/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buspubsub;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author admin
 */
public class BusPubSub {

    /**
     * @param args the command line arguments
     * @throws java.net.UnknownHostException
     */
    public static void main(String[] args) throws UnknownHostException {

        String host = InetAddress.getLocalHost().getHostAddress();
        String[] split = host.split("\\.");
        List<String> serverList = new ArrayList<>();
        System.out.println("-- Looking for Brokers waiting... --\n");

        for (int i = 1; i < 255; i++) {
            String IP = split[0] + "." + split[1] + "." + split[2] + "." + i;

            Socket socket;
            try {
                socket = new Socket();
                socket.connect(new InetSocketAddress(IP, 4321), 50);
                System.out.println("Found Broker waiting - IP: " + IP);
                serverList.add(IP);
                socket.close();
            } catch (IOException e) {
                // System.out.println(IP + " - ");
            }
        }

        if (serverList.isEmpty()) {
            System.out.println("No Brokers found.");
            System.exit(1);
        }

        Scanner input = new Scanner(System.in);

        Node node = new Node();
        node.init();
        int size = node.topics.size();

        System.out.println("\n-- Initializing brokers key areas... --");

        System.out.println("\n1st broker area: " + 1 + " - " + size / 2);
        System.out.println("2nd broker area: " + size / 2 + " - " + size);

        if (serverList.size() == 2) {
            new Broker_Thread(0, size / 2, serverList.get(0)).start();
            new Broker_Thread(size / 2, size, serverList.get(1)).start();
        } else if (serverList.size() < 2) {
            new Broker_Thread(0, size, serverList.get(0)).start();
        }

        Publisher firstPub = new PublisherImpl();
        Publisher secondPub = new PublisherImpl();
        Consumer consumer = new ConsumerImpl();

        System.out.println("\n-- 1st Publisher - Please give the busline you want. --\n");
        System.out.print("> Enter the busLine: ");
        while (!input.hasNextInt()) {
            System.out.print("> Invalid. Try again: ");
            input.next();
        }
        int firstpub = input.nextInt();

        Broker_Thread.brokers.forEach((broker) -> {
            firstPub.push(node.topics.get(firstpub), broker);
        });

        System.out.println("\n-- 2nd Publisher - Please give the busline you want. --\n");
        System.out.print("> Enter the busLine: ");
        while (!input.hasNextInt()) {
            System.out.print("> Invalid. Try again: ");
            input.next();
        }
        int secondpub = input.nextInt();

        Broker_Thread.brokers.forEach((broker) -> {
            firstPub.push(node.topics.get(secondpub), broker);
        });

        System.out.println("\n-- Consumer - Please give the busline you want. --\n");
        System.out.print("> Enter the busLine: ");
        while (!input.hasNextInt()) {
            System.out.print("> Invalid. Try again: ");
            input.next();
        }
        int consline = input.nextInt();

        Broker_Thread.brokers.forEach((broker) -> {
            consumer.register(String.valueOf(consline), broker);
        });

        Broker_Thread.brokers.forEach((broker) -> {
            broker.broadcast();
        });

        System.out.println("Consumer: ");
        //consumer.printMessages();
        for (Bus b : node.buses) {
            if (b.getLineNumber().equals(consline)) {
                for (Value v : node.locations) {
                    if (b.equals(v.getBus())) {
                        System.out.println("Consumer Bus Location: " + v.toString());
                    }
                }
            }

        }

    }
}
