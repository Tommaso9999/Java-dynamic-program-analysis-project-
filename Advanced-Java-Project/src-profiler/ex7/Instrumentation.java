package ex7;

import ch.usi.dag.disl.annotation.Before;
import ch.usi.dag.disl.dynamiccontext.DynamicContext;
import ch.usi.dag.disl.marker.BasicBlockMarker;
import ch.usi.dag.disl.marker.BodyMarker;
import ch.usi.dag.disl.marker.BytecodeMarker;
import ch.usi.dag.disl.staticcontext.ClassStaticContext;
import ch.usi.dag.disl.staticcontext.InstructionStaticContext;
import ch.usi.dag.disl.staticcontext.MethodStaticContext;


import java.util.concurrent.ConcurrentHashMap;

public class Instrumentation {

    // Get DoubleCounter And FloatCounter locks
    // Before the monitorenter instruction, get the hash code of local variable at position 1 in the operand stack.
    // Pass this information with the name of the class to the Profiler method
    @Before(marker = BytecodeMarker.class,
            args = "monitorenter",
            scope = "ex7.MainThread.*")
    static void beforeMonitorEnter(InstructionStaticContext isc, DynamicContext dc) {
        // Concurrent Hash Map to populate
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();

        // Populating the Hash Map
        map.put(dc.getLocalVariableValue(1, Number.class).hashCode(), isc.getClass().toString());
            Profiler.countSynchMethod(map);
    }

    // Get all the locks done by java.lang.Class
    // Before each method, load the class to get the Class context and get the hash code and the name
    // Pass the information to the profiler methods
    // Use a synchronized guard to get only synch methods
    @Before(marker = BodyMarker.class,
            guard = SynchronizedGuard.class,
            scope = "ex7.MainThread.*")
    static void countSynchMethod(MethodStaticContext msc, ClassStaticContext csc, DynamicContext dc){
        // Load Class
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        // Concurrent hash map for static methods
        ConcurrentHashMap<Integer, String> staticMap = new ConcurrentHashMap<>();

        // Concurrent hash map for non-static methods
        ConcurrentHashMap<Integer, String> noStaticMap = new ConcurrentHashMap<>();

        // If statement to get static and not static methods
            if(msc.isMethodStatic()) {

                // Try block to catch ClassNotFound Exception
                try {
                    // Load class
                    Class<?> staticClass = classLoader.loadClass(csc.getName());

                    // Populate static map
                    staticMap.put(staticClass.hashCode(), staticClass.getClass().getName());

                    // Call profiler method
                    Profiler.countSynchMethod(staticMap);
                } catch (ClassNotFoundException e){
                }
            } else {
                // Try block to catch ClassNotFound Exception
                try {
                    // Load class
                    Class<?> instanceClass = classLoader.loadClass(dc.getThis().toString());

                    // Populate non static map
                    noStaticMap.put(instanceClass.hashCode(), instanceClass.getClass().getName());

                    // Call profiler method
                    Profiler.countSynchMethod(noStaticMap);
                } catch (ClassNotFoundException e){
                }
            }
    }

    // Get all locks acquired by Ex7.MainThread
    // Locks in this Thread are acquired by the method getN(), we count the lock before each execution of that method
    @Before(marker = BasicBlockMarker.class,
            scope = "int getN()")
    static void countSynchMethod(MethodStaticContext msc){

        // Concurrent hash map to populate
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();

        // Populate hash map
        map.put(msc.hashCode(),msc.getClass().getName());

        // Call profiler method
        Profiler.countSynchMethod(map);
    }

}
