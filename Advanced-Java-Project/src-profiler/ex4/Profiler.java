package ex4;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

public class Profiler {

    // Instantiate Shutdown Hook
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(Profiler::dump));
    }

    // Shutdown Hook prints the name of the Class where the exception occurs with the counter
    public static void dump() {
        for (String key : counter.keySet()){
            System.out.println("Exception class: " + key + " - occurrences: " + counter.get(key));
        }
    }

    // Counter for exception thrown
    public static ConcurrentHashMap<String, LongAdder> counter = new ConcurrentHashMap<String, LongAdder>();

    // Increment every time an exception is thrown
    public static void profileAllocation(String exceptionName) {
        counter.computeIfAbsent(exceptionName, a -> new LongAdder()).increment();
    }
}
