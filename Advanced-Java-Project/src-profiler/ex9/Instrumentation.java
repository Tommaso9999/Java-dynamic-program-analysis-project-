package ex9;

import ch.usi.dag.disl.annotation.Before;
import ch.usi.dag.disl.dynamiccontext.DynamicContext;
import ch.usi.dag.disl.marker.BodyMarker;
import ch.usi.dag.disl.staticcontext.MethodStaticContext;


public class Instrumentation {

    // Before the execution of every method, takes the thread name and populate the associated list with the methods' names
    @Before(marker = BodyMarker.class,
            scope="ex9.MainThread.*")
    static void afterStatic(DynamicContext dc, MethodStaticContext msc) {

        // Condition to avoid constructor and static initialisations
        if(!(msc.getUniqueInternalName().contains("<init>") || msc.getUniqueInternalName().contains("<clinit>")))

            // Call profiler method to populate the hash map
            ex9.Profiler.ex9method(Thread.currentThread().getName(), msc.thisMethodFullName());
    }

}