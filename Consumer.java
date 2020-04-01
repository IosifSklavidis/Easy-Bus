package buspubsub;

import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author admin
 */
public abstract class Consumer {

    private List<Topic> consumerMessages = new ArrayList<Topic>();

    public List<Topic> getConsumerMessages() {
        return consumerMessages;
    }

    public void setConsumerMessages(List<Topic> consumerMessages) {
        this.consumerMessages = consumerMessages;
    }

    public abstract void register(String topic, Broker broker);

    public abstract void disconnect(String topic, Broker broker);

    public abstract void visualiseData(String topic, Broker broker);

    public void printMessages() {
        for (Topic topic : consumerMessages) {
            System.out.println("Bus Line : " + topic.getBusLine());
        }
    }
}
