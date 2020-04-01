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
public class PublisherImpl implements Publisher {

    public void push(Topic topic, Broker broker) {
        broker.pull(topic);
    }
}
