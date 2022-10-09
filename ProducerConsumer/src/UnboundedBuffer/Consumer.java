package UnboundedBuffer;

public class Consumer extends Entities {

    public Consumer(int item, int ITEM_LIMIT) {
        super(item, ITEM_LIMIT);
    }

    public Consumer(int item, int ITEM_LIMIT, int PROCESS_TIME) {
        super(item, ITEM_LIMIT, PROCESS_TIME);
    }

    @Override
    public void run() {
        System.out.println("CONSUMPTION LIMIT : "+ITEM_LIMIT);

        while (ITEM_LIMIT >= 0) {
            synchronized (buffer) {
                if(buffer <= 0) {
                    System.out.println(Thread.currentThread().getName()+" cannot consume an item because Buffer : "+buffer);
                } else {
                    buffer --;
                    ITEM_LIMIT -= 1;
                    System.out.println(Thread.currentThread().getName()+" has consumed one item. Buffer : "+buffer);
                }
                try {
                    Thread.sleep(this.PROCESS_TIME);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName()+" has been interrupted.");
                }
            }
        }
    }
}
