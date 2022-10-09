package UnboundedBuffer;

public abstract class Entities implements Runnable{
    protected static Integer buffer;
    protected int PROCESS_TIME;
    protected int ITEM_LIMIT;

    public Entities(int item, int ITEM_LIMIT) {
        this(item, ITEM_LIMIT, 1000);
    }

    public Entities(int item, int ITEM_LIMIT, int PROCESS_TIME) {
        buffer = item;
        this.PROCESS_TIME = PROCESS_TIME;
        this.ITEM_LIMIT = ITEM_LIMIT;
    }
}
