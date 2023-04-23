package ex5;

import java.util.concurrent.atomic.LongAdder;

public class Profiler {

    // Printing format for safe accesses and unsafe accesses
    static class ShutdownThread extends Thread {

        public void run() {
            System.out.format("Safe allocation: %s\nUnsafe allocation: %s ",
                safeAccessCounter.sum(),
                unsafeAccessCounter);
        }
    }

    // Instantiating Shutdown Hook
    static {
        Runtime.getRuntime().addShutdownHook(new ShutdownThread());
    }

    // Counter for safe accesses
    private static LongAdder safeAccessCounter = new LongAdder();

    // Counter for unsafe accesses
    private static LongAdder unsafeAccessCounter = new LongAdder();

    // Increment the safe access counter
    public static void registerSafeAccesses() {safeAccessCounter.increment();}

    // Increment the unsafe access counter
    public static void registerUnsafeAccesses() {unsafeAccessCounter.increment();}

}
