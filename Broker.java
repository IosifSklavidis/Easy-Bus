package buspubsub;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author admin
 */
public class Broker {

    Map<String, Set<Consumer>> consumersTopicMap = new HashMap<>();
    private final Object o = new Object();
    Queue<Topic> topicsQueue = new LinkedList<>();

    public void pull(Topic topic) {
        topicsQueue.add(topic);
        //System.out.println(topic.toString());
    }

    public void addConsumer(String topic, Consumer consumer) {
        synchronized (o) {
            if (consumersTopicMap.containsKey(topic)) {
                Set<Consumer> consumers = consumersTopicMap.get(topic);
                consumers.add(consumer);
                consumersTopicMap.put(topic, consumers);
                //System.out.println("Consumer added: " + consumersTopicMap.toString());
            } else {
//			
                System.out.println("No compatible broker found...");
            }
        }
    }

    public void removeConsumer(String topic, Consumer consumer) {
        synchronized (o) {
            if (consumersTopicMap.containsKey(topic)) {
                Set<Consumer> consumers = consumersTopicMap.get(topic);
                consumers.remove(consumer);
                consumersTopicMap.put(topic, consumers);
            }
        }
    }

    public void broadcast() {
        if (topicsQueue.isEmpty()) {
            System.out.println("No topics from publishers to display");
        } else {
            while (!topicsQueue.isEmpty()) {
                Topic topic = topicsQueue.remove();
                String busLine = topic.getBusLine();
                //System.out.println("busline: " + busLine);
                Set<Consumer> consumersOfTopic = consumersTopicMap.get(busLine);

                //System.out.println(topicsQueue.toString());
                if (consumersOfTopic != null) {
                    consumersOfTopic.parallelStream().forEach((consumer) -> {
                        List<Topic> consumerMessages = consumer.getConsumerMessages();
                        consumerMessages.add(topic);
                        consumer.setConsumerMessages(consumerMessages);
                    });
                } else {
                    System.out.println("BusLine: " + busLine + " has no consumers");
                }
            }
        }
    }

    public void notifyPublisher(String busLine, Consumer consumer) {
        synchronized (o) {
            if (topicsQueue.isEmpty()) {
                System.out.println("No topics from publishers to display");
            } else {
                while (!topicsQueue.isEmpty()) {
                    Topic topic = topicsQueue.remove();

                    if (topic.getBusLine().equals(busLine)) {

                        Set<Consumer> consumersOfTopic = consumersTopicMap.get(topic.getBusLine());
                        if (consumersOfTopic != null) {
                            consumersOfTopic.stream().filter((_consumer) -> (_consumer.equals(consumer))).map((_item) -> consumer.getConsumerMessages()).map((consumerMessages) -> {

                                consumerMessages.add(topic);
                                return consumerMessages;
                            }).forEachOrdered((consumerMessages) -> {
                                consumer.setConsumerMessages(consumerMessages);
                            });
                        } else {
                            System.out.println("BusLine: " + busLine + " has no consumers");
                        }
                    }
                }
            }
        }
    }

}
