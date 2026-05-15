package manfred.exercises.concurrency.publish;

public class Init {
    public Holder holder = new Holder(42);

    public void initialize() {
        holder = new Holder(42);
    }
}
