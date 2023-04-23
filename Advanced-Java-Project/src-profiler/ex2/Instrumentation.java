package ex2;

import ch.usi.dag.disl.annotation.*;
import ch.usi.dag.disl.marker.BytecodeMarker;

public class Instrumentation {

    // Register read accesses before "getfield" instruction
    @Before(marker = BytecodeMarker.class,
            args = "getfield")
    static void afterFieldRead(){
        Profiler.registerReadAccesses();
    }

    // Register write accesses before "putfield" instruction
    @Before(marker = BytecodeMarker.class,
                    args = "putfield")
    static void afterFieldWrite(){
        Profiler.registerWriteAccesses();
    }

    // NOTE: We could have instrumented also static fields accesses by adding respectively "getstatic" and "putstatic"
    // in the annotation args.
}
