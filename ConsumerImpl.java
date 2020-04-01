package buspubsub;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author admin
 */
public class ConsumerImpl extends Consumer {

    public void register(String topic, Broker broker) {
        broker.addConsumer(topic, this);
    }

    public void disconnect(String topic, Broker broker) {
        broker.removeConsumer(topic, this);
    }

    public void visualiseData(String topic, Broker broker) {
        broker.notifyPublisher(topic, this);

    }
}
