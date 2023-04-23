package ex4;

import ch.usi.dag.disl.annotation.AfterThrowing;
import ch.usi.dag.disl.dynamiccontext.DynamicContext;
import ch.usi.dag.disl.marker.TryClauseMarker;


public class Instrumentation {

    // After an exception is thrown, take the name of the Class where it occurs
    @AfterThrowing(marker = TryClauseMarker.class)
    static void afterExceptionThrown(DynamicContext dc) {
        Profiler.profileAllocation(dc.getException().getClass().getName());
    }

}
