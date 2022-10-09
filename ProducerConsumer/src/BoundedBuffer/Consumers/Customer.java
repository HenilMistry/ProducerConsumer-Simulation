package BoundedBuffer.Consumers;

import BoundedBuffer.Buffers.Warehouse;
import BoundedBuffer.Crops.Crop;
import BoundedBuffer.Entities.Person;

import java.util.Map;

public class Customer extends Person {

    private Map<Crop, Integer> consumption;

    public Customer(Map<Crop, Integer> consumption, Warehouse warehouse) {
        this.consumption = consumption;
        storage = warehouse;
    }

    @Override
    public void run() {
        for (Map.Entry<Crop, Integer> list : this.consumption.entrySet()) {
            int consumed = 0;
            while (consumed < list.getValue()) {
                Crop requiredForConsumption;
                requiredForConsumption = storage.getCrop(list.getKey());

                // it is not an end of production...
                if(requiredForConsumption.toString().compareTo("EOP")!=0) {
                    // try to digest crop...
                    Thread digestion = requiredForConsumption.consume();
                    try {
                        digestion.join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    consumed ++;
                    System.out.println(">>>> Customer has consumed "+consumed+" "+list.getKey());
                } else {
                    consumed = Integer.MAX_VALUE;
                    System.out.println(">>>> There is end of production and customer is exiting!");
                }
            }
        }

        System.out.println(">>>> Customer has consumed enough, customer exiting!");
    }
}
