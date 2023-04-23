package ex2;

import java.util.concurrent.atomic.LongAdder;
import java.lang.Thread;

public class Profiler {

    // ShutDown hook prints the result after the program executio
    static class ShutdownThread extends Thread {

        public void run() {
            System.out.format("Instance Field Read Accesses: %s\nInstance Field Write Accesses: %s",
                    readAccess.sum(),
                    writeAccess.sum());
        }
    }

    // Instantiate the Shutdown hook
    static {
        Runtime.getRuntime().addShutdownHook(new ShutdownThread());
    }

    // Counter for write accesses
    private static LongAdder readAccess = new LongAdder();

    // Counter for write accesses
    private static LongAdder writeAccess = new LongAdder();

    // Increment for read accesses
    public static void registerReadAccesses() {readAccess.increment();}

    // Increment for write accesses
    public static void registerWriteAccesses() {writeAccess.increment();}
}
