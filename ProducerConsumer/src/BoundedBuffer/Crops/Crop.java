package BoundedBuffer.Crops;

import BoundedBuffer.Entities.Item;

public class Crop extends Item {

    private String name;
    private int PROCESS_TIME = this.PRODUCTION_TIME;

    public Crop(String name) {
        super();
        this.name = name;
    }

    public Crop(int PRODUCTION_TIME, int CONSUMPTION_TIME, String name) {
        super(PRODUCTION_TIME, CONSUMPTION_TIME);
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(this.PROCESS_TIME);
        } catch (InterruptedException e) {
            System.out.println("Production of "+this.name+" is interrupted!");
        }
    }

    public Thread produce() {
        this.PROCESS_TIME = this.PRODUCTION_TIME;
        Thread t = new Thread(this);
        t.start();
        return t;
    }

    public Thread consume() {
        this.PROCESS_TIME = this.CONSUMPTION_TIME;
        Thread t = new Thread(this);
        t.start();
        return t;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
