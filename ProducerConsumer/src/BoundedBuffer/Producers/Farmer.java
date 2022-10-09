package BoundedBuffer.Producers;

import BoundedBuffer.Buffers.Warehouse;
import BoundedBuffer.Crops.Crop;
import BoundedBuffer.Entities.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Farmer extends Person {

    private Map<Crop, Integer> production;
    private List<Crop> siloStorage;
    private Thread transportation = new Thread(new Truck());

    private class Truck implements Runnable {
        public Truck() {

        }

        @Override
        public void run() {
            boolean transport = true;

            while (transport) {
                boolean sleep = false;
                // getting items to be transported...
                synchronized (siloStorage) {
                    if(siloStorage.size() > 0) {
                        // if production is not ended...
                        if(!siloStorage.get(0).toString().equals("EOP")) {
                            storage.storeCrop(siloStorage.get(0));
                            siloStorage.remove(0);
                        } else {
                            storage.storeCrop(siloStorage.get(0));
                            System.out.println("<<< Truck is stopping transportation!!");
                            transport = false;
                        }
                    } else {
                        sleep = true;
                    }
                }
                // if items not found, try again after sometime...
                if (sleep) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + " interrupted.");
                    }
                    sleep = false;
                }
            }

        }
    }

    public Farmer(Map<Crop, Integer> production, Warehouse warehouse) {
        this.production = production;
        this.siloStorage = new ArrayList<>();
        storage = warehouse;
    }

    @Override
    public void run() {
        // start transportation...
        this.transportation.start();

        // start production of crops...
        for (Map.Entry<Crop, Integer> field : this.production.entrySet()) {
            for(int i=0; i<field.getValue(); i++) {
                System.out.println(">>> Producing...");
                // plant the crop...
                Crop cropInProduction = new Crop(field.getKey().PRODUCTION_TIME, field.getKey().CONSUMPTION_TIME, field.getKey().toString());
                Thread growthOfCrop = cropInProduction.produce();
                // wait for growth of the crop...
                try {
                    growthOfCrop.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // add crop to the silo storage...
                synchronized (siloStorage) {
                    this.siloStorage.add(cropInProduction);
                    System.out.println(">> Farmer has produced one "+this.siloStorage.get(this.siloStorage.size()-1));
                }
            }
        }

        // end of the production...
        synchronized (siloStorage) {
            System.out.println("<<< Farmer is exiting, stopping production!");
            this.siloStorage.add(new Crop("EOP"));
        }
    }
}
