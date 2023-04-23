package ex7;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class Profiler {

    // Instantiate Shutdown Hook
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(Profiler::dump));
    }

    // Format String for printing with Shutdown Hook
    public static void dump() {
        for (ConcurrentHashMap<Integer, String> innerKey : lockCounter.keySet()){
            for (Map.Entry<Integer, String> entry : innerKey.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue() + " - #Locks: " + lockCounter.get(innerKey));
            }
        }
    }

    // lock Counter by using as key another ConcurrentHashMap(K: HashCode, V: ClassName)
    public static ConcurrentHashMap<ConcurrentHashMap<Integer, String>, LongAdder> lockCounter =
                                                                    new ConcurrentHashMap<>();

    // Register the increment
    public static void countSynchMethod(ConcurrentHashMap synchMethodHashCodeClass){
        lockCounter.computeIfAbsent(synchMethodHashCodeClass, b -> new LongAdder()).increment();
    }
}
