package UnboundedBuffer;

public class Main {
    public static void main(String[] args) {
        Integer unboundedBuffer = 0;

        Thread p = new Thread(new Producer(unboundedBuffer, 2));
        Thread p1 = new Thread(new Producer(unboundedBuffer, 2, 2000));
        Thread c = new Thread(new Consumer(unboundedBuffer, 5, 4000));

        p.setName("Farmer");
        p1.setName("Farmer 1");
        c.setName("Customer");

        p.start();
        p1.start();
        c.start();
    }
}
