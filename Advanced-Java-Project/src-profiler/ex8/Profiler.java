package ex8;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

public class Profiler {

    static ArrayList<String> list = new ArrayList<>(Arrays.asList("App Thread: 1", "App Thread: 2", "App Thread: 3", "App Thread: 4",
                                                                  "App Thread: 5", "App Thread: 6", "App Thread: 7", "App Thread: 8",
                                                                  "App Thread: 9", "App Thread: 10"));
    // Instantiate Shutdown Hook
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(Profiler::dump));
    }

    // Format the string and print at the end of the script execution
    public static void dump() {

        Map<String, List<LongAdder>> result = new HashMap<>();
        for (String key : counterStatic.keySet()) {
            List<LongAdder> values = new ArrayList<>();
            values.add(counterStatic.get(key));
            values.add(counterSpecial.get(key));
            values.add(counterVirtual.get(key));
            values.add(counterDynamic.get(key));
            result.put(key, values);
        }

        result.entrySet().forEach(a ->System.out.println(a.getKey() + " -#static: "+ a.getValue().get(0)
                +" -#special: " + a.getValue().get(1)
                +" -#virtual: " + a.getValue().get(2)
                +" -#dynamic: " + a.getValue().get(3)
        ));
    }

    // Static counter
    public static ConcurrentHashMap<String, LongAdder> counterStatic = new ConcurrentHashMap<>();

    // Special counter
    public static ConcurrentHashMap<String, LongAdder> counterSpecial = new ConcurrentHashMap<>();

    // Virtual counter
    public static ConcurrentHashMap<String, LongAdder> counterVirtual = new ConcurrentHashMap<>();

    // Dynamic counter
    public static ConcurrentHashMap<String, LongAdder> counterDynamic = new ConcurrentHashMap<>();

    // Method incrementing the static counter
    public static void incrementStatic(String threadName) {
        // Boolean to check if the Thread name is in the list
        boolean flag = list.contains(threadName);
        // Increment only if the name of the thread is one of the following
        if (flag)
        {
            counterStatic.computeIfAbsent(threadName, a -> new LongAdder()).increment();
        }
    }

    // Method incrementing special counter
    public static void incrementSpecial(String threadName){
        // Boolean to check if the Thread name is in the list
        boolean flag = list.contains(threadName);
        // Avoid null pointer exception
        if(threadName !=null){

            // Increment only if the name of the thread is one of the following
            if (flag){
                counterSpecial.computeIfAbsent(threadName, a -> new LongAdder()).increment();
            }
        }
    }

    // Method incrementing virtual counter
    public static void incrementVirtual(String threadName){
        // Boolean to check if the Thread name is in the list
        boolean flag = list.contains(threadName);
        // Increment only if the name of the thread is one of the following
        if (flag)
        {
            counterVirtual.computeIfAbsent(threadName, a -> new LongAdder()).increment();
        }
    }

    // Method incrementing dynamic counter
    public static void incrementDynamic(String threadName){
        // Boolean to check if the Thread name is in the list
        boolean flag = list.contains(threadName);
        // Increment only if the name of the thread is one of the following
        if(flag)
        {
            counterDynamic.computeIfAbsent(threadName, a -> new LongAdder()).increment();
        }
    }
}