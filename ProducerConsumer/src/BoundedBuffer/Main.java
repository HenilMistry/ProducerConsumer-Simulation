package BoundedBuffer;

import BoundedBuffer.Buffers.Warehouse;
import BoundedBuffer.Consumers.Customer;
import BoundedBuffer.Crops.Crop;
import BoundedBuffer.Entities.Person;
import BoundedBuffer.Producers.Farmer;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Crop wheat = new Crop(4000, 2000, "Wheat");
        Crop strawberry = new Crop(5000, 10000, "Strawberry");

        Warehouse market = new Warehouse(5);

        Map<Crop, Integer> listOfProduction = new HashMap<>();
        listOfProduction.put(wheat, 5);
        listOfProduction.put(strawberry, 10);

        Map<Crop, Integer> listOfConsumption = new HashMap<>();
        listOfConsumption.put(strawberry, 5);
        listOfConsumption.put(strawberry, 5);

        Farmer henil = new Farmer(listOfProduction, market);
        Customer heal = new Customer(listOfConsumption, market);

        Thread t1 = new Thread(henil);
        Thread t2 = new Thread(heal);
        t1.start();
        t2.start();
    }
}
