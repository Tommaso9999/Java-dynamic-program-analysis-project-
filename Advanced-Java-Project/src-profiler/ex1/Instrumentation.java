package ex1;

import ch.usi.dag.disl.annotation.After;
import ch.usi.dag.disl.annotation.SyntheticLocal;
import ch.usi.dag.disl.annotation.Before;
import ch.usi.dag.disl.marker.BodyMarker;
import ch.usi.dag.disl.staticcontext.MethodStaticContext;

public class Instrumentation {

    // timeStamp variable
    @SyntheticLocal
    static long entryTimestamp;

    // Catch time before method
    @Before(marker = BodyMarker.class,
            scope = "ex1.MainThread.factorial")
    static void onMethodEntry() {
        entryTimestamp = System.nanoTime();
    }

    // Print time after the method as been executed
    @After(marker = BodyMarker.class,
    scope = "ex1.MainThread.factorial")

    static void onMethodExit(MethodStaticContext msc){

    long time = System.nanoTime() - entryTimestamp;
        System.out.println(Thread.currentThread().getName() +
                "- Execution time for " +
                msc.getUniqueInternalName() +
                ":" + time + "ns");
    }

}
