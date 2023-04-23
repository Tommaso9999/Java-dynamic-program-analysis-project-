package ex10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Profiler {

    // Shutdown Hook
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(Profiler::dump));
    }

    // Apply Shutdown hook
    public static void dump() {

        // Loop for printing
        for(String key : methodsToProcess.keySet()){
            //Print Title with class name as key
            System.out.println("=== Class Name: " + key + " ===");

            //Create and synchronize list
            List<String> synchList;
            synchList = Collections.synchronizedList(methodsToProcess.get(key));

            //Loop for printing
            for (String synchElement : synchList){
                System.out.println(synchElement);
            }
        }
    }

    // Concurrent HashMap
    public static ConcurrentHashMap<String, ArrayList<String>> methodsToProcess = new ConcurrentHashMap<>();

    // Profiler method to get the methods
    public static void getMethods(String className, String decMethod){

            // If the key (class name) is missing, it will add it to the HashMap keyset and it creates a new ArrayList to each distinct key (class name).
            methodsToProcess.computeIfAbsent(className, a -> new ArrayList<>());

            // If the ArrayList inside the HashMap does not contain the method, add it to the ArrayList inside the HashMap
            if(!methodsToProcess.get(className).contains((decMethod))){
                methodsToProcess.computeIfPresent(className, (key, val) -> val).add(decMethod);
            }

    }
}
