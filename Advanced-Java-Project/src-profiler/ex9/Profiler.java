package ex9;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Profiler {

    // Instantiate Shutdown Hook
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(Profiler::dump));
    }

    // Format string to print at the end of the program execution
    public static void dump() {

        for (String key : iname.keySet()){

            // Print header
            System.out.println("=== "+key+" ===");

            // Array of methods to print
            ArrayList<String> methods = iname.get(key);

            // Loop for printing
            for (String i : methods) {
                System.out.println(i);
            }
        }
    }

    // Hash map to store for each thread the list of methods executed by that thread
    public static ConcurrentHashMap<String, ArrayList<String>> iname = new ConcurrentHashMap<>();

    // Method for computing hash map structure
    public static void ex9method(String threadName, String msc) {

        // Avoid NullPointerException
        if(threadName!=null) {

            // Populate iname with new thread name associated with a list of String
            iname.computeIfAbsent(threadName, a -> new ArrayList<String>());

            // If the thread name is present, add to the value list of the hash map the method name
            iname.computeIfPresent(threadName, (key, val)->val).add(msc);
        }
    }

}