package ConsumerPack;

import java.util.List;
import java.util.Random;

public class Consumer implements Runnable {
    private List<String> buffer;
    private String EOF;
    private int consumtionRate;

    public Consumer(List<String> buffer, String EOF, int consumtionRate) {
        this.buffer = buffer;
        this.EOF = EOF;
        this.consumtionRate = consumtionRate;
    }

    @Override
    public void run() {

        // infinite loop...
        while (true) {

            // synchronized block...critical section..
            synchronized (buffer) {
                // if there are no any items in the buffer...
                if (buffer.isEmpty()) {
                    continue;
                }

                // if the production is ends....
                if (buffer.get(0).equals(this.EOF)) {
                    System.out.println(Thread.currentThread().getName() + " exiting.");
                    break;
                }
                // there are some items ready to be consumed...
                else {
                    System.out.println(Thread.currentThread().getName() + " removed " + buffer.remove(0));
                    try {
                        Random random = new Random();
                        Thread.sleep(random.nextInt(consumtionRate));
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + " interrupted.");
                    }
                }
            }
        }
    }

}
