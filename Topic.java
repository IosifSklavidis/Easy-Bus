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
public class Topic {

    private String busLine;

    public Topic() {
    }

    public Topic(String busLine) {
        this.busLine = busLine;
    }

    public String getBusLine() {
        return busLine;
    }

    public void setBusLine(String busLine) {
        this.busLine = busLine;
    }

    @Override
    public String toString() {
        return "Topic{" + "busLine=" + busLine + "}\n";
    }

}
