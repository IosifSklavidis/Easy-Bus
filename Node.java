package buspubsub;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
public class Node {

    List<Bus> buses = new ArrayList<>();
    List<Topic> topics = new ArrayList<>();
    List<Value> locations = new ArrayList<>();

    public void init() {

        final String BUS_LINES = "busLinesNew.txt";
        final String ROUTE_CODES = "RouteCodesNew.txt";
        final String BUS_POSITIONS = "busPositionsNew.txt";

        try (BufferedReader in = new BufferedReader(new FileReader(BUS_LINES))) {
            String str;
            while ((str = in.readLine()) != null) {

                String[] tokens = str.split(",");

                String lineCode = tokens[0];
                String busLineid = tokens[1];
                //String info = tokens[3];

                Bus bus = new Bus();
                bus.setLineNumber(lineCode);
                bus.setBusLineId(busLineid);
                //bus.setInfo(info);
                buses.add(bus);

            }
        } catch (IOException e) {
            System.out.println("File Read Error");
        }

        try (BufferedReader in = new BufferedReader(new FileReader(ROUTE_CODES))) {
            String str;
            while ((str = in.readLine()) != null) {

                String[] tokens = str.split(",");

                String routeCode = tokens[0];
                String lineCode = tokens[1];

                for (Bus b : buses) {
                    if (b.getLineNumber().equals(lineCode)) {
                        //System.out.println(b.toString());
                        b.setRouteCode(routeCode);

                    }

                }
            }
        } catch (IOException e) {
            System.out.println("File Read Error");
        }

        try (BufferedReader in = new BufferedReader(new FileReader(BUS_POSITIONS))) {
            String str;
            while ((str = in.readLine()) != null) {

                String[] tokens = str.split(",");

                String lineCode = tokens[0];
                String routeCode = tokens[1];
                String vehicleId = tokens[2];
                double lat = Double.parseDouble(tokens[3]);
                double lon = Double.parseDouble(tokens[4]);

                for (Bus b : buses) {
                    if ((b.getRouteCode().equals(routeCode)) && (b.getLineNumber().equals(lineCode))) {
                        b.setVehicleId(vehicleId);
                        Value value = new Value(b, lat, lon);
                        locations.add(value);
                        //System.out.println(value.toString());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("File Read Error");
        }

        for (Bus b : buses) {
            Topic topic = new Topic(b.getBusLineId());
            topics.add(topic);
        }

    }

}
