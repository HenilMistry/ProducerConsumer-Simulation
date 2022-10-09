package ConsumerPack;

import java.util.List;

public class BasicConsumer implements Runnable {
    private List<String> buffer;
    private String EOF;

    public BasicConsumer(List<String> buffer, String EOF) {
        this.buffer = buffer;
        this.EOF = EOF;
    }

    @Override
    public void run() {

        while (true){
            if (buffer.isEmpty()){
                continue;
            }
            if (buffer.get(0).equals(this.EOF)){
                System.out.println(Thread.currentThread().getName()+" exiting.");
                break;
            } else {
                System.out.println(Thread.currentThread().getName()+ " removed " +buffer.remove(0));
            }
        }
    }
}
