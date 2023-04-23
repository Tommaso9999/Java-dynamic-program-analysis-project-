package ex6;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;


public class Profiler {

    // Instantiate Shutdown Hook
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(Profiler::dump));
    }

    // Shutdown Hook printing the formatted string
    public static void dump() {
        for(String key : counter.keySet()){
            System.out.println("Method name: "+ key + " - # executed loops: " + counter.get(key));
        }
    }

    // Loop counter
    public static ConcurrentHashMap<String, LongAdder> counter = new ConcurrentHashMap<String, LongAdder>();

    // Increment counter
    public static void profileAllocation(String MethodName) {
        counter.computeIfAbsent(MethodName, a -> new LongAdder()).increment();
    }
}
