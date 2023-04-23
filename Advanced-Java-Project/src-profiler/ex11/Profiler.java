package ex11;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

public class Profiler {
    // Instantiate Shutdown Hook
    static {Runtime.getRuntime().addShutdownHook(new Thread(ex11.Profiler::dump));}

    // Dump method to print the formatted string at the end of the application
    public static void dump() {

        // For loop to get the key and value
        for (String key : result.keySet()) {

            // Printing format
            System.out.println(key + " #invokes " + result.get(key));
        }
    }

    // Instantiate the Concurrent Hashmap
    public static ConcurrentHashMap<String, LongAdder> result = new ConcurrentHashMap<>();

    // Method to collect into the Hash Map the needed values
    public static void ex11Map(String m, int isc, String c, String s, String a){

        // Compute if absent method to populate the Hash Map
        result.computeIfAbsent("CallerMethodFQIN: " + m +
                                    " CallerBCI: " + isc +
                                    " Invoke Type: " + a +
                                    " CalleeMethodFQIN: " + c +
                                    " TargetMethod: " + s
                                    , b -> new LongAdder()).increment();
    }

    // Method to collected needed values in th Hash Map (just for virtual)
    public static void ex11virtualMap(String m, int isc, String c, Class s, String a){

        // Try and Catch to avoid NoSuchMethodException
        try {
            // Check if the method instrumented is C or A and B.
            if(s.getSimpleName().equals("C")){

                // Getting the methods of the Superclass of C (B in this case)
                Method me = s.getSuperclass().getDeclaredMethod("foo", null);

                // Structure the String for target method
                String l = s.getPackageName() +"/" + s.getSuperclass().getSimpleName() +"." + me.getName()+ "()" + me.getReturnType();

                // Compute if absent to populate the Hash Map
                result.computeIfAbsent("CallerMethodFQIN: " + m +
                                            " CallerBCI: "+ isc +
                                            " Invoke Type: " + a +
                                            " CalleeMethodFQIN: " + c +
                                            " TargetMethod: "+ l
                                            , b -> new LongAdder()).increment();
            }
            // A and B are considered from here
            else {
                //  Getting the methods of the instrumented class (not superclass in this case, as we are considering A and B).
                Method mes = s.getDeclaredMethod("foo", null);

                // Structure the String for target method
                String lo = s.getPackageName() + "/" +s.getSimpleName()+"."+ mes.getName() +"()" + mes.getReturnType().toString();

                // Compute if absent to populate the Hash Map
                result.computeIfAbsent("CallerMethodFQIN: " + m +
                                            " CallerBCI: " + isc +
                                            " Invoke Type: " + a +
                                            " CalleeMethodFQIN: " + c +
                                            " TargetMethod: "+ lo
                                            , b -> new LongAdder()).increment();
            }
        } catch (NoSuchMethodException e){
        }
    }

}