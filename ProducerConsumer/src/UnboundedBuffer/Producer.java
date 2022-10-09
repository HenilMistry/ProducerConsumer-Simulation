package UnboundedBuffer;

public class Producer extends Entities {

    public Producer(int item, int ITEM_LIMIT) {
        super(item, ITEM_LIMIT);
    }

    public Producer(int item, int ITEM_LIMIT, int PROCESS_TIME) {
        super(item, ITEM_LIMIT, PROCESS_TIME);
    }

    @Override
    public void run() {
        System.out.println("PRODUCTION LIMIT : "+ITEM_LIMIT);
        for (int i=1; i<=this.ITEM_LIMIT; i++) {
            synchronized (buffer) {
                buffer++;
                System.out.println(Thread.currentThread().getName()+" has produced an item. Buffer : "+buffer);
                try {
                    Thread.sleep(this.PROCESS_TIME);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName()+" has been interrupted.");
                }
            }
        }
    }
}
