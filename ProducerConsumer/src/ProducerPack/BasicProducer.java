package ProducerPack;

import java.util.List;

public class BasicProducer implements Runnable {
    private List<String> buffer;
    private String EOF;

    public BasicProducer(List<String> buffer, String EOF) {
        this.buffer = buffer;
        this.EOF = EOF;
    }


    @Override
    public void run() {
        String numbers[] = {"1","2","3"};
        for (String number : numbers){
            buffer.add(number);
            System.out.println(Thread.currentThread().getName()+" added "+number);

        }
        System.out.println(Thread.currentThread().getName()+" added "+this.EOF);
        buffer.add(this.EOF);
    }
}
