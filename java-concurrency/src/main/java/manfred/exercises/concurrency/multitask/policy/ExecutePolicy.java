package manfred.exercises.concurrency.multitask.policy;

public interface ExecutePolicy {

    long timeoutInMs();

    boolean submitOrder();
}
