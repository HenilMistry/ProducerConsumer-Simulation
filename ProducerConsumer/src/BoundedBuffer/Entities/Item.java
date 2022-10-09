package BoundedBuffer.Entities;

public abstract class Item implements Runnable {
    public int PRODUCTION_TIME;
    public int CONSUMPTION_TIME;

    public Item() {
        this(1000, 2000);
    }

    public Item(int PRODUCTION_TIME, int CONSUMPTION_TIME) {
        this.PRODUCTION_TIME = PRODUCTION_TIME;
        this.CONSUMPTION_TIME = CONSUMPTION_TIME;
    }
}
