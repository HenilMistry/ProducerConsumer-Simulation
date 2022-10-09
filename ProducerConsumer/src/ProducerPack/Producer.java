package ProducerPack;

import java.util.List;
import java.util.Random;

public class Producer implements Runnable {
    private List<String> buffer;
    private String EOF;
    private int ProductionRate;

    public Producer(List<String> buffer, String EOF, int productionRate) {
        this.buffer = buffer;
        this.EOF = EOF;
        this.ProductionRate = productionRate;
    }


    @Override
    public void run() {
        // items, that will be produced by the producer....
        String numbers[] = {"1", "2", "3"};

        // the critical section...
        for (String number : numbers) {

            // the synchronized block...acquiring lock on buffer...
            synchronized (buffer) {
                buffer.add(number);
                System.out.println(Thread.currentThread().getName() + " added " + number);
                try {
                    Random random = new Random();
                    Thread.sleep(random.nextInt(this.ProductionRate));
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " interrupted.");
                }
            }
            // the synchronized block ends....

        }

        // production ends by the producer...
        System.out.println(Thread.currentThread().getName() + " added " + this.EOF);
        synchronized (buffer) {
            buffer.add(this.EOF);
        }
    }
}
